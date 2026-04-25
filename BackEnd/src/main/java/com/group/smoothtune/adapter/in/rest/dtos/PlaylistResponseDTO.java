package com.group.smoothtune.adapter.in.rest.dtos;

import java.time.LocalDateTime;

public record PlaylistResponseDTO(
        Long id,
        String name,
        Long ownerId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
){}
