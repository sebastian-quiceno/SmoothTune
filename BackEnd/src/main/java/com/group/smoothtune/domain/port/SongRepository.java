package com.group.smoothtune.domain.port;

import com.group.smoothtune.domain.model.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface SongRepository {
    Song save(Song song);
    Optional<Song> findById(Long id);
    List<Song> findByTitle(String title);
    List<Song> findByArtist(String artist);
    Page<Song> findAll(Pageable pageable);
    void deleteById(Long id);

    List<Song> findByUploaderId(Long userId);
}
