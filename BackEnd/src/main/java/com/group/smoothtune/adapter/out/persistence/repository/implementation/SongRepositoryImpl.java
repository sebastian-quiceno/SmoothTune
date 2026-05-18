package com.group.smoothtune.adapter.out.persistence.repository.implementation;

import com.group.smoothtune.domain.model.Song;
import com.group.smoothtune.domain.port.SongRepository;
import com.group.smoothtune.adapter.out.persistence.entity.SongEntity;
import com.group.smoothtune.adapter.out.persistence.mapper.SongPersistenceMapper;
import com.group.smoothtune.adapter.out.persistence.repository.jpa.SongJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SongRepositoryImpl implements SongRepository {

    private final SongJpaRepository songJpaRepository;

    public SongRepositoryImpl(SongJpaRepository songJpaRepository) {
        this.songJpaRepository = songJpaRepository;
    }

    @Override
    public Song save(Song song) {
        SongEntity entity = SongPersistenceMapper.toEntity(song);
        SongEntity saved = songJpaRepository.save(entity);
        return SongPersistenceMapper.toDomain(saved);
    }

    @Override
    public Optional<Song> findById(Long id) {
        return songJpaRepository.findById(id)
                .map(SongPersistenceMapper::toDomain);
    }

    @Override
    public List<Song> findByArtist(String artist) {
        List<SongEntity> songs = songJpaRepository.findByArtist(artist);

        return songs.stream().map(SongPersistenceMapper::toDomain).toList();
    }

    @Override
    public List<Song> findByTitle(String title) {
        List<SongEntity> songs = songJpaRepository.findByTitle(title);

        return songs.stream().map(SongPersistenceMapper::toDomain).toList();
    }

    @Override
    public Page<Song> findAll(Pageable pageable){
        return songJpaRepository.findAll(pageable).map(SongPersistenceMapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        songJpaRepository.deleteById(id);
    }

    @Override
    public List<Song> findByUploaderId(Long userId){
        return songJpaRepository.findByUploaderId(userId).
                stream().
                map(SongPersistenceMapper::toDomain).
                toList();
    }
}


