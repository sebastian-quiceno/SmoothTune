package com.group.smoothtune.application.usecase.Song;

import com.group.smoothtune.domain.exception.GenreNotFoundException;
import com.group.smoothtune.domain.exception.UserNotFoundException;
import com.group.smoothtune.domain.model.FileResource;
import com.group.smoothtune.domain.model.Song;
import com.group.smoothtune.domain.model.UploadResult;
import com.group.smoothtune.domain.port.*;

import java.io.IOException;

public class UploadSongUseCase {

    private final FileStoragePort fileStoragePort;
    private final SongRepository songRepository;
    private final UserRepository userRepository;
    private final GenreRepository genreRepository;

    public UploadSongUseCase(FileStoragePort fileStoragePort,
                             SongRepository songRepository,
                             UserRepository userRepository,
                             GenreRepository genreRepository) {
        this.fileStoragePort = fileStoragePort;
        this.songRepository = songRepository;
        this.userRepository = userRepository;
        this.genreRepository = genreRepository;
    }

    public Song execute(FileResource audioFile,
                        FileResource imageFile,
                        String title,
                        String artist,
                        Long userId,
                        Long genreId) throws IOException {

        userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado"));

        genreRepository.findById(genreId)
                .orElseThrow(() -> new GenreNotFoundException("Género no encontrado"));

        UploadResult audioResult = fileStoragePort.uploadSong(audioFile);
        String imagePath = fileStoragePort.uploadImage(imageFile);

        int size = audioResult.getSize();
        float duration = audioResult.getDuration();

        Song song = new Song(
                title,
                duration,
                size,
                artist,
                userId,
                genreId
        );

        song.setAudioPath(audioResult.getKey());
        song.setImagePath(imagePath);

        return songRepository.save(song);
    }
}