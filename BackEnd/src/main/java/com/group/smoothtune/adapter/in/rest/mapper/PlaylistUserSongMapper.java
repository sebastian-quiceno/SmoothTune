package com.group.smoothtune.adapter.in.rest.mapper;

import com.group.smoothtune.adapter.in.rest.dtos.request.PlaylistUserSongRequestDTO;
import com.group.smoothtune.adapter.in.rest.dtos.response.PlaylistUserSongResponseDTO;
import com.group.smoothtune.domain.model.PlaylistUserSong;

public class PlaylistUserSongMapper {

    public PlaylistUserSong toDomain(PlaylistUserSongRequestDTO dto){
        PlaylistUserSong playlistUser = new PlaylistUserSong(dto.getPosition(), dto.getPlaylistId(), dto.getUserSongId());

        return playlistUser;
    }

    public PlaylistUserSongResponseDTO toResponse(PlaylistUserSong playlistUserSong){
        return new PlaylistUserSongResponseDTO(
                playlistUserSong.getId(),
                playlistUserSong.getPlaylistId(),
                playlistUserSong.getUserSongId(),
                playlistUserSong.getPosition(),
                playlistUserSong.getAddedAt()
        );
    }
}
