package com.group.smoothtune.domain.port;

import com.group.smoothtune.domain.model.Artist;

import java.util.List;
import java.util.Optional;

public interface ArtistRepository {
    Artist save(Artist artist);
    Optional<Artist> findById(Long id);
    List<Artist> findAll();
    void deleteById(Long id);
}
