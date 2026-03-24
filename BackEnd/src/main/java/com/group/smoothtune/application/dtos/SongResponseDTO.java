package com.group.smoothtune.application.dtos;

public record SongResponseDTO(
   String tittle,
   String artist,
   Long uploaderId,
   Long genreId,
   String url,
   Long duration

) {}
