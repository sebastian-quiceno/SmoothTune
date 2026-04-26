package com.group.smoothtune;

import com.group.smoothtune.application.usecase.Song.UploadSongUseCase;
import com.group.smoothtune.domain.exception.GenreNotFoundException;
import com.group.smoothtune.domain.exception.UserNotFoundException;
import com.group.smoothtune.domain.model.FileResource;
import com.group.smoothtune.domain.model.Song;
import com.group.smoothtune.domain.model.UploadResult;
import com.group.smoothtune.domain.port.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
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

    @InjectMocks
    private UploadSongUseCase useCase;

    @Test
    void shouldUploadSongSuccessfully() throws IOException {
        // Arrange
        FileResource audioFile = new FileResource("audio".getBytes(), "song.mp3", "audio/mpeg");
        FileResource imageFile = new FileResource("image".getBytes(), "cover.jpg", "image/jpeg");

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(mock(com.group.smoothtune.domain.model.User.class)));

        when(genreRepository.findById(2L))
                .thenReturn(Optional.of(mock(com.group.smoothtune.domain.model.Genre.class)));

        UploadResult uploadResult = new UploadResult("path/file.mp3", 120.0f, 1000);
        when(fileStoragePort.uploadSong(audioFile))
                .thenReturn(uploadResult);

        when(fileStoragePort.uploadImage(imageFile))
                .thenReturn("path/cover.jpg");

        when(songRepository.save(any(Song.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Song result = useCase.execute(
                audioFile,
                imageFile,
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
        FileResource audioFile = new FileResource("audio".getBytes(), "song.mp3", "audio/mpeg");
        FileResource imageFile = new FileResource("image".getBytes(), "cover.jpg", "image/jpeg");

        when(userRepository.findById(1L))
                .thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> {
            useCase.execute(
                    audioFile,
                    imageFile,
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
        FileResource audioFile = new FileResource("audio".getBytes(), "song.mp3", "audio/mpeg");
        FileResource imageFile = new FileResource("image".getBytes(), "cover.jpg", "image/jpeg");

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(mock(com.group.smoothtune.domain.model.User.class)));

        when(genreRepository.findById(2L))
                .thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(GenreNotFoundException.class, () -> {
            useCase.execute(
                    audioFile,
                    imageFile,
                    "Titulo",
                    "Artista",
                    1L,
                    2L
            );
        });

        verify(songRepository, never()).save(any());
    }
}