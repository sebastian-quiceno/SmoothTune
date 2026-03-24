package com.group.smoothtune.domain.port;

import java.io.InputStream;

public interface FileStoragePort {

    String saveFile(InputStream inputStream, String filename, String contentType);

    InputStream getFile(String path);

    void deleteFile(String path);
}
