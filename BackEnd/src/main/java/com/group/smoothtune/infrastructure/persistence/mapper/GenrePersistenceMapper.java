package com.group.smoothtune.infrastructure.persistence.mapper;

import com.group.smoothtune.domain.model.Genre;
import com.group.smoothtune.infrastructure.persistence.entity.GenreEntity;

public class GenrePersistenceMapper {
    public static Genre toDomain(GenreEntity entity) {
        Genre genre = new Genre(
                entity.getId(),
                entity.getName(),
                entity.getDescription()
        );

        return genre;
    }

    public static GenreEntity toEntity(Genre genre) {
        GenreEntity entity = new GenreEntity();

        entity.setId(genre.getId());
        entity.setName(genre.getName());
        entity.setDescription(genre.getDescription());

        return entity;
    }
}
