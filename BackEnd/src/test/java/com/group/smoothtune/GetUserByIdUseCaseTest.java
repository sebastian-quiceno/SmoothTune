package com.group.smoothtune;

import com.group.smoothtune.application.usecase.User.GetUserByIdUseCase;
import com.group.smoothtune.domain.exception.UserNotFoundException;
import com.group.smoothtune.domain.model.User;
import com.group.smoothtune.domain.port.UserRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetUserByIdUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private GetUserByIdUseCase getUserByIdUseCase;

    @Test
    void shouldReturnUserWhenIdExists() {
        // Arrange
        Long id = 1L;
        User user = new User("test@test.com", "123", "Sebastian");

        when(userRepository.findById(id))
                .thenReturn(Optional.of(user));

        // Act
        User result = getUserByIdUseCase.execute(id);

        // Assert
        assertNotNull(result);
        assertEquals("Sebastian", result.getUsername());

        verify(userRepository).findById(id);
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        // Arrange
        Long id = 1L;

        when(userRepository.findById(id))
                .thenReturn(Optional.empty());

        // Act & Assert
        UserNotFoundException exception = assertThrows(
                UserNotFoundException.class,
                () -> getUserByIdUseCase.execute(id)
        );

        assertTrue(exception.getMessage().contains("No se encontro el usuario"));

        verify(userRepository).findById(id);
    }
}