package com.group.smoothtune.adapter.out.persistence.repository.implementation;

import com.group.smoothtune.domain.model.Genre;
import com.group.smoothtune.domain.port.GenreRepository;
import com.group.smoothtune.adapter.out.persistence.entity.GenreEntity;
import com.group.smoothtune.adapter.out.persistence.mapper.GenrePersistenceMapper;
import com.group.smoothtune.adapter.out.persistence.repository.jpa.GenreJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class GenreRepositoryImpl implements GenreRepository {

    private final GenreJpaRepository genreJpaRepository;

    public GenreRepositoryImpl(GenreJpaRepository genreJpaRepository) {
        this.genreJpaRepository = genreJpaRepository;
    }

    @Override
    public Genre save(Genre genre) {
        GenreEntity entity = GenrePersistenceMapper.toEntity(genre);
        GenreEntity saved = genreJpaRepository.save(entity);
        return GenrePersistenceMapper.toDomain(saved);
    }

    @Override
    public Optional<Genre> findById(Long id) {
        return genreJpaRepository.findById(id)
                .map(GenrePersistenceMapper :: toDomain);
    }

    @Override
    public List<Genre> findAll() {
        return genreJpaRepository.findAll()
                .stream()
                .map(GenrePersistenceMapper :: toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        genreJpaRepository.deleteById(id);
    }
}
