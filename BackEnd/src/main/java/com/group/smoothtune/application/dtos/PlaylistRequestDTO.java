package com.group.smoothtune.application.dtos;

import java.util.List;

public class PlaylistRequestDTO {

    private String name;
    private Long ownerId;
    private List<Long> songIds;


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