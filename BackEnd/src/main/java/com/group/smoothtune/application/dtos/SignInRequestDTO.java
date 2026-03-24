package com.group.smoothtune.application.dtos;

public record SignInRequestDTO(
        String email,
        String password
) {}