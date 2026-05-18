package com.group.smoothtune.adapter.in.rest.mapper;

import com.group.smoothtune.adapter.in.rest.dtos.response.UserSongResponseDTO;
import com.group.smoothtune.domain.model.UserSong;

public class UserSongMapper {
    public static UserSongResponseDTO toResponse(UserSong userSong){
        return new UserSongResponseDTO(
                userSong.getId(),
                userSong.getUser().getUsername(),
                SongMapper.toResponse(userSong.getSong()),
                userSong.getTimesPlayed(),
                userSong.getSavedAt()
                );
    }
}
