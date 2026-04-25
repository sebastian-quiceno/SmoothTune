package com.group.smoothtune.adapter.out.persistence.repository.implementation;

import com.group.smoothtune.domain.model.PlaylistUserSong;
import com.group.smoothtune.domain.port.PlaylistUserSongRepository;
import com.group.smoothtune.adapter.out.persistence.entity.PlaylistUserSongEntity;
import com.group.smoothtune.adapter.out.persistence.mapper.PlaylistUserSongPersistenceMapper;
import com.group.smoothtune.adapter.out.persistence.repository.jpa.PlaylistUserSongJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PlaylistUserSongRepositoryImpl implements PlaylistUserSongRepository {
    private final PlaylistUserSongJpaRepository playlistUserSongJpaRepository;

    public PlaylistUserSongRepositoryImpl(PlaylistUserSongJpaRepository playlistUserSongJpaRepository) {
        this.playlistUserSongJpaRepository = playlistUserSongJpaRepository;
    }

    @Override
    public PlaylistUserSong save(PlaylistUserSong playlistUserSong) {
        PlaylistUserSongEntity entity = PlaylistUserSongPersistenceMapper.toEntity(playlistUserSong);
        PlaylistUserSongEntity saved = playlistUserSongJpaRepository.save(entity);
        return PlaylistUserSongPersistenceMapper.toDomain(saved);
    }

    @Override
    public Optional<PlaylistUserSong> findById(Long id) {
        return playlistUserSongJpaRepository.findById(id)
                .map(PlaylistUserSongPersistenceMapper::toDomain);
    }

    @Override
    public List<PlaylistUserSong> findAll() {
        return playlistUserSongJpaRepository.findAll()
                .stream()
                .map(PlaylistUserSongPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        playlistUserSongJpaRepository.deleteById(id);
    }

    @Override
    public boolean existsByPlaylistIdAndUserSongId(Long userId, Long songId){

        return playlistUserSongJpaRepository.existsByPlaylistIdAndUserSongId(userId, songId);
    }


}
