package com.group.smoothtune.infrastructure.persistance.mappers;

import com.group.smoothtune.domain.model.User;
import com.group.smoothtune.infrastructure.persistance.entities.UserEntity;

public class UserMapper {

    public static User toDomain(UserEntity entity) {
        return new User(
                entity.getId(),
                entity.getEmail(),
                entity.getUsername()
        );
    }

    public static UserEntity toEntity(User user, String encodedPassword) {
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setEmail(user.getEmail());
        entity.setUsername(user.getUsername());
        entity.setPassword(encodedPassword);
        return entity;
    }

}
