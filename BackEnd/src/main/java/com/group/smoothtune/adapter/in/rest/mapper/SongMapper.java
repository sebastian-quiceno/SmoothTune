package com.group.smoothtune.adapter.in.rest.mapper;

import com.group.smoothtune.adapter.in.rest.dtos.SongResponseDTO;
import com.group.smoothtune.domain.model.Song;

public class SongMapper {

    public static SongResponseDTO toResponse(Song song) {
        return new SongResponseDTO(
                song.getTitle(),
                song.getArtistId(),
                song.getUploaderId(),
                song.getGenreId(),
                song.getDuration()
        );
    }
}
