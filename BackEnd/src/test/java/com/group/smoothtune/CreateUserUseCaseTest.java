package com.group.smoothtune;

import com.group.smoothtune.domain.model.User;
import com.group.smoothtune.domain.port.UserRepository;
import com.group.smoothtune.application.usecase.User.CreateUserUseCase;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateUserUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CreateUserUseCase createUserUseCase;

    @Test
    void shouldCreateUserSuccessfully() {
        // Arrange
        String email = "test@test.com";
        String password = "123";
        String username = "Sebastian";

        User savedUser = new User(email, password, username);

        when(userRepository.save(any(User.class)))
                .thenReturn(savedUser);

        // Act
        User result = createUserUseCase.execute(email, password, username);

        // Assert
        assertNotNull(result);
        assertEquals(email, result.getEmail());
        assertEquals(username, result.getUsername());

        verify(userRepository).save(any(User.class));
    }
}
