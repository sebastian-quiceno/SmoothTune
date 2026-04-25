package com.group.smoothtune;

import com.group.smoothtune.application.usecase.Song.DeleteSongUseCase;
import com.group.smoothtune.domain.exception.SongNotFoundException;
import com.group.smoothtune.domain.exception.AccessDeniedException;
import com.group.smoothtune.domain.model.Song;
import com.group.smoothtune.domain.port.FileStoragePort;
import com.group.smoothtune.domain.port.SongRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteSongUseCaseTest {

    @Mock
    private FileStoragePort fileStoragePort;

    @Mock
    private SongRepository songRepository;

    @InjectMocks
    private DeleteSongUseCase useCase;

    @Test
    void shouldDeleteSongSuccessfully() {
        // Arrange
        Long songId = 1L;
        Long userId = 10L;

        Song song = mock(Song.class);
        when(song.getUploaderId()).thenReturn(userId);
        when(song.getAudioPath()).thenReturn("path/file.mp3");

        when(songRepository.findById(songId))
                .thenReturn(Optional.of(song));

        // Act
        useCase.execute(songId, userId);

        // Assert
        verify(fileStoragePort).deleteFile("path/file.mp3");
        verify(songRepository).deleteById(songId);
    }

    @Test
    void shouldThrowExceptionWhenSongNotFound() {
        // Arrange
        Long songId = 1L;
        Long userId = 10L;

        when(songRepository.findById(songId))
                .thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(SongNotFoundException.class, () -> {
            useCase.execute(songId, userId);
        });

        verify(fileStoragePort, never()).deleteFile(any());
        verify(songRepository, never()).deleteById(any());
    }

    @Test
    void shouldThrowExceptionWhenUserHasNoPermission() {
        // Arrange
        Long songId = 1L;
        Long userId = 10L;

        Song song = mock(Song.class);
        when(song.getUploaderId()).thenReturn(99L); // distinto usuario

        when(songRepository.findById(songId))
                .thenReturn(Optional.of(song));

        // Act & Assert
        assertThrows(AccessDeniedException.class, () -> {
            useCase.execute(songId, userId);
        });

        verify(fileStoragePort, never()).deleteFile(any());
        verify(songRepository, never()).deleteById(any());
    }
}
