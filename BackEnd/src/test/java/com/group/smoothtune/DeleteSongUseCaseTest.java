package com.group.smoothtune;

import com.group.smoothtune.application.usecase.Song.DeleteSongUseCase;
import com.group.smoothtune.domain.exception.SongNotFoundException;
import com.group.smoothtune.domain.exception.AccessDeniedException;
import com.group.smoothtune.domain.model.Song;
import com.group.smoothtune.domain.model.User;
import com.group.smoothtune.domain.port.FileStoragePort;
import com.group.smoothtune.domain.port.SongRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        Long songId = 1L;
        Long userId = 10L;

        Song song = mock(Song.class);
        User uploader = mock(User.class);
        when(uploader.getId()).thenReturn(userId);
        when(song.getUploader()).thenReturn(uploader);
        when(song.getAudioPath()).thenReturn("path/file.mp3");

        when(songRepository.findById(songId))
                .thenReturn(Optional.of(song));

        useCase.execute(songId, userId);

        verify(fileStoragePort).deleteFile("path/file.mp3");
        verify(songRepository).deleteById(songId);
    }

    @Test
    void shouldThrowExceptionWhenSongNotFound() {
        Long songId = 1L;
        Long userId = 10L;

        when(songRepository.findById(songId))
                .thenReturn(Optional.empty());

        assertThrows(SongNotFoundException.class, () -> useCase.execute(songId, userId));

        verify(fileStoragePort, never()).deleteFile(any());
        verify(songRepository, never()).deleteById(any());
    }

    @Test
    void shouldThrowExceptionWhenUserHasNoPermission() {
        Long songId = 1L;
        Long userId = 10L;

        Song song = mock(Song.class);
        User uploader = mock(User.class);
        when(uploader.getId()).thenReturn(99L);
        when(song.getUploader()).thenReturn(uploader);

        when(songRepository.findById(songId))
                .thenReturn(Optional.of(song));

        assertThrows(AccessDeniedException.class, () -> useCase.execute(songId, userId));

        verify(fileStoragePort, never()).deleteFile(any());
        verify(songRepository, never()).deleteById(any());
    }
}
