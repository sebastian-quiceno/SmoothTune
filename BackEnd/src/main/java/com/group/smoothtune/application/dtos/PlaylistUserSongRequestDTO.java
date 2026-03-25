package com.group.smoothtune.application.dtos;

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
