package com.group.smoothtune.adapter.in.rest.mapper;

import com.group.smoothtune.adapter.in.rest.dtos.response.SongResponseDTO;
import com.group.smoothtune.domain.model.Song;

public class SongMapper {

    public static SongResponseDTO toResponse(Song song) {
        return new SongResponseDTO(
                song.getId(),
                song.getTitle(),
                song.getArtist(),
                song.getUploader().getUsername(),
                song.getGenre(),
                song.getDuration(),
                song.getSize(),
                song.getUploadedAt()
        );
    }
}
