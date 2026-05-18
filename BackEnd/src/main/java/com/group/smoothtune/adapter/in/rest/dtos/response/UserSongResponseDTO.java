package com.group.smoothtune.adapter.in.rest.dtos.response;

import java.time.LocalDateTime;

public record UserSongResponseDTO(
    Long id,
    String userName,
    SongResponseDTO song,
    Integer timesPlayed,
    LocalDateTime savedAt
){}
