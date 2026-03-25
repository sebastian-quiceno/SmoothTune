package com.group.smoothtune.domain.port;

import com.group.smoothtune.domain.model.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {
    Genre save(Genre genre);
    Optional<Genre> findById(Long id);
    List<Genre> findAll();
    void deleteById(Long id);
}
