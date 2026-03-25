package com.group.smoothtune.infrastructure.persistence.mapper;

import com.group.smoothtune.domain.model.User;
import com.group.smoothtune.infrastructure.persistence.entity.PlaylistEntity;
import com.group.smoothtune.infrastructure.persistence.entity.SongEntity;
import com.group.smoothtune.infrastructure.persistence.entity.UserEntity;
import com.group.smoothtune.infrastructure.persistence.entity.UserSongEntity;

public class UserPersistenceMapper {

    public static User toDomain(UserEntity entity) {
        User user = new User(
                entity.getId(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getUsername(),
                entity.isEnabled(),
                entity.getSavedSongs().stream().map(UserSongEntity::getId).toList(),
                entity.getUploadedSongs().stream().map(SongEntity::getId).toList(),
                entity.getPlaylists().stream().map(PlaylistEntity::getId).toList()
        );

        return user;
    }

    public static UserEntity toEntity(User user) {
        UserEntity entity = new UserEntity();

        entity.setId(user.getId());
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());
        entity.setUsername(user.getUsername());
        entity.setEnabled(user.isEnabled());

        return entity;
    }
}
