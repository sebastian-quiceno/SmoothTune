package com.group.smoothtune.domain.port;

import com.group.smoothtune.domain.model.FileResource;
import com.group.smoothtune.domain.model.UploadResult;

import java.io.IOException;

public interface FileStoragePort {

    //TODO: Intentar usar InputStream, porque MultipartFile es solo de sprint
    UploadResult uploadSong(FileResource file) throws IOException;

    String uploadImage(FileResource file) throws IOException;

    String generatePresignedUrl(String key);

    byte[] downloadFile(String key);

    void deleteFile(String key);

}
