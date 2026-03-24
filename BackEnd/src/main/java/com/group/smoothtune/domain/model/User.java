package com.group.smoothtune.domain.model;

import java.util.List;

public class User {

    private Long id;
    private String email;
    private String password;
    private String username;
    private boolean enabled;

    private List<Long> savedSongs;
    private List<Long> uploadedSongs;
    private List<Long> userPlaylists;

    public User(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.enabled = true;
    }

    public User(Long id, String email, String password, String username, boolean enabled, List<Long> savedSongs, List<Long> uploadedSongs, List<Long> userPlaylists) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.enabled = enabled;
        this.savedSongs = savedSongs;
        this.uploadedSongs = uploadedSongs;
        this.userPlaylists = userPlaylists;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public List<Long> getSavedSongs() {
        return savedSongs;
    }

    public List<Long> getUploadedSongs() {
        return uploadedSongs;
    }

    public List<Long> getUserPlaylists() {
        return userPlaylists;
    }

    public void setId(Long id){
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserPlaylists(List<Long> userPlaylists) {
        this.userPlaylists = userPlaylists;
    }
}
