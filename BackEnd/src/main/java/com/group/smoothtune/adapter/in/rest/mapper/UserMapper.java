package com.group.smoothtune.adapter.in.rest.mapper;

import com.group.smoothtune.adapter.in.rest.dtos.UserRequestDTO;
import com.group.smoothtune.adapter.in.rest.dtos.UserResponseDTO;
import com.group.smoothtune.domain.model.User;

//REVISAR
public class UserMapper {
    public static User toDomain(UserRequestDTO dto, String encodedPassword) {
        return new User(
                dto.getEmail(),
                encodedPassword,
                dto.getUsername()
        );
    }

    public static UserResponseDTO toResponse(User user) {
        return new UserResponseDTO(
                user.getEmail(),
                user.getUsername(),
                user.getSavedSongs(),
                user.getUploadedSongs(),
                user.getUserPlaylists()
        );
    }
}
