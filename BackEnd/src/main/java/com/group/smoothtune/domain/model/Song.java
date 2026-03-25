package com.group.smoothtune.domain.model;

import java.util.List;

public class Song {

    private Long id;
    private String title;
    private String filePath;
    private String contentType;
    private Float duration;
    private Long size;
    private String artist;

    private Long uploaderId;
    private Long genreId;

    private List<Long> userSongs;

    public Song(String title, String filePath, String contentType, Float duration, Long size, String artist, Long uploaderId, Long genreId) {
        this.title = title;
        this.filePath = filePath;
        this.contentType = contentType;
        this.duration = duration;
        this.size = size;
        this.artist = artist;
        this.uploaderId = uploaderId;
        this.genreId = genreId;
    }

    public Song(Long id, String title, String filePath, String contentType, Float duration, Long size, String artist, Long uploaderId, Long genreId, List<Long> userSongs) {
        this.id = id;
        this.title = title;
        this.filePath = filePath;
        this.contentType = contentType;
        this.duration = duration;
        this.size = size;
        this.artist = artist;
        this.uploaderId = uploaderId;
        this.genreId = genreId;
        this.userSongs = userSongs;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getFilePath() {
        return filePath;
    }


    public String getContentType() {
        return contentType;
    }

    public Float getDuration() {
        return duration;
    }

    public Long getSize() {
        return size;
    }

    public String getArtist() {
        return artist;
    }

    public Long getUploaderId() {
        return uploaderId;
    }

    public Long getGenreId() {
        return genreId;
    }

    public List<Long> getUserSongs() {
        return userSongs;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setUploaderId(Long uploaderId) {
        this.uploaderId = uploaderId;
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setSize(Long size) {
        this.size = size;
    }
}
