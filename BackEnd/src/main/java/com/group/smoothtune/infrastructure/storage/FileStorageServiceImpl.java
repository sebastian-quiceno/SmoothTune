package com.group.smoothtune.infrastructure.storage;

import com.group.smoothtune.domain.port.FileStoragePort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageServiceImpl implements FileStoragePort {

    @Value("${storage.location}")
    private String storageLocation;

    @Override
    public String saveFile(InputStream file, String filename, String contentType) {
        try {
            String extension = getExtension(filename);
            if (!extension.equalsIgnoreCase(".mp3")) {
                throw new RuntimeException("Formato de archivo no valido");
            }
            String uniqueName = UUID.randomUUID().toString() + extension;

            Path storagePath = Paths.get(storageLocation).toAbsolutePath().normalize();
            Files.createDirectories(storagePath);

            Path targetLocation = storagePath.resolve(uniqueName);

            Files.copy(file, targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return uniqueName;

        } catch (Exception e) {
            throw new RuntimeException("Error saving file", e);
        }
    }

    @Override
    public InputStream getFile(String path) {
        try {
            Path filePath = Paths.get(storageLocation).resolve(path).normalize();
            return Files.newInputStream(filePath);
        } catch (Exception e) {
            throw new RuntimeException("Error reading file", e);
        }
    }

    @Override
    public void deleteFile(String path) {
        try {
            Path filePath = Paths.get(storageLocation).resolve(path).normalize();
            Files.deleteIfExists(filePath);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting file", e);
        }
    }

    private String getExtension(String filename) {
        if (filename == null || !filename.contains(".")) {
            return "";
        }
        return filename.substring(filename.lastIndexOf("."));
    }

}
