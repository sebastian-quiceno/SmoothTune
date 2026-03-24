package com.group.smoothtune.application.dtos;

import java.time.LocalDateTime;
import java.util.List;

public record PlaylistResponseDTO(
        Long id,
        String name,
        Long ownerId,
        List<Long> songs,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
){}
