package com.group.smoothtune.application.usecase.UserSong;

import com.group.smoothtune.domain.model.UserSong;
import com.group.smoothtune.domain.port.UserSongRepository;

import java.util.List;

public class GetUserSongsUseCase {
    private final UserSongRepository userSongRepository;

    public GetUserSongsUseCase(UserSongRepository userSongRepository) {
        this.userSongRepository = userSongRepository;
    }

    public List<UserSong> execute(Long userId){
        return userSongRepository.findAllByUserId(userId);
    }
}
