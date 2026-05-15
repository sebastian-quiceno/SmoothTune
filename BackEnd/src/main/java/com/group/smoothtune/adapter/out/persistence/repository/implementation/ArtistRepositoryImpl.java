package com.group.smoothtune.adapter.out.persistence.repository.implementation;

import com.group.smoothtune.adapter.out.persistence.entity.ArtistEntity;
import com.group.smoothtune.adapter.out.persistence.mapper.ArtistPersistenceMapper;
import com.group.smoothtune.adapter.out.persistence.repository.jpa.ArtistJpaRepository;
import com.group.smoothtune.domain.model.Artist;
import com.group.smoothtune.domain.port.ArtistRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ArtistRepositoryImpl implements ArtistRepository {

    private final ArtistJpaRepository artistJpaRepository;

    public ArtistRepositoryImpl(ArtistJpaRepository artistJpaRepository) {
        this.artistJpaRepository = artistJpaRepository;
    }

    @Override
    public Artist save(Artist artist){
        ArtistEntity entity = ArtistPersistenceMapper.toEntity(artist);
        ArtistEntity saved = artistJpaRepository.save(entity);
        return ArtistPersistenceMapper.toDomain(saved);
    }

    @Override
    public Optional<Artist> findById(Long id){
        return artistJpaRepository.findById(id).map(ArtistPersistenceMapper::toDomain);
    }

    @Override
    public List<Artist> findAll(){
        return artistJpaRepository.findAll().stream().map((a) -> ArtistPersistenceMapper.toDomain(a)).toList();
    }

    @Override
    public void deleteById(Long id){
        artistJpaRepository.deleteById(id);
    }
}
