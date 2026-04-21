package com.group.smoothtune.application.dtos;

public class SongRequestDTO {

    private String title;
    private Float duration;
    private String artist;

    private Long userId;
    private Long genreId;

    public String getTitle() {
        return title;
    }

    public Float getDuration() {
        return duration;
    }

    public String getArtist() {
        return artist;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getGenreId() {
        return genreId;
    }
}
