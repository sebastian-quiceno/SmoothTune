package com.group.smoothtune.infrastructure.persistence.mapper;

import com.group.smoothtune.domain.model.Playlist;
import com.group.smoothtune.infrastructure.persistence.entities.PlaylistEntity;
import com.group.smoothtune.infrastructure.persistence.entities.SongEntity;
import com.group.smoothtune.infrastructure.persistence.entities.UserEntity;

import java.util.List;

public class PlaylistMapper {

    public static PlaylistEntity toEntity(
            Playlist playlist,
            UserEntity owner,
            List<SongEntity> songs
    ) {
        PlaylistEntity entity = new PlaylistEntity();
        entity.setId(playlist.getId());
        entity.setName(playlist.getName());
        entity.setOwner(owner);
        entity.setSongs(songs);
        return entity;
    }

    public static Playlist toDomain(PlaylistEntity entity) {
        return new Playlist(
                entity.getId(),
                entity.getName(),
                entity.getOwner().getId(),
                entity.getSongs()
                        .stream()
                        .map(SongEntity::getId)
                        .toList()
        );
    }
}