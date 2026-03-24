package com.group.smoothtune.application.mapper;

import com.group.smoothtune.application.dtos.PlaylistRequestDTO;
import com.group.smoothtune.application.dtos.PlaylistResponseDTO;
import com.group.smoothtune.infrastructure.persistence.entity.PlaylistEntity;
import com.group.smoothtune.infrastructure.persistence.entity.SongEntity;
import com.group.smoothtune.infrastructure.persistence.entity.UserEntity;

import java.util.List;

public class PlaylistMapper {

    public static PlaylistEntity toEntity(
            PlaylistRequestDTO playlistRequestDTO,
            UserEntity owner,
            List<SongEntity> songs
    ) {
        PlaylistEntity entity = new PlaylistEntity();
        entity.setName(playlistRequestDTO.getName());
        entity.setOwner(owner);
        entity.setSongs(songs);
        return entity;
    }

    public static PlaylistResponseDTO toResponse(PlaylistEntity entity) {
        return new PlaylistResponseDTO(
                entity.getId(),
                entity.getName(),
                entity.getOwner().getId(),
                entity.getSongs()
                        .stream()
                        .map(SongEntity::getId)
                        .toList(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}