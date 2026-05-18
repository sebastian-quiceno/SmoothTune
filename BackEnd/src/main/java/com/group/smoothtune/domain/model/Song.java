package com.group.smoothtune.domain.model;

import java.time.LocalDateTime;
import java.util.List;

public class Song {

    private Long id;
    private String title;
    private String imagePath;
    private String audioPath;
    private Float duration;
    private Integer size;
    private LocalDateTime uploadedAt;

    private User uploader;
    private Genre genre;
    private Artist artist;
//    private Long uploaderId;
//    private Long genreId;
//    private Long artistId;
    private List<Long> userSongs;

    public Song(String title, Float duration, Integer size, User uploader, Genre genre, Artist artist) {
        this.uploadedAt = LocalDateTime.now();
        this.title = title;
        this.duration = duration;
        this.size = size;
        this.uploader = uploader;
        this.genre = genre;
        this.artist = artist;
    }

    public Song(Long id, String title, String imagePath, String audioPath, Float duration, Integer size, LocalDateTime uploadedAt, User uploader, Genre genre, Artist artist, List<Long> userSongs) {
        this.id = id;
        this.title = title;
        this.imagePath = imagePath;
        this.audioPath = audioPath;
        this.duration = duration;
        this.size = size;
        this.uploadedAt = uploadedAt;
        this.uploader = uploader;
        this.genre = genre;
        this.artist = artist;
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

    public User getUploader() {
        return uploader;
    }

    public Genre getGenre() {
        return genre;
    }

    public Artist getArtist() {
        return artist;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }

    public List<Long> getUserSongs() {
        return userSongs;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public void setAudioPath(String audioPath) {
        this.audioPath = audioPath;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public void setUploadedAt(LocalDateTime uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    public void setUploader(User uploader) {
        this.uploader = uploader;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
