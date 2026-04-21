package com.group.smoothtune.infrastructure.storage;

import com.group.smoothtune.domain.exception.InvalidAudioException;
import com.group.smoothtune.domain.exception.InvalidImageException;
import com.group.smoothtune.domain.port.AudioMetadataPort;
import com.group.smoothtune.domain.port.FileStoragePort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.UUID;

//Clase prueba
@Service
public class S3ServiceImpl{
//
//    private final S3Client s3Client;
//    private final AudioMetadataPort audioMetadataPort;
//    private final S3Presigner s3Presigner;
//
//    @Value("${aws.bucket.name}")
//    private String bucketName;
//
//    public S3ServiceImpl(S3Client s3Client, AudioMetadataPort audioMetadataPort, S3Presigner s3Presigner) {
//        this.s3Client = s3Client;
//        this.audioMetadataPort = audioMetadataPort;
//        this.s3Presigner = s3Presigner;
//    }
//
//    //TODO: implementar exception
//    public String uploadSong(MultipartFile file) throws IOException {
//        if (!isMp3(file)) {
//            throw new InvalidAudioException("Solo se permiten archivos MP3");
//        }
//
//        String key = generateKey("songs", file.getOriginalFilename());
//
//        // Obtener duración
//        Float duration = audioMetadataPort.getDuration(file.getInputStream());
//
//        InputStream inputStream = file.getInputStream();
//
//        s3Client.putObject(
//                PutObjectRequest.builder()
//                        .bucket(bucketName)
//                        .key(key)
//                        .contentType(file.getContentType())
//                        .build(),
//                RequestBody.fromInputStream(inputStream, file.getSize())
//        );
//
//        // Aquí podrías guardar duración en DB
//        System.out.println("Duración: " + duration);
//
//        return key;
//    }
//
//    //TODO: implementar exception
//    public String uploadImage(MultipartFile file)throws IOException{
//        if(!isImage(file)){
//            throw new InvalidImageException("Solo se permiten archivos PNG, JPG y JPEG");
//        }
//
//        String key = generateKey("images", file.getOriginalFilename());
//
//        s3Client.putObject(
//                PutObjectRequest.builder()
//                        .bucket(bucketName)
//                        .key(key)
//                        .contentType(file.getContentType())
//                        .build(),
//                RequestBody.fromBytes(file.getBytes())
//        );
//
//        return key;
//    }
//
//    public String generatePresignedUrl(String key) {
//
//        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
//                .bucket(bucketName)
//                .key(key)
//                .build();
//
//        GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
//                .signatureDuration(Duration.ofMinutes(10)) // la url expira en 10 min
//                .getObjectRequest(getObjectRequest)
//                .build();
//
//        PresignedGetObjectRequest presignedRequest =
//                s3Presigner.presignGetObject(presignRequest);
//
//        return presignedRequest.url().toString();
//    }
//
//    public byte[] downloadFile(String key){
//        ResponseBytes<GetObjectResponse> objectAsBytes = s3Client.getObjectAsBytes(GetObjectRequest.builder()
//                .bucket(bucketName)
//                .key(key)
//                .build());
//        return objectAsBytes.asByteArray();
//    }
//
//    public void deleteFile(String key) {
//        try {
//            s3Client.deleteObject(DeleteObjectRequest.builder()
//                    .bucket(bucketName)
//                    .key(key)
//                    .build());
//        } catch (S3Exception e) {
//            throw new RuntimeException("Error eliminando archivo en S3: " + key, e);
//        }
//    }
//
//
//    //TODO: REVISAR SI HACE FALTA HACER LOS METODOS PUBLICOS DE COMPROVACION
//    //Metodo para revisar que el archivo si sea una imagen (png, jpg y jpeg)
//    public boolean isImage(MultipartFile file) {
//        String name = file.getOriginalFilename();
//        return file.getContentType() != null &&
//                file.getContentType().startsWith("image/") &&
//                name != null &&
//                (
//                        name.toLowerCase().endsWith(".png") ||
//                        name.toLowerCase().endsWith(".jpg") ||
//                        name.toLowerCase().endsWith(".jpeg")
//                );
//    }
//
//    //Metodo para revisar que el archivo si sea musica y tenag formato mp3
//    public boolean isMp3(MultipartFile file) {
//        String name = file.getOriginalFilename();
//        return file.getContentType() != null &&
//                file.getContentType().equals("audio/mpeg") &&
//                name != null &&
//                name.toLowerCase().endsWith(".mp3");
//    }
//
//    //Metodo para crear nombres unicos para los archivos
//    private String generateKey(String folder, String originalName) {
//        String extension = "";
//
//        if (originalName != null && originalName.contains(".")) {
//            extension = originalName.substring(originalName.lastIndexOf("."));
//        }
//
//        return folder + "/" + UUID.randomUUID() + extension;
//    }
}
