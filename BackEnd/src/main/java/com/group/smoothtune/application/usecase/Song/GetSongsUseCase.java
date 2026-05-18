package com.group.smoothtune.application.usecase.Song;

import com.group.smoothtune.domain.model.Song;
import com.group.smoothtune.domain.port.SongRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class GetSongsUseCase {
    private final SongRepository songRepository;

    public GetSongsUseCase(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public Page<Song> execute(Pageable pageable){
        return songRepository.findAll(pageable);
    }
}
