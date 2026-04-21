package com.group.smoothtune.application.usecase.Song;

import com.group.smoothtune.domain.exception.SongNotFoundException;
import com.group.smoothtune.domain.model.Song;
import com.group.smoothtune.domain.port.FileStoragePort;
import com.group.smoothtune.domain.port.SongRepository;

import java.io.InputStream;

public class GetSongByIdUseCase {
    private final FileStoragePort fileStoragePort;
    private final SongRepository songRepository;

    public GetSongByIdUseCase(FileStoragePort fileStoragePort, SongRepository songRepository) {
        this.fileStoragePort = fileStoragePort;
        this.songRepository = songRepository;
    }

    public Song execute(Long songId) {
        return songRepository.findById(songId).orElseThrow(()-> new SongNotFoundException("No se encontro la cancion con el ID: "+songId));

    }

}
