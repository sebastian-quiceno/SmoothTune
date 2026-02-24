package com.group.smoothtune.dtos;

public record SignUpRequestDTO(
        String email,
        String username,
        String password
) {}