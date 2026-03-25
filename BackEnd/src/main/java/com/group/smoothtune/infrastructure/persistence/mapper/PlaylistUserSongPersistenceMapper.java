package com.group.smoothtune.infrastructure.persistence.mapper;

import com.group.smoothtune.domain.model.PlaylistUserSong;
import com.group.smoothtune.infrastructure.persistence.entity.PlaylistUserSongEntity;

public class PlaylistUserSongPersistenceMapper {
    public static PlaylistUserSong toDomain(PlaylistUserSongEntity entity) {
        PlaylistUserSong playlistUserSong = new PlaylistUserSong(
                entity.getId(),
                entity.getPosition(),
                entity.getAddedAt(),
                entity.getPlaylist().getId(),
                entity.getUserSong().getId()
        );

        return playlistUserSong;
    }

    public static PlaylistUserSongEntity toEntity(PlaylistUserSong playlistUserSong) {
        PlaylistUserSongEntity entity = new PlaylistUserSongEntity();

        entity.setId(playlistUserSong.getId());
        entity.setPosition(playlistUserSong.getPosition());
        entity.setAddedAt(playlistUserSong.getAddedAt());

        return entity;
    }
}
