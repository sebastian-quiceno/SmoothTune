package com.group.smoothtune.adapter.in.rest.mapper;

import com.group.smoothtune.adapter.in.rest.dtos.GenreResponseDTO;
import com.group.smoothtune.domain.model.Genre;

public class GenreMapper {

    public static GenreResponseDTO toResponse(Genre genre){
        return new GenreResponseDTO(
                genre.getName(),
                genre.getDescription()
        );
    }
}
