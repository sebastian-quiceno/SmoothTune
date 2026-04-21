package com.group.smoothtune.domain.model;

public class FileResource {

    private final byte[] content;
    private final String fileName;
    private final String contentType;

    public FileResource(byte[] content, String fileName, String contentType) {
        this.content = content;
        this.fileName = fileName;
        this.contentType = contentType;
    }

    public byte[] getContent() { return content; }
    public String getFileName() { return fileName; }
    public String getContentType() { return contentType; }
}
