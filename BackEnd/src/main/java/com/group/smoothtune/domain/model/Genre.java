package com.group.smoothtune.domain.model;

public class Genre {

    private Long id;
    private String name;
    private String description;

    public Genre(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Genre(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
