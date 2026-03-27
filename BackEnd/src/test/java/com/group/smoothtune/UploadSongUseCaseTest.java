package com.group.smoothtune;

import com.group.smoothtune.application.usecase.Song.UploadSongUseCase;
import com.group.smoothtune.domain.exception.GenreNotFoundException;
import com.group.smoothtune.domain.exception.UserNotFoundException;
import com.group.smoothtune.domain.model.Song;
import com.group.smoothtune.domain.port.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UploadSongUseCaseTest {

    @Mock
    private FileStoragePort fileStoragePort;

    @Mock
    private SongRepository songRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private GenreRepository genreRepository;

    @Mock
    private AudioMetadataPort audioMetadataPort;

    @InjectMocks
    private UploadSongUseCase useCase;

    @Test
    void shouldUploadSongSuccessfully() {
        // Arrange
        InputStream inputStream = new ByteArrayInputStream("audio".getBytes());

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(mock(com.group.smoothtune.domain.model.User.class)));

        when(genreRepository.findById(2L))
                .thenReturn(Optional.of(mock(com.group.smoothtune.domain.model.Genre.class)));

        when(audioMetadataPort.getDuration(any()))
                .thenReturn(120.0f);

        when(fileStoragePort.saveFile(any(), anyString(), anyString()))
                .thenReturn("path/file.mp3");

        when(songRepository.save(any(Song.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Song result = useCase.execute(
                inputStream,
                "song.mp3",
                "audio/mpeg",
                1000L,
                "Titulo",
                "Artista",
                1L,
                2L
        );

        // Assert
        assertNotNull(result);
        assertEquals("Titulo", result.getTitle());

        verify(songRepository).save(any(Song.class));
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        // Arrange
        InputStream inputStream = new ByteArrayInputStream("audio".getBytes());

        when(userRepository.findById(1L))
                .thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> {
            useCase.execute(
                    inputStream,
                    "song.mp3",
                    "audio/mpeg",
                    1000L,
                    "Titulo",
                    "Artista",
                    1L,
                    2L
            );
        });

        verify(songRepository, never()).save(any());
    }

    @Test
    void shouldThrowExceptionWhenGenreNotFound() {
        // Arrange
        InputStream inputStream = new ByteArrayInputStream("audio".getBytes());

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(mock(com.group.smoothtune.domain.model.User.class)));

        when(genreRepository.findById(2L))
                .thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(GenreNotFoundException.class, () -> {
            useCase.execute(
                    inputStream,
                    "song.mp3",
                    "audio/mpeg",
                    1000L,
                    "Titulo",
                    "Artista",
                    1L,
                    2L
            );
        });

        verify(songRepository, never()).save(any());
    }
}