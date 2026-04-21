package com.group.smoothtune.domain.model;

public class UploadResult {

    private String key;
    private Float duration;
    private Integer size;

    public UploadResult(String key, Float duration, int size) {
        this.key = key;
        this.duration = duration;
        this.size = size;
    }

    public String getKey() {
        return key;
    }

    public Float getDuration() {
        return duration;
    }

    public Integer getSize() {
        return size;
    }
}
