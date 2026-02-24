package com.group.smoothtune.domain.model;

import java.util.List;
import java.util.UUID;

public class Playlist {

    private Long id;
    private String name;
    private Long ownerId;
    private List<Long> songIds;

    public Playlist(
            Long id,
            String name,
            Long ownerId,
            List<Long> songIds
    ) {
        this.id = id;
        this.name = name;
        this.ownerId = ownerId;
        this.songIds = songIds;
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