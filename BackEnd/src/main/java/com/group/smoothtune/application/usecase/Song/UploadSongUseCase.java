package com.group.smoothtune.application.usecase.Song;

import com.group.smoothtune.domain.exception.ArtistNotFoundException;
import com.group.smoothtune.domain.exception.GenreNotFoundException;
import com.group.smoothtune.domain.exception.UserNotFoundException;
import com.group.smoothtune.domain.model.*;
import com.group.smoothtune.domain.port.*;

import java.io.IOException;

public class UploadSongUseCase {

    private final FileStoragePort fileStoragePort;
    private final SongRepository songRepository;
    private final UserRepository userRepository;
    private final ArtistRepository artistRepository;
    private final GenreRepository genreRepository;

    public UploadSongUseCase(FileStoragePort fileStoragePort, SongRepository songRepository, UserRepository userRepository, ArtistRepository artistRepository, GenreRepository genreRepository) {
        this.fileStoragePort = fileStoragePort;
        this.songRepository = songRepository;
        this.userRepository = userRepository;
        this.artistRepository = artistRepository;
        this.genreRepository = genreRepository;
    }

    public Song execute(FileResource audioFile,
                        FileResource imageFile,
                        String title,
                        Long artistId,
                        Long userId,
                        Long genreId) throws IOException {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado"));

        Genre genre = genreRepository.findById(genreId)
                .orElseThrow(() -> new GenreNotFoundException("Género no encontrado"));

        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(()-> new ArtistNotFoundException("Artista no encontrado"));

        UploadResult audioResult = fileStoragePort.uploadSong(audioFile);
        String imagePath = fileStoragePort.uploadImage(imageFile);

        int size = audioResult.getSize();
        float duration = audioResult.getDuration();

        Song song = new Song(
                title,
                duration,
                size,
                user,
                genre,
                artist
        );

        song.setAudioPath(audioResult.getKey());
        song.setImagePath(imagePath);

        return songRepository.save(song);
    }
}