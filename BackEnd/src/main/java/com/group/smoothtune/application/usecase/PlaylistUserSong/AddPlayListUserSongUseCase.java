package com.group.smoothtune.application.usecase.PlaylistUserSong;


import com.group.smoothtune.domain.exception.*;
import com.group.smoothtune.domain.model.Playlist;
import com.group.smoothtune.domain.model.PlaylistUserSong;
import com.group.smoothtune.domain.port.*;

public class AddPlayListUserSongUseCase {

    private final PlaylistUserSongRepository playlistUserSongRepository;
    private final PlaylistRepository playlistRepository;
    private final UserSongRepository userSongRepository;

    public AddPlayListUserSongUseCase(PlaylistUserSongRepository playlistUserSongRepository, PlaylistRepository playlistRepository, UserSongRepository userSongRepository) {
        this.playlistUserSongRepository = playlistUserSongRepository;
        this.playlistRepository = playlistRepository;
        this.userSongRepository = userSongRepository;
    }

    public PlaylistUserSong execute(Long playlistId, Long userSongId) {

        Playlist playlist = playlistRepository.findById(playlistId).orElseThrow(()-> new PlaylistNotFoundException("No se encontro la playlist con el ID: "+playlistId));
        userSongRepository.findById(userSongId).orElseThrow(()-> new UserSongNotFoundException("No se encontro la cancion de usuario con el ID: "+userSongId));

        boolean alreadyExists = playlistUserSongRepository
                .existsByPlaylistIdAndUserSongId(playlistId, userSongId);

        if (alreadyExists) {
            throw new SongAlreadySaveException("La cancion ya esta guardada en la playlist");
        }

        PlaylistUserSong newPlaylistUserSong = new PlaylistUserSong(playlist.getPlaylistSongs().toArray().length, playlistId, userSongId);

        return playlistUserSongRepository.save(newPlaylistUserSong);
    }
}
