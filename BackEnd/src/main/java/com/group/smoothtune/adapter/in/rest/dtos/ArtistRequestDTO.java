package com.group.smoothtune.adapter.in.rest.dtos;

public class ArtistRequestDTO {
    private String name;
    private String biography;

    public ArtistRequestDTO(String name, String biography) {
        this.name = name;
        this.biography = biography;
    }

    public String getName() {
        return name;
    }

    public String getBiography() {
        return biography;
    }
}
