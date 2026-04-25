package com.group.smoothtune.adapter.out.persistence.repository.implementation;

import com.group.smoothtune.domain.model.Playlist;
import com.group.smoothtune.domain.port.PlaylistRepository;
import com.group.smoothtune.adapter.out.persistence.entity.PlaylistEntity;
import com.group.smoothtune.adapter.out.persistence.mapper.PlaylistPersistenceMapper;
import com.group.smoothtune.adapter.out.persistence.repository.jpa.PlaylistJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PlaylistRepositoryImpl implements PlaylistRepository{
    private final PlaylistJpaRepository playlistJpaRepository;

    public PlaylistRepositoryImpl(PlaylistJpaRepository playlistJpaRepository) {
        this.playlistJpaRepository = playlistJpaRepository;
    }

    @Override
    public Playlist save(Playlist playlist) {
        PlaylistEntity entity = PlaylistPersistenceMapper.toEntity(playlist);
        PlaylistEntity saved = playlistJpaRepository.save(entity);
        return PlaylistPersistenceMapper.toDomain(saved);
    }

    @Override
    public Optional<Playlist> findById(Long id) {
        return playlistJpaRepository.findById(id)
                .map(PlaylistPersistenceMapper::toDomain);
    }

    @Override
    public List<Playlist> findAll() {
        return playlistJpaRepository.findAll()
                .stream()
                .map(PlaylistPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        playlistJpaRepository.deleteById(id);
    }

}
