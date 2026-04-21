package com.group.smoothtune.application.mapper;

import com.group.smoothtune.application.dtos.SongResponseDTO;
import com.group.smoothtune.domain.model.Song;

public class SongMapper {

    public static SongResponseDTO toResponse(Song song) {
        return new SongResponseDTO(
                song.getTitle(),
                song.getArtist(),
                song.getUploaderId(),
                song.getGenreId(),
                song.getAudioPath(),
                song.getDuration()
        );
    }
}
