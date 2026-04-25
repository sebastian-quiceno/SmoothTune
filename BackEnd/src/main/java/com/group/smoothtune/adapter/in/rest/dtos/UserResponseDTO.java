package com.group.smoothtune.adapter.in.rest.dtos;

import java.util.List;

public record UserResponseDTO(
        String email,
        String userName,
        List<Long> savedSongs,
        List<Long> uploadedSongs,
        List<Long> userPlaylists

){}
