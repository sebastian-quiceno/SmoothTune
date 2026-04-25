package com.group.smoothtune.adapter.in.rest.dtos;

public record SongResponseDTO(
   String tittle,
   String artist,
   Long uploaderId,
   Long genreId,
   String getFilePath,
   Float duration

) {}
