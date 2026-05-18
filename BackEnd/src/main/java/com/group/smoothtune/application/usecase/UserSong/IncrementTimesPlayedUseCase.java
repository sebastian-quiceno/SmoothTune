package com.group.smoothtune.application.usecase.UserSong;

import com.group.smoothtune.domain.exception.UserSongNotFoundException;
import com.group.smoothtune.domain.model.User;
import com.group.smoothtune.domain.model.UserSong;
import com.group.smoothtune.domain.port.UserSongRepository;

public class IncrementTimesPlayedUseCase {
    private final UserSongRepository userSongRepository;

    public IncrementTimesPlayedUseCase(UserSongRepository userSongRepository) {
        this.userSongRepository = userSongRepository;
    }

    public UserSong execute(Long userSongId){
        UserSong userSong = userSongRepository.findById(userSongId).orElseThrow(()-> new UserSongNotFoundException("No se encontro la cancion de usuario con id: "+userSongId));
        Integer timesPlayed = userSong.getTimesPlayed();
        userSong.setTimesPlayed(timesPlayed+1);
        return userSongRepository.save(userSong);
    }
}
