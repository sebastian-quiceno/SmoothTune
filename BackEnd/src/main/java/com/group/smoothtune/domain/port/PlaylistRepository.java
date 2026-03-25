package com.group.smoothtune.domain.port;

import com.group.smoothtune.domain.model.Playlist;

import java.util.List;
import java.util.Optional;

public interface PlaylistRepository {
    Playlist save(Playlist playlist);
    Optional<Playlist> findById(Long id);
    List<Playlist> findAll();
    void deleteById(Long id);

}
