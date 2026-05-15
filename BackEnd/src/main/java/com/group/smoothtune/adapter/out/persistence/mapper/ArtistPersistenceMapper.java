package com.group.smoothtune.adapter.out.persistence.mapper;

import com.group.smoothtune.adapter.out.persistence.entity.ArtistEntity;
import com.group.smoothtune.domain.model.Artist;

public class ArtistPersistenceMapper {
    public static Artist toDomain(ArtistEntity artistEntity){
        return new Artist(
                artistEntity.getId(),
                artistEntity.getName(),
                artistEntity.getBiography()
        );
    }

    public static ArtistEntity toEntity(Artist artist){
        ArtistEntity entity = new ArtistEntity();

        entity.setId(artist.getId());
        entity.setName(artist.getName());
        entity.setBiography(artist.getBiography());

        return entity;
    }
}
