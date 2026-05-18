package com.group.smoothtune.application.usecase.Song;

import com.group.smoothtune.domain.model.Song;
import com.group.smoothtune.domain.port.SongRepository;

import java.util.List;

public class GetSongsByUploaderId {
    private final SongRepository songRepository;

    public GetSongsByUploaderId(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public List<Song> execute(Long userId){
        return songRepository.findByUploaderId(userId);
    }
}
