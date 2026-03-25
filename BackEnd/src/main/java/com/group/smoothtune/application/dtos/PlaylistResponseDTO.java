package com.group.smoothtune.application.dtos;

import java.time.LocalDateTime;

public record PlaylistResponseDTO(
        Long id,
        String name,
        Long ownerId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
){}
