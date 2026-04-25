package com.group.smoothtune.adapter.out.persistence.mapper;

import com.group.smoothtune.domain.model.Playlist;
import com.group.smoothtune.adapter.out.persistence.entity.PlaylistEntity;
import com.group.smoothtune.adapter.out.persistence.entity.PlaylistUserSongEntity;

public class PlaylistPersistenceMapper {
    public static Playlist toDomain(PlaylistEntity entity) {
        Playlist playlist = new Playlist(
                entity.getId(),
                entity.getName(),
                entity.getOwner().getId(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.getPlaylistSongs().stream().map(PlaylistUserSongEntity::getId).toList()
        );

        return playlist;
    }

    public static PlaylistEntity toEntity(Playlist playlist) {
        PlaylistEntity entity = new PlaylistEntity();

        entity.setId(playlist.getId());
        entity.setName(playlist.getName());
        entity.setCreatedAt(playlist.getCreatedAt());
        entity.setUpdatedAt(playlist.getUpdatedAt());

        return entity;
    }
}
