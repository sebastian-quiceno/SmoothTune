package com.group.smoothtune;

import com.group.smoothtune.application.usecase.PlaylistUserSong.AddPlayListUserSongUseCase;
import com.group.smoothtune.domain.exception.*;
import com.group.smoothtune.domain.model.Playlist;
import com.group.smoothtune.domain.model.PlaylistUserSong;
import com.group.smoothtune.domain.model.UserSong;
import com.group.smoothtune.domain.port.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddPlayListUserSongUseCaseTest {

    @Mock
    private PlaylistUserSongRepository playlistUserSongRepository;

    @Mock
    private PlaylistRepository playlistRepository;

    @Mock
    private UserSongRepository userSongRepository;

    @InjectMocks
    private AddPlayListUserSongUseCase useCase;

    @Test
    void shouldAddSongToPlaylistSuccessfully() {
        // Arrange
        Long playlistId = 1L;
        Long userSongId = 10L;

        Playlist playlist = mock(Playlist.class);
        when(playlist.getPlaylistSongs()).thenReturn(Collections.emptyList());

        when(playlistRepository.findById(playlistId))
                .thenReturn(Optional.of(playlist));

        when(userSongRepository.findById(userSongId))
                .thenReturn(Optional.of(mock(UserSong.class)));

        when(playlistUserSongRepository.existsByPlaylistIdAndUserSongId(playlistId, userSongId))
                .thenReturn(false);

        PlaylistUserSong saved = new PlaylistUserSong(0, playlistId, userSongId);

        when(playlistUserSongRepository.save(any(PlaylistUserSong.class)))
                .thenReturn(saved);

        // Act
        PlaylistUserSong result = useCase.execute(playlistId, userSongId);

        // Assert
        assertNotNull(result);
        verify(playlistUserSongRepository).save(any(PlaylistUserSong.class));
    }

    @Test
    void shouldThrowExceptionWhenPlaylistNotFound() {
        // Arrange
        Long playlistId = 1L;
        Long userSongId = 10L;

        when(playlistRepository.findById(playlistId))
                .thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(PlaylistNotFoundException.class, () -> {
            useCase.execute(playlistId, userSongId);
        });

        verify(playlistUserSongRepository, never()).save(any());
    }

    @Test
    void shouldThrowExceptionWhenUserSongNotFound() {
        // Arrange
        Long playlistId = 1L;
        Long userSongId = 10L;

        Playlist playlist = mock(Playlist.class);

        when(playlistRepository.findById(playlistId))
                .thenReturn(Optional.of(playlist));

        when(userSongRepository.findById(userSongId))
                .thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserSongNotFoundException.class, () -> {
            useCase.execute(playlistId, userSongId);
        });

        verify(playlistUserSongRepository, never()).save(any());
    }

    @Test
    void shouldThrowExceptionWhenSongAlreadyExistsInPlaylist() {
        // Arrange
        Long playlistId = 1L;
        Long userSongId = 10L;

        Playlist playlist = mock(Playlist.class);

        when(playlistRepository.findById(playlistId))
                .thenReturn(Optional.of(playlist));

        when(userSongRepository.findById(userSongId))
                .thenReturn(Optional.of(mock(UserSong.class)));

        when(playlistUserSongRepository.existsByPlaylistIdAndUserSongId(playlistId, userSongId))
                .thenReturn(true);

        // Act & Assert
        assertThrows(SongAlreadySave.class, () -> {
            useCase.execute(playlistId, userSongId);
        });

        verify(playlistUserSongRepository, never()).save(any());
    }
}