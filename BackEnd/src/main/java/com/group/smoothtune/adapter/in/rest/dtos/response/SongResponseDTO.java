package com.group.smoothtune.adapter.in.rest.dtos.response;

import com.group.smoothtune.domain.model.Artist;
import com.group.smoothtune.domain.model.Genre;
import java.time.LocalDateTime;

public record SongResponseDTO(
        Long id,
        String tittle,
        Artist artist,
        String uploaderName,
        Genre genre,
        Float duration,
        Integer size,
        LocalDateTime uploadedAt
) {}
