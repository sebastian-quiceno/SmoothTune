package com.group.smoothtune.application.usecase.UserSong;


import com.group.smoothtune.domain.exception.SongAlreadySaveException;
import com.group.smoothtune.domain.exception.SongNotFoundException;
import com.group.smoothtune.domain.exception.UserNotFoundException;
import com.group.smoothtune.domain.model.UserSong;
import com.group.smoothtune.domain.port.SongRepository;
import com.group.smoothtune.domain.port.UserRepository;
import com.group.smoothtune.domain.port.UserSongRepository;

public class AddUserSongUseCase {

    private final UserSongRepository userSongRepository;
    private final UserRepository userRepository;
    private final SongRepository songRepository;

    public AddUserSongUseCase(UserSongRepository userSongRepository, UserRepository userRepository, SongRepository songRepository) {
        this.userSongRepository = userSongRepository;
        this.userRepository = userRepository;
        this.songRepository = songRepository;
    }

    public UserSong execute(Long userId, Long songId) {

        userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("No se encontro el usuario con el ID: "+userId));
        songRepository.findById(songId).orElseThrow(()->new SongNotFoundException("No se encontro la cancion con el ID: "+songId));

        boolean alreadyExists = userSongRepository
                .existsByUserIdAndSongId(userId, songId);

        if (alreadyExists) {
            throw new SongAlreadySaveException("La cancion ya esta guardada");
        }

        UserSong userSong = new UserSong(userId, songId);

        return userSongRepository.save(userSong);
    }
}
