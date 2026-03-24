package com.group.smoothtune.model.mappers;

import com.group.smoothtune.application.dtos.UserRequestDTO;
import com.group.smoothtune.application.dtos.UserResponseDTO;
import com.group.smoothtune.infrastructure.persistence.entity.UserEntity;
import com.group.smoothtune.infrastructure.persistence.entity.UserSongEntity;

public class UserMapper {

    public static UserEntity toEntity(UserRequestDTO user, String encodedPassword) {
        UserEntity entity = new UserEntity();
        entity.setEmail(user.getEmail());
        entity.setUsername(user.getUsername());
        entity.setPassword(encodedPassword);
        return entity;
    }

    public static UserResponseDTO toResponse(UserEntity entity) {
        return new UserResponseDTO(
                entity.getEmail(),
                entity.getUsername(),
                entity.getSavedSongs().
                        stream().
                        map(UserSongEntity::getId).
                        toList()
        );
    }

}
