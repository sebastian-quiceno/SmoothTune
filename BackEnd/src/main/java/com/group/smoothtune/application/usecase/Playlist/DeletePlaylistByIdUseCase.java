package com.group.smoothtune.application.usecase.Playlist;

import com.group.smoothtune.domain.exception.PlaylistNotFoundException;
import com.group.smoothtune.domain.exception.UserDontHavePermission;
import com.group.smoothtune.domain.model.Playlist;
import com.group.smoothtune.domain.port.PlaylistRepository;

public class DeletePlaylistByIdUseCase {
    private final PlaylistRepository playlistRepository;

    public DeletePlaylistByIdUseCase(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    public void execute(Long userId, Long playlistId){
        Playlist playlist =  playlistRepository.findById(playlistId).orElseThrow(()-> new PlaylistNotFoundException("No se encontro la playlist con el ID: "+playlistId));

        if(!playlist.getOwnerId().equals(userId)){
            throw new UserDontHavePermission("No tienes permiso para eliminar la playlist");
        }

        playlistRepository.deleteById(playlistId);
    }
}
