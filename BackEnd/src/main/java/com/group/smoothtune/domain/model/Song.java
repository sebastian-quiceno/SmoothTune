package com.group.smoothtune.domain.model;

import java.util.List;

public class Song {

    private Long id;
    private String title;
    private String imagePath;
    private String audioPath;
    private Float duration;
    private Integer size;
    private String artist;

    private Long uploaderId;
    private Long genreId;
    private List<Long> userSongs;

    public Song(String title, Float duration, Integer size, String artist, Long uploaderId, Long genreId) {
        this.title = title;
        this.duration = duration;
        this.size = size;
        this.artist = artist;
        this.uploaderId = uploaderId;
        this.genreId = genreId;
    }

    public Song(Long id, String title, String imagePath, String audioPath, Float duration, Integer size, String artist, Long uploaderId, Long genreId, List<Long> userSongs) {
        this.id = id;
        this.title = title;
        this.imagePath = imagePath;
        this.audioPath = audioPath;
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

    public String getImagePath() {
        return imagePath;
    }

    public String getAudioPath() {
        return audioPath;
    }

    public Float getDuration() {
        return duration;
    }

    public Integer getSize() {
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

    public void setDuration(Float duration) {
        this.duration = duration;
    }

    public void setUserSongs(List<Long> userSongs) {
        this.userSongs = userSongs;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setUploaderId(Long uploaderId) {
        this.uploaderId = uploaderId;
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }

    public void setAudioPath(String audioPath) {
        this.audioPath = audioPath;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
