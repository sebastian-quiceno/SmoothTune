package com.group.smoothtune.application.mapper;

import com.group.smoothtune.application.dtos.GenreResponseDTO;
import com.group.smoothtune.domain.model.Genre;

public class GenreMapper {

    public static GenreResponseDTO toResponse(Genre genre){
        return new GenreResponseDTO(
                genre.getName(),
                genre.getDescription()
        );
    }
}
