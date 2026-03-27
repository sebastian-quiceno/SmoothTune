package com.group.smoothtune;

import com.group.smoothtune.application.usecase.Song.GetSongByIdUseCase;
import com.group.smoothtune.domain.exception.SongNotFoundException;
import com.group.smoothtune.domain.model.Song;
import com.group.smoothtune.domain.port.FileStoragePort;
import com.group.smoothtune.domain.port.SongRepository;

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
class GetSongByIdUseCaseTest {

    @Mock
    private FileStoragePort fileStoragePort;

    @Mock
    private SongRepository songRepository;

    @InjectMocks
    private GetSongByIdUseCase useCase;

    @Test
    void shouldReturnSongInputStreamSuccessfully() {
        // Arrange
        Long songId = 1L;

        Song song = mock(Song.class);
        when(song.getFilePath()).thenReturn("path/file.mp3");

        when(songRepository.findById(songId))
                .thenReturn(Optional.of(song));

        InputStream expectedStream = new ByteArrayInputStream("audio".getBytes());

        when(fileStoragePort.getFile("path/file.mp3"))
                .thenReturn(expectedStream);

        // Act
        InputStream result = useCase.execute(songId);

        // Assert
        assertNotNull(result);
        assertEquals(expectedStream, result);

        verify(songRepository).findById(songId);
        verify(fileStoragePort).getFile("path/file.mp3");
    }

    @Test
    void shouldThrowExceptionWhenSongNotFound() {
        // Arrange
        Long songId = 1L;

        when(songRepository.findById(songId))
                .thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(SongNotFoundException.class, () -> {
            useCase.execute(songId);
        });

        verify(fileStoragePort, never()).getFile(any());
    }
}