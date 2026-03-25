package com.group.smoothtune.application.dtos;

import java.time.LocalDateTime;

public record PlaylistUserSongResponseDTO(
        Long id,
        Long playlistId,
        Long userSongId,
        Integer position,
        LocalDateTime addedAt
){}