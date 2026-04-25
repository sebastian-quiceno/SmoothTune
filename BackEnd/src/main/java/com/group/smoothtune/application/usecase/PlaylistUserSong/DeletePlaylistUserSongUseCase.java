package com.group.smoothtune.application.usecase.PlaylistUserSong;

import com.group.smoothtune.domain.exception.*;
import com.group.smoothtune.domain.model.Playlist;
import com.group.smoothtune.domain.model.PlaylistUserSong;
import com.group.smoothtune.domain.port.PlaylistRepository;
import com.group.smoothtune.domain.port.PlaylistUserSongRepository;

public class DeletePlaylistUserSongUseCase {

    private final PlaylistUserSongRepository playlistUserSongRepository;
    private final PlaylistRepository playlistRepository;

    public DeletePlaylistUserSongUseCase(PlaylistUserSongRepository playlistUserSongRepository, PlaylistRepository playlistRepository) {
        this.playlistUserSongRepository = playlistUserSongRepository;
        this.playlistRepository = playlistRepository;
    }

    public void execute(Long userId, Long playlistUserSongId) {

        PlaylistUserSong item = playlistUserSongRepository.findById(playlistUserSongId)
                .orElseThrow(() -> new PlaylistUserSongNotFoundException("No se encontro la cancion en la playlist (playlistUserSong) con el ID: "+playlistUserSongId));

        Playlist playlist = playlistRepository.findById(item.getPlaylistId())
                .orElseThrow(() -> new PlaylistNotFoundException("No se encontro la playlist con el ID: "+item.getPlaylistId()));

        if (!playlist.getOwnerId().equals(userId)) {
            throw new AccessDeniedException("No tienes permiso para eliminar la cancion de la playlist");
        }

        playlistUserSongRepository.deleteById(playlistUserSongId);
    }
}
