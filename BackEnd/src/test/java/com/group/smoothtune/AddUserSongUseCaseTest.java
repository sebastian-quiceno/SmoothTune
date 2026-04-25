package com.group.smoothtune;

import com.group.smoothtune.application.usecase.UserSong.AddUserSongUseCase;
import com.group.smoothtune.domain.exception.SongAlreadySaveException;
import com.group.smoothtune.domain.exception.SongNotFoundException;
import com.group.smoothtune.domain.exception.UserNotFoundException;
import com.group.smoothtune.domain.model.UserSong;
import com.group.smoothtune.domain.port.SongRepository;
import com.group.smoothtune.domain.port.UserRepository;
import com.group.smoothtune.domain.port.UserSongRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddUserSongUseCaseTest {

    @Mock
    private UserSongRepository userSongRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private SongRepository songRepository;

    @InjectMocks
    private AddUserSongUseCase useCase;

    @Test
    void shouldAddUserSongSuccessfully() {
        // Arrange
        Long userId = 1L;
        Long songId = 2L;

        when(userRepository.findById(userId))
                .thenReturn(Optional.of(mock(com.group.smoothtune.domain.model.User.class)));

        when(songRepository.findById(songId))
                .thenReturn(Optional.of(mock(com.group.smoothtune.domain.model.Song.class)));

        when(userSongRepository.existsByUserIdAndSongId(userId, songId))
                .thenReturn(false);

        when(userSongRepository.save(any(UserSong.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        UserSong result = useCase.execute(userId, songId);

        // Assert
        assertNotNull(result);
        verify(userSongRepository).save(any(UserSong.class));
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        // Arrange
        Long userId = 1L;
        Long songId = 2L;

        when(userRepository.findById(userId))
                .thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> {
            useCase.execute(userId, songId);
        });

        verify(userSongRepository, never()).save(any());
    }

    @Test
    void shouldThrowExceptionWhenSongNotFound() {
        // Arrange
        Long userId = 1L;
        Long songId = 2L;

        when(userRepository.findById(userId))
                .thenReturn(Optional.of(mock(com.group.smoothtune.domain.model.User.class)));

        when(songRepository.findById(songId))
                .thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(SongNotFoundException.class, () -> {
            useCase.execute(userId, songId);
        });

        verify(userSongRepository, never()).save(any());
    }

    @Test
    void shouldThrowExceptionWhenSongAlreadySaved() {
        // Arrange
        Long userId = 1L;
        Long songId = 2L;

        when(userRepository.findById(userId))
                .thenReturn(Optional.of(mock(com.group.smoothtune.domain.model.User.class)));

        when(songRepository.findById(songId))
                .thenReturn(Optional.of(mock(com.group.smoothtune.domain.model.Song.class)));

        when(userSongRepository.existsByUserIdAndSongId(userId, songId))
                .thenReturn(true);

        // Act & Assert
        assertThrows(SongAlreadySaveException.class, () -> {
            useCase.execute(userId, songId);
        });

        verify(userSongRepository, never()).save(any());
    }
}