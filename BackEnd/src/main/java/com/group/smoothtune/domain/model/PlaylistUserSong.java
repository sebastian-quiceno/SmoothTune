package com.group.smoothtune.domain.model;

import java.time.LocalDateTime;

public class PlaylistUserSong {

    private Long id;
    private Integer position; // opcional (orden)

    private LocalDateTime addedAt;

    private Long playlistId;
    private Long userSongId;

    public PlaylistUserSong(Integer position, Long playlistId, Long userSongId) {
        this.position = position;
        this.addedAt = LocalDateTime.now();
        this.playlistId = playlistId;
        this.userSongId = userSongId;
    }

    public PlaylistUserSong(Long id, Integer position, LocalDateTime addedAt, Long playlistId, Long userSongId) {
        this.id = id;
        this.position = position;
        this.addedAt = addedAt;
        this.playlistId = playlistId;
        this.userSongId = userSongId;
    }

    public Long getId() {
        return id;
    }

    public Integer getPosition() {
        return position;
    }

    public LocalDateTime getAddedAt() {
        return addedAt;
    }

    public Long getPlaylistId() {
        return playlistId;
    }

    public Long getUserSongId() {
        return userSongId;
    }

}
