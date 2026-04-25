package com.group.smoothtune.adapter.in.rest.dtos;

public class UserRequestDTO {

    private String email;
    private String username;

    public UserRequestDTO(String email, String username) {
        this.email = email;
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }
}