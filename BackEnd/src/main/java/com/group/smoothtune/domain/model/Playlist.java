package com.group.smoothtune.domain.model;

import java.time.LocalDateTime;
import java.util.List;

public class Playlist {

    private Long id;
    private String name;
    private Long ownerId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private List<Long> playlistSongs;

    public Playlist(String name, Long ownerId) {
        this.name = name;
        this.ownerId = ownerId;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Playlist(Long id, String name, Long ownerId, LocalDateTime createdAt, LocalDateTime updatedAt, List<Long> playlistSongs) {
        this.id = id;
        this.name = name;
        this.ownerId = ownerId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.playlistSongs = playlistSongs;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public List<Long> getPlaylistSongs() {
        return playlistSongs;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}



