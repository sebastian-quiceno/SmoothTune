package com.group.smoothtune.adapter.in.rest.dtos.response;

import java.time.LocalDateTime;

public record PlaylistResponseDTO(
        Long id,
        String name,
        Long ownerId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
){}
