package com.group.smoothtune;

import com.group.smoothtune.application.usecase.Song.UploadSongUseCase;
import com.group.smoothtune.domain.exception.ArtistNotFoundException;
import com.group.smoothtune.domain.exception.GenreNotFoundException;
import com.group.smoothtune.domain.exception.UserNotFoundException;
import com.group.smoothtune.domain.model.Artist;
import com.group.smoothtune.domain.model.FileResource;
import com.group.smoothtune.domain.model.Genre;
import com.group.smoothtune.domain.model.Song;
import com.group.smoothtune.domain.model.UploadResult;
import com.group.smoothtune.domain.model.User;
import com.group.smoothtune.domain.port.ArtistRepository;
import com.group.smoothtune.domain.port.FileStoragePort;
import com.group.smoothtune.domain.port.GenreRepository;
import com.group.smoothtune.domain.port.SongRepository;
import com.group.smoothtune.domain.port.UserRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    private ArtistRepository artistRepository;

    @InjectMocks
    private UploadSongUseCase useCase;

    @Test
    void shouldUploadSongSuccessfully() throws IOException {
        FileResource audioFile = new FileResource("audio".getBytes(), "song.mp3", "audio/mpeg");
        FileResource imageFile = new FileResource("image".getBytes(), "cover.jpg", "image/jpeg");
        User user = new User(1L, "mail@test.com", "password", "uploader", true, null, null, null);
        Genre genre = new Genre(2L, "Rock", "desc");
        Artist artist = new Artist(3L, "Artista", "bio");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(genreRepository.findById(2L)).thenReturn(Optional.of(genre));
        when(artistRepository.findById(3L)).thenReturn(Optional.of(artist));

        UploadResult uploadResult = new UploadResult("path/file.mp3", 120.0f, 1000);
        when(fileStoragePort.uploadSong(audioFile)).thenReturn(uploadResult);
        when(fileStoragePort.uploadImage(imageFile)).thenReturn("path/cover.jpg");

        when(songRepository.save(any(Song.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Song result = useCase.execute(
                audioFile,
                imageFile,
                "Titulo",
                3L,
                1L,
                2L
        );

        assertNotNull(result);
        assertEquals("Titulo", result.getTitle());
        assertEquals("path/file.mp3", result.getAudioPath());
        assertEquals("path/cover.jpg", result.getImagePath());
        assertEquals(Float.valueOf(120.0f), result.getDuration());
        assertEquals(Integer.valueOf(1000), result.getSize());
        assertSame(user, result.getUploader());
        assertSame(genre, result.getGenre());
        assertSame(artist, result.getArtist());

        verify(songRepository).save(any(Song.class));
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        FileResource audioFile = new FileResource("audio".getBytes(), "song.mp3", "audio/mpeg");
        FileResource imageFile = new FileResource("image".getBytes(), "cover.jpg", "image/jpeg");

        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> useCase.execute(
                audioFile,
                imageFile,
                "Titulo",
                3L,
                1L,
                2L
        ));

        verify(songRepository, never()).save(any());
    }

    @Test
    void shouldThrowExceptionWhenGenreNotFound() {
        FileResource audioFile = new FileResource("audio".getBytes(), "song.mp3", "audio/mpeg");
        FileResource imageFile = new FileResource("image".getBytes(), "cover.jpg", "image/jpeg");
        User user = new User(1L, "mail@test.com", "password", "uploader", true, null, null, null);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(genreRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(GenreNotFoundException.class, () -> useCase.execute(
                audioFile,
                imageFile,
                "Titulo",
                3L,
                1L,
                2L
        ));

        verify(songRepository, never()).save(any());
    }

    @Test
    void shouldThrowExceptionWhenArtistNotFound() {
        FileResource audioFile = new FileResource("audio".getBytes(), "song.mp3", "audio/mpeg");
        FileResource imageFile = new FileResource("image".getBytes(), "cover.jpg", "image/jpeg");
        User user = new User(1L, "mail@test.com", "password", "uploader", true, null, null, null);
        Genre genre = new Genre(2L, "Rock", "desc");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(genreRepository.findById(2L)).thenReturn(Optional.of(genre));
        when(artistRepository.findById(3L)).thenReturn(Optional.empty());

        assertThrows(ArtistNotFoundException.class, () -> useCase.execute(
                audioFile,
                imageFile,
                "Titulo",
                3L,
                1L,
                2L
        ));

        verify(songRepository, never()).save(any());
    }
}
