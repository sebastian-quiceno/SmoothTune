package com.group.smoothtune.application.mapper;

import com.group.smoothtune.application.dtos.PlaylistUserSongResponseDTO;
import com.group.smoothtune.infrastructure.persistence.entity.PlaylistEntity;
import com.group.smoothtune.infrastructure.persistence.entity.PlaylistUserSongEntity;
import com.group.smoothtune.infrastructure.persistence.entity.UserSongEntity;

public class PlaylistUserSongMapper {

    public PlaylistUserSongEntity toEntity(PlaylistEntity playlist, UserSongEntity userSong){
        PlaylistUserSongEntity playlistUser = new PlaylistUserSongEntity();

        playlistUser.setPlaylist(playlist);
        playlistUser.setUserSong(userSong);

        playlistUser.setPosition(playlist.getPlaylistSongs().size());

        return playlistUser;
    }

    public PlaylistUserSongResponseDTO toResponse(PlaylistUserSongEntity playlistUserSong){
        return new PlaylistUserSongResponseDTO(
                playlistUserSong.getId(),
                playlistUserSong.getPlaylist().getId(),
                playlistUserSong.getUserSong().getId(),
                playlistUserSong.getPosition()
        );
    }
}
