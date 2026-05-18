package com.group.smoothtune.adapter.in.rest.mapper;

import com.group.smoothtune.adapter.in.rest.dtos.response.GenreResponseDTO;
import com.group.smoothtune.domain.model.Genre;

public class GenreMapper {

    public static GenreResponseDTO toResponse(Genre genre){
        return new GenreResponseDTO(
                genre.getId(),
                genre.getName(),
                genre.getDescription()
        );
    }
}
