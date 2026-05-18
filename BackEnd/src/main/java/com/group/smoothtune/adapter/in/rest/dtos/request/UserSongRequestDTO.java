package com.group.smoothtune.adapter.in.rest.dtos.request;

public class UserSongRequestDTO {

    private Long userId;
    private Long songId;

    public UserSongRequestDTO(Long userId, Long songId) {
        this.userId = userId;
        this.songId = songId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getSongId() {
        return songId;
    }
}
