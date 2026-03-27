package com.group.smoothtune;

import com.group.smoothtune.application.usecase.Playlist.CreatePlaylistUseCase;
import com.group.smoothtune.domain.exception.UserNotFoundException;
import com.group.smoothtune.domain.model.Playlist;
import com.group.smoothtune.domain.port.PlaylistRepository;
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
class CreatePlaylistUseCaseTest {

    @Mock
    private PlaylistRepository playlistRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CreatePlaylistUseCase createPlaylistUseCase;

    @Test
    void shouldCreatePlaylistSuccessfully() {
        // Arrange
        Long userId = 1L;
        String name = "Mi Playlist";

        when(userRepository.findById(userId))
                .thenReturn(Optional.of(mock(com.group.smoothtune.domain.model.User.class)));

        Playlist savedPlaylist = new Playlist(name, userId);

        when(playlistRepository.save(any(Playlist.class)))
                .thenReturn(savedPlaylist);

        // Act
        Playlist result = createPlaylistUseCase.execute(userId, name);

        // Assert
        assertNotNull(result);
        assertEquals(name, result.getName());

        verify(userRepository).findById(userId);
        verify(playlistRepository).save(any(Playlist.class));
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        // Arrange
        Long userId = 1L;
        String name = "Mi Playlist";

        when(userRepository.findById(userId))
                .thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> {
            createPlaylistUseCase.execute(userId, name);
        });

        verify(playlistRepository, never()).save(any(Playlist.class));
    }
}