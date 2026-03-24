package com.group.smoothtune.application.dtos;

public class SongRequestDTO {

    private String title;
    private String artist;
    private String album;
    private Long uploaderId;
    private Long genreId;


    public Long getUploaderId() {
        return uploaderId;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public Long getGenreId() {
        return genreId;
    }
}
