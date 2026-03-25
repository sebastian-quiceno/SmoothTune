package com.group.smoothtune.domain.model;

public class Song {
    private Long id;
    private String title;
    private String artist;
    private String album;
    private String genre;


    public Song(Long id, String title, String artist, String album, String genre) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.genre = genre;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getArtist() { return artist; }
    public String getAlbum() { return album; }
    public String getGenre() { return genre; }
}
