package com.group.smoothtune.infrastructure.storage;

import com.group.smoothtune.domain.exception.InvalidAudioException;
import com.group.smoothtune.domain.exception.InvalidImageException;
import com.group.smoothtune.domain.model.FileResource;
import com.group.smoothtune.domain.model.UploadResult;
import com.group.smoothtune.domain.port.AudioMetadataPort;
import com.group.smoothtune.domain.port.FileStoragePort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

import java.io.ByteArrayInputStream;
import java.time.Duration;
import java.util.UUID;

@Service
public class S3Service implements FileStoragePort {

    private final static int urlDuration = 10;

    private final S3Client s3Client;
    private final AudioMetadataPort audioMetadataPort;
    private final S3Presigner s3Presigner;

    @Value("${aws.bucket.name}")
    private String bucketName;

    public S3Service(S3Client s3Client,
                     AudioMetadataPort audioMetadataPort,
                     S3Presigner s3Presigner) {
        this.s3Client = s3Client;
        this.audioMetadataPort = audioMetadataPort;
        this.s3Presigner = s3Presigner;
    }

    @Override
    public UploadResult uploadSong(FileResource file) {

        if (!isMp3(file)) {
            throw new InvalidAudioException("Solo se permiten archivos MP3");
        }

        String key = generateKey("songs", file.getFileName());

        byte[] bytes = file.getContent();

        Float duration = audioMetadataPort.getDuration(
                new ByteArrayInputStream(bytes)
        );

        int size = bytes.length;

        s3Client.putObject(
                PutObjectRequest.builder()
                        .bucket(bucketName)
                        .key(key)
                        .contentType(file.getContentType())
                        .build(),
                RequestBody.fromBytes(bytes)
        );

        return new UploadResult(key, duration, size);
    }

    @Override
    public String uploadImage(FileResource file) {

        if (!isImage(file)) {
            throw new InvalidImageException("Solo PNG, JPG, JPEG");
        }

        String key = generateKey("images", file.getFileName());

        s3Client.putObject(
                PutObjectRequest.builder()
                        .bucket(bucketName)
                        .key(key)
                        .contentType(file.getContentType())
                        .build(),
                RequestBody.fromBytes(file.getContent())
        );

        return key;
    }

    @Override
    public String generatePresignedUrl(String key) {

        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                .signatureDuration(Duration.ofMinutes(urlDuration))
                .getObjectRequest(getObjectRequest)
                .build();

        PresignedGetObjectRequest presignedRequest =
                s3Presigner.presignGetObject(presignRequest);

        return presignedRequest.url().toString();
    }

    @Override
    public byte[] downloadFile(String key) {
        ResponseBytes<GetObjectResponse> objectAsBytes =
                s3Client.getObjectAsBytes(GetObjectRequest.builder()
                        .bucket(bucketName)
                        .key(key)
                        .build());

        return objectAsBytes.asByteArray();
    }

    @Override
    public void deleteFile(String key) {
        try {
            s3Client.deleteObject(DeleteObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .build());
        } catch (S3Exception e) {
            throw new RuntimeException("Error eliminando archivo en S3: " + key, e);
        }
    }

    // ================= VALIDACIONES =================

    private boolean isImage(FileResource file) {
        String name = file.getFileName();

        return file.getContentType() != null &&
                file.getContentType().startsWith("image/") &&
                name != null &&
                (
                        name.toLowerCase().endsWith(".png") ||
                        name.toLowerCase().endsWith(".jpg") ||
                        name.toLowerCase().endsWith(".jpeg")
                );
    }

    private boolean isMp3(FileResource file) {
        String name = file.getFileName();

        return file.getContentType() != null &&
                file.getContentType().equals("audio/mpeg") &&
                name != null &&
                name.toLowerCase().endsWith(".mp3");
    }

    // ================= UTILS =================

    private String generateKey(String folder, String originalName) {
        String extension = "";

        if (originalName != null && originalName.contains(".")) {
            extension = originalName.substring(originalName.lastIndexOf("."));
        }

        return folder + "/" + UUID.randomUUID() + extension;
    }
}
