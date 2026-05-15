package com.group.smoothtune.adapter.in.rest.dtos;

public record SongResponseDTO(
   String tittle,
   Long artistId,
   Long uploaderId,
   Long genreId,
   Float duration

) {}
