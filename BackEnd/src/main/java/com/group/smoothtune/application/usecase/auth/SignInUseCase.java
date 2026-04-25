package com.group.smoothtune.application.usecase.auth;

import com.group.smoothtune.adapter.in.rest.dtos.AuthResponseDTO;
import com.group.smoothtune.adapter.in.rest.dtos.SignInRequestDTO;
import com.group.smoothtune.domain.port.AuthenticatePort;
import com.group.smoothtune.domain.port.TokenPort;

public class SignInUseCase {

    private final AuthenticatePort authenticatePort;
    private final TokenPort tokenPort;

    public SignInUseCase(AuthenticatePort authenticatePort, TokenPort tokenPort) {
        this.authenticatePort = authenticatePort;
        this.tokenPort = tokenPort;
    }

    public AuthResponseDTO execute(SignInRequestDTO dto) {

        // 1. Autenticación
        authenticatePort.authenticate(dto.email(), dto.password());

        // 2. Generar token
        String token = tokenPort.generateToken(dto.email());

        // 3. Retornar DTO
        return new AuthResponseDTO(token);
    }
}