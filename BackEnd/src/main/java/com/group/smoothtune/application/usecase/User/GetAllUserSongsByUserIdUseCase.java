package com.group.smoothtune.application.usecase.User;


import com.group.smoothtune.domain.exception.SongNotFoundException;
import com.group.smoothtune.domain.exception.UserNotFoundException;
import com.group.smoothtune.domain.model.Song;
import com.group.smoothtune.domain.model.User;
import com.group.smoothtune.domain.port.SongRepository;
import com.group.smoothtune.domain.port.UserRepository;

import java.util.List;

public class GetAllUserSongsByUserIdUseCase {

    private final UserRepository userRepository;
    private final SongRepository songRepository;

    public GetAllUserSongsByUserIdUseCase(UserRepository userRepository, SongRepository songRepository) {
        this.userRepository = userRepository;
        this.songRepository = songRepository;
    }

    public List<Song> execute(Long userId) {

        User user = userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("No se encontro el usuario con el ID: "+userId));

        List<Song> saveSongs = user.getSavedSongs().stream()
                .map(songId -> songRepository.findById(songId)
                        .orElseThrow(() -> new SongNotFoundException("No se encontro la cancion con el ID: " + songId)))
                .toList();

        return saveSongs;
    }
}
