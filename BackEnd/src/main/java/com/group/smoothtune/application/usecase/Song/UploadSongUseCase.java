package com.group.smoothtune.application.usecase.Song;

import com.group.smoothtune.domain.exception.GenreNotFoundException;
import com.group.smoothtune.domain.exception.UserNotFoundException;
import com.group.smoothtune.domain.model.Song;
import com.group.smoothtune.domain.port.*;

import java.io.InputStream;

public class UploadSongUseCase {
    private final FileStoragePort fileStoragePort;
    private final SongRepository songRepository;
    private final UserRepository userRepository;
    private final GenreRepository genreRepository;
    private final AudioMetadataPort audioMetadataPort;

    public UploadSongUseCase(FileStoragePort fileStoragePort, SongRepository songRepository, UserRepository userRepository, GenreRepository genreRepository, AudioMetadataPort audioMetadataPort) {
        this.fileStoragePort = fileStoragePort;
        this.songRepository = songRepository;
        this.userRepository = userRepository;
        this.genreRepository = genreRepository;
        this.audioMetadataPort = audioMetadataPort;
    }

    public Song execute(InputStream inputStream,
                        String filename,
                        String contentType,
                        Long size,
                        String title,
                        String artist,
                        Long userId,
                        Long genreId) {

        userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("No se encontro el usuario con el ID: "+userId));
        genreRepository.findById(genreId).orElseThrow(()-> new GenreNotFoundException("No se encontro el genero con el ID: "+genreId));

        Float duration = audioMetadataPort.getDuration(inputStream);

        String filePath = fileStoragePort.saveFile(
                inputStream,
                filename,
                contentType
        );

        Song song = new Song(
                title,
                filePath,
                contentType,
                duration,
                size,
                artist,
                userId,
                genreId
        );

        return songRepository.save(song);
    }

}
