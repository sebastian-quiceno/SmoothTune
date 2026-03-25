package com.group.smoothtune.domain.model;

import java.time.LocalDateTime;
import java.util.List;

public class UserSong {

    private Long id;

    private LocalDateTime savedAt;

    private Long userId;
    private Long songId;

    private List<Long> playlistUserSongs;

    public UserSong(Long userId, Long songId) {;
        this.savedAt = LocalDateTime.now();
        this.userId = userId;
        this.songId = songId;
    }

    public UserSong(Long id, LocalDateTime savedAt, Long userId, Long songId, List<Long> playlistUserSongs) {
        this.id = id;
        this.savedAt = savedAt;
        this.userId = userId;
        this.songId = songId;
        this.playlistUserSongs = playlistUserSongs;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getSavedAt() {
        return savedAt;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getSongId() {
        return songId;
    }

    public List<Long> getPlaylistUserSongs() {
        return playlistUserSongs;
    }
}
