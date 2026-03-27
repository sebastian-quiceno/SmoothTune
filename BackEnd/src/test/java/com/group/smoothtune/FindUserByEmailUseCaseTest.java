package com.group.smoothtune;

import com.group.smoothtune.application.usecase.User.FindUserByEmailUseCase;
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
class FindUserByEmailUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private FindUserByEmailUseCase findUserByEmailUseCase;

    @Test
    void shouldReturnUserWhenEmailExists() {
        // Arrange
        String email = "test@test.com";
        User user = new User(email, "123", "Sebastian");

        when(userRepository.findByEmail(email))
                .thenReturn(Optional.of(user));

        // Act
        Optional<User> result = findUserByEmailUseCase.execute(email);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(email, result.get().getEmail());

        verify(userRepository).findByEmail(email);
    }

    @Test
    void shouldReturnEmptyWhenUserDoesNotExist() {
        // Arrange
        String email = "test@test.com";

        when(userRepository.findByEmail(email))
                .thenReturn(Optional.empty());

        // Act
        Optional<User> result = findUserByEmailUseCase.execute(email);

        // Assert
        assertFalse(result.isPresent());

        verify(userRepository).findByEmail(email);
    }
}