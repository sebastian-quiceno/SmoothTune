package com.group.smoothtune.domain.model;

public class Artist {
    private Long id;
    private String name;
    private String biography;

    public Artist(String name, String biography) {
        this.name = name;
        this.biography = biography;
    }

    public Artist(Long id, String name, String biography) {
        this.id = id;
        this.name = name;
        this.biography = biography;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBiography() {
        return biography;
    }
}
