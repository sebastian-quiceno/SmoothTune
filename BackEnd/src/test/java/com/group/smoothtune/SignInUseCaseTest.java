package com.group.smoothtune;

import com.group.smoothtune.application.usecase.auth.SignInUseCase;
import com.group.smoothtune.application.dtos.AuthResponseDTO;
import com.group.smoothtune.application.dtos.SignInRequestDTO;
import com.group.smoothtune.domain.model.AuthResult;
import com.group.smoothtune.domain.port.AuthenticatePort;
import com.group.smoothtune.domain.port.TokenPort;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SignInUseCaseTest {

    @Mock
    private AuthenticatePort authenticatePort;

    @Mock
    private TokenPort tokenPort;

    @InjectMocks
    private SignInUseCase signInUseCase;

    @Test
    void shouldSignInSuccessfullyAndReturnToken() {
        // Arrange
        String email = "test@test.com";
        String password = "123";
        String fakeToken = "jwt-token";

        SignInRequestDTO request = new SignInRequestDTO(email, password);

        AuthResult authResult = mock(AuthResult.class);

        when(authenticatePort.authenticate(email, password))
                .thenReturn(authResult);

        when(tokenPort.generateToken(authResult))
                .thenReturn(fakeToken);

        // Act
        AuthResponseDTO response = signInUseCase.execute(request);

        // Assert
        assertNotNull(response);
        assertEquals(fakeToken, response.token());

        verify(authenticatePort).authenticate(email, password);
        verify(tokenPort).generateToken(authResult);
    }

    @Test
    void shouldCallAuthenticateBeforeGeneratingToken() {
        // Arrange
        String email = "test@test.com";
        String password = "123";

        SignInRequestDTO request = new SignInRequestDTO(email, password);

        AuthResult authResult = mock(AuthResult.class);

        when(authenticatePort.authenticate(email, password))
                .thenReturn(authResult);

        when(tokenPort.generateToken(authResult))
                .thenReturn("token");

        // Act
        signInUseCase.execute(request);

        // Assert (verifica orden)
        verify(authenticatePort).authenticate(email, password);
        verify(tokenPort).generateToken(authResult);
    }
}