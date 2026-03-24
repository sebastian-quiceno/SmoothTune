package com.group.smoothtune.application.mapper;

import com.group.smoothtune.application.dtos.SongRequestDTO;
import com.group.smoothtune.application.dtos.SongResponseDTO;
import com.group.smoothtune.domain.model.Song;
import com.group.smoothtune.infrastructure.persistence.entity.GenreEntity;
import com.group.smoothtune.infrastructure.persistence.entity.SongEntity;
import com.group.smoothtune.infrastructure.persistence.entity.UserEntity;

public class SongMapper {

    public Song toDomain(SongRequestDTO dto,
                         String filePath,
                         String contentType,
                         Long size) {
        Song song = new SongEntity();

        song.setTitle(dto.getTitle());
        song.setArtist(dto.getArtist());
        song.setUploader(dto.getUploaderId());
        song.setGenre(dto.getGenreId());
        song.setUrl(fileUrl);
        song.setContentType(contentType);
        song.setSize(size);

        // duración podrías calcularla después con una librería de audio
        song.setDuration(null);

        return song;
    }

    public SongResponseDTO toResponse(SongEntity song) {
        return new SongResponseDTO(
                song.getTitle(),
                song.getArtist(),
                song.getUploader().getId(),
                song.getGenre().getId(),
                song.getUrl(),
                song.getDuration()
        );
    }
}
