package com.group.smoothtune.application.usecase.Playlist;

import com.group.smoothtune.domain.exception.UserNotFoundException;
import com.group.smoothtune.domain.model.Playlist;
import com.group.smoothtune.domain.port.PlaylistRepository;
import com.group.smoothtune.domain.port.UserRepository;

public class CreatePlaylistUseCase {
    private final PlaylistRepository playlistRepository;
    private final UserRepository userRepository;

    public CreatePlaylistUseCase(PlaylistRepository playlistRepository, UserRepository userRepository) {
        this.playlistRepository = playlistRepository;
        this.userRepository = userRepository;
    }

    public Playlist execute(Long userId, String name){

        userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(
                        "No se encontro el usuario con el ID: " + userId));

        Playlist newPlaylist = new Playlist(name, userId);

        return playlistRepository.save(newPlaylist);
    }
}
