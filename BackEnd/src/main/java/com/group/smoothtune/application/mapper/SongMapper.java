package com.group.smoothtune.application.mapper;

import com.group.smoothtune.application.dtos.SongRequestDTO;
import com.group.smoothtune.application.dtos.SongResponseDTO;
import com.group.smoothtune.domain.model.Song;

public class SongMapper {

    public Song toDomain(SongRequestDTO dto,
                         String filePath,
                         String contentType,
                         Float duration,
                         Long size) {
        Song song = new Song(
                dto.getTitle(),
                filePath,
                contentType,
                duration,
                size,
                dto.getArtist(),
                dto.getUploaderId(),
                dto.getGenreId()
        );

        return song;
    }

    public SongResponseDTO toResponse(Song song) {
        return new SongResponseDTO(
                song.getTitle(),
                song.getArtist(),
                song.getUploaderId(),
                song.getGenreId(),
                song.getFilePath(),
                song.getDuration()
        );
    }
}
