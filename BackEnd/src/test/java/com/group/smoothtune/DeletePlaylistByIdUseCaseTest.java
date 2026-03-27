package com.group.smoothtune;

import com.group.smoothtune.application.usecase.Playlist.DeletePlaylistByIdUseCase;
import com.group.smoothtune.domain.exception.PlaylistNotFoundException;
import com.group.smoothtune.domain.exception.UserDontHavePermission;
import com.group.smoothtune.domain.model.Playlist;
import com.group.smoothtune.domain.port.PlaylistRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeletePlaylistByIdUseCaseTest {

    @Mock
    private PlaylistRepository playlistRepository;

    @InjectMocks
    private DeletePlaylistByIdUseCase useCase;

    @Test
    void shouldDeletePlaylistSuccessfully() {
        // Arrange
        Long userId = 1L;
        Long playlistId = 10L;

        Playlist playlist = mock(Playlist.class);
        when(playlist.getOwnerId()).thenReturn(userId);

        when(playlistRepository.findById(playlistId))
                .thenReturn(Optional.of(playlist));

        // Act
        useCase.execute(userId, playlistId);

        // Assert
        verify(playlistRepository).deleteById(playlistId);
    }

    @Test
    void shouldThrowExceptionWhenPlaylistNotFound() {
        // Arrange
        Long userId = 1L;
        Long playlistId = 10L;

        when(playlistRepository.findById(playlistId))
                .thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(PlaylistNotFoundException.class, () -> {
            useCase.execute(userId, playlistId);
        });

        verify(playlistRepository, never()).deleteById(any());
    }

    @Test
    void shouldThrowExceptionWhenUserHasNoPermission() {
        // Arrange
        Long userId = 1L;
        Long playlistId = 10L;

        Playlist playlist = mock(Playlist.class);
        when(playlist.getOwnerId()).thenReturn(999L); // otro usuario

        when(playlistRepository.findById(playlistId))
                .thenReturn(Optional.of(playlist));

        // Act & Assert
        assertThrows(UserDontHavePermission.class, () -> {
            useCase.execute(userId, playlistId);
        });

        verify(playlistRepository, never()).deleteById(any());
    }
}
