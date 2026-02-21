package com.group.smoothtune.domain.model;

import java.util.List;

public class Playlist {
    private Long id;
    private String name;
    private Long ownerId;
    private List<Long> songIds;

    public Playlist(Long id, String name, Long ownerId, List<Long> songIds) {
        this.id = id;
        this.name = name;
        this.ownerId = ownerId;
        this.songIds = songIds;
    }

    public void addSong(Long songId) {
        if (!songIds.contains(songId)) {
            songIds.add(songId);
        }
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

    public List<Long> getSongIds() {
        return songIds;
    }
}
