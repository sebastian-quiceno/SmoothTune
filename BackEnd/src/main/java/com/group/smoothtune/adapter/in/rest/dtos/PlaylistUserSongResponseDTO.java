package com.group.smoothtune.adapter.in.rest.dtos;

import java.time.LocalDateTime;

public record PlaylistUserSongResponseDTO(
        Long id,
        Long playlistId,
        Long userSongId,
        Integer position,
        LocalDateTime addedAt
){}