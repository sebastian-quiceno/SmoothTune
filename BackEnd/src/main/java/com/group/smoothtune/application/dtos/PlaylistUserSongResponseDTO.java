package com.group.smoothtune.application.dtos;

public record PlaylistUserSongResponseDTO(
        Long id,
        Long playlistId,
        Long userSongId,
        Integer position
){}
