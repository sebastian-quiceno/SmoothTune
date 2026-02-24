package com.group.smoothtune.dtos;

public record SignInRequestDTO(
        String email,
        String password
) {}