package com.group.smoothtune;

import com.group.smoothtune.application.usecase.User.UpdateUserUseCase;
import com.group.smoothtune.domain.exception.UserNotFoundException;
import com.group.smoothtune.domain.exception.UsernameAlreadyExistException;
import com.group.smoothtune.domain.model.User;
import com.group.smoothtune.domain.port.UserRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateUserUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UpdateUserUseCase updateUserUseCase;

    @Test
    void shouldUpdateUsernameSuccessfully() {
        // Arrange
        Long userId = 1L;
        String newUsername = "nuevoUser";

        User existingUser = new User("test@test.com", "123", "viejoUser");

        when(userRepository.findById(userId))
                .thenReturn(Optional.of(existingUser));

        when(userRepository.findAll())
                .thenReturn(Arrays.asList(existingUser));

        when(userRepository.save(any(User.class)))
                .thenReturn(existingUser);

        // Act
        User result = updateUserUseCase.actualizar(userId, newUsername);

        // Assert
        assertNotNull(result);
        assertEquals(newUsername, result.getUsername());

        verify(userRepository).save(existingUser);
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        // Arrange
        Long userId = 1L;

        when(userRepository.findById(userId))
                .thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> {
            updateUserUseCase.actualizar(userId, "nuevoUser");
        });

        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void shouldThrowExceptionWhenUsernameAlreadyExists() {
        // Arrange
        Long userId = 1L;
        String newUsername = "existente";

        User existingUser = new User("test@test.com", "123", "viejoUser");
        User anotherUser = new User("otro@test.com", "123", "existente");

        when(userRepository.findById(userId))
                .thenReturn(Optional.of(existingUser));

        when(userRepository.findAll())
                .thenReturn(Arrays.asList(existingUser, anotherUser));

        // Act & Assert
        assertThrows(UsernameAlreadyExistException.class, () -> {
            updateUserUseCase.actualizar(userId, newUsername);
        });

        verify(userRepository, never()).save(any(User.class));
    }
}