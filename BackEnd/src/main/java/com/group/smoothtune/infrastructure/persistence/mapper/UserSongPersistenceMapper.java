package com.group.smoothtune.infrastructure.persistence.mapper;

import com.group.smoothtune.domain.model.UserSong;
import com.group.smoothtune.infrastructure.persistence.entity.PlaylistUserSongEntity;
import com.group.smoothtune.infrastructure.persistence.entity.UserSongEntity;

public class UserSongPersistenceMapper {

    public static UserSong toDomain(UserSongEntity entity) {
        UserSong userSong = new UserSong(
                entity.getId(),
                entity.getSavedAt(),
                entity.getUser().getId(),
                entity.getSong().getId(),
                entity.getPlaylistSongs().stream().map(PlaylistUserSongEntity::getId).toList()

        );

        return userSong;
    }

    public static UserSongEntity toEntity(UserSong userSong) {
        UserSongEntity entity = new UserSongEntity();

        entity.setId(userSong.getId());
        entity.setSavedAt(userSong.getSavedAt());

        return entity;
    }
}
