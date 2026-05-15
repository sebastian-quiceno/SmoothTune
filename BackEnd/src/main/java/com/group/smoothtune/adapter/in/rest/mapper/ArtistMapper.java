package com.group.smoothtune.adapter.in.rest.mapper;

import com.group.smoothtune.adapter.in.rest.dtos.ArtistResponseDTO;
import com.group.smoothtune.domain.model.Artist;

public class ArtistMapper {
    public static ArtistResponseDTO toRespose(Artist artist){
        return new ArtistResponseDTO(
                artist.getId(),
                artist.getName(),
                artist.getBiography()
        );
    }
}
