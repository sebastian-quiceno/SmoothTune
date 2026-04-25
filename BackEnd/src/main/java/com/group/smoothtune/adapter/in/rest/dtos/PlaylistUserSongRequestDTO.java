package com.group.smoothtune.adapter.in.rest.dtos;

public class PlaylistUserSongRequestDTO {
    private Integer position;
    private Long playlistId;
    private Long userSongId;

    public Long getPlaylistId() {
        return playlistId;
    }

    public Long getUserSongId() {
        return userSongId;
    }

    public Integer getPosition() {
        return position;
    }
}
