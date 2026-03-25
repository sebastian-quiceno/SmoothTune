package com.group.smoothtune.application.mapper;

import com.group.smoothtune.application.dtos.PlaylistRequestDTO;
import com.group.smoothtune.application.dtos.PlaylistResponseDTO;
import com.group.smoothtune.domain.model.Playlist;

public class PlaylistMapper {

    public static Playlist toDomain(PlaylistRequestDTO dto) {
        Playlist playlist = new Playlist(dto.getName(), dto.getOwnerId());

        return playlist;
    }

    public static PlaylistResponseDTO toResponse(Playlist playlist) {
        return new PlaylistResponseDTO(
                playlist.getId(),
                playlist.getName(),
                playlist.getOwnerId(),
                playlist.getCreatedAt(),
                playlist.getUpdatedAt()
        );
    }
}