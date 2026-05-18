package com.group.smoothtune.application.usecase.UserSong;

import com.group.smoothtune.domain.model.UserSong;
import com.group.smoothtune.domain.port.UserSongRepository;

import java.util.List;

public class GetMostPlayedUserSongsUseCase {
    private final UserSongRepository userSongRepository;

    public GetMostPlayedUserSongsUseCase(UserSongRepository userSongRepository) {
        this.userSongRepository = userSongRepository;
    }

    public List<UserSong> execute(Long userId){
        return userSongRepository.getTenMostPlayed(userId);
    }
}
