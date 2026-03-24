package com.group.smoothtune.application.usecase.Song;

import com.group.smoothtune.domain.exception.SongNotFoundException;
import com.group.smoothtune.domain.exception.UserDontHavePermission;
import com.group.smoothtune.domain.model.Song;
import com.group.smoothtune.domain.port.*;

public class DeleteSongUseCase {

    private final FileStoragePort fileStoragePort;
    private final SongRepository songRepository;

    public DeleteSongUseCase(FileStoragePort fileStoragePort, SongRepository songRepository) {
        this.fileStoragePort = fileStoragePort;
        this.songRepository = songRepository;
    }

    public void execute(Long songId, Long userId) {

        Song song = songRepository.findById(songId).orElseThrow(()-> new SongNotFoundException("No se encontro la cancion con el ID: "+songId));

        if (!song.getUploaderId().equals(userId)) {
            throw new UserDontHavePermission("No tienes permisos para eliminar esta canción");
        }

        fileStoragePort.deleteFile(song.getFilePath());


        songRepository.deleteById(songId);
    }
}
