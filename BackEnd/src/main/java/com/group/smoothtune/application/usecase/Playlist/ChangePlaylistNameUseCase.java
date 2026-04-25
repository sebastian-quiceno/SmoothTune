package com.group.smoothtune.application.usecase.Playlist;

import com.group.smoothtune.domain.exception.PlaylistNotFoundException;
import com.group.smoothtune.domain.exception.AccessDeniedException;
import com.group.smoothtune.domain.model.Playlist;
import com.group.smoothtune.domain.port.PlaylistRepository;

public class ChangePlaylistNameUseCase {
    private final PlaylistRepository playlistRepository;

    public ChangePlaylistNameUseCase(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    public void execute(Long userId, Long playlistId, String newName){
        Playlist playlist =  playlistRepository.findById(playlistId).orElseThrow(()-> new PlaylistNotFoundException("No se encontro la playlist con el ID: "+playlistId));

        if(!playlist.getOwnerId().equals(userId)){
            throw new AccessDeniedException("No tienes permiso para eliminar la playlist");
        }

        playlist.setName(newName);

        playlistRepository.save(playlist);
    }
}
