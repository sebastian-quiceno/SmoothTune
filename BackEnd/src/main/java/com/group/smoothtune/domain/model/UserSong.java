package com.group.smoothtune.domain.model;

import java.time.LocalDateTime;
import java.util.List;

public class UserSong {

    private Long id;
    private Integer timesPlayed;
    private LocalDateTime savedAt;

    private User user;
    private Song song;

    private List<Long> playlistUserSongs;

    public UserSong(User user, Song song) {
        this.savedAt = LocalDateTime.now();
        this.user = user;
        this.song = song;
        this.timesPlayed = 0;
    }

    public UserSong(Long id, Integer timesPlayed, LocalDateTime savedAt, User user, Song song, List<Long> playlistUserSongs) {
        this.id = id;
        this.timesPlayed = timesPlayed;
        this.savedAt = savedAt;
        this.user = user;
        this.song = song;
        this.playlistUserSongs = playlistUserSongs;
    }

    public Long getId() {
        return id;
    }

    public Integer getTimesPlayed(){
        return timesPlayed;
    }

    public LocalDateTime getSavedAt() {
        return savedAt;
    }

    public User getUser() {
        return user;
    }

    public Song getSong() {
        return song;
    }

    public List<Long> getPlaylistUserSongs() {
        return playlistUserSongs;
    }

    public void setTimesPlayed(Integer timesPlayed) {
        this.timesPlayed = timesPlayed;
    }
}
