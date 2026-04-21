package com.group.smoothtune.application.dtos;

public class GenreRequestDTO {

    private String name;
    private String description;

    public GenreRequestDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
