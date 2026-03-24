package com.group.smoothtune.domain.port;

import com.group.smoothtune.domain.model.Song;

import java.util.List;
import java.util.Optional;

public interface SongRepository {
    Song save(Song song);
    Optional<Song> findById(Long id);
    List<Song> findByTitle(String title);
    List<Song> findByArtist(String artist);
    List<Song> findAll();
    void deleteById(Long id);

}
