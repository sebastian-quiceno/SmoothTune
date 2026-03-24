package com.group.smoothtune.infrastructure.persistence.repository.implementation;

import com.group.smoothtune.domain.model.UserSong;
import com.group.smoothtune.domain.port.UserSongRepository;
import com.group.smoothtune.infrastructure.persistence.entity.UserSongEntity;
import com.group.smoothtune.infrastructure.persistence.mapper.UserSongPersistenceMapper;
import com.group.smoothtune.infrastructure.persistence.repository.UserSongJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserSongRepositoryImpl implements UserSongRepository {
    private final UserSongJpaRepository userSongJpaRepository;

    public UserSongRepositoryImpl(UserSongJpaRepository userSongJpaRepository) {
        this.userSongJpaRepository = userSongJpaRepository;
    }

    @Override
    public UserSong save(UserSong userSong) {
        UserSongEntity entity = UserSongPersistenceMapper.toEntity(userSong);
        UserSongEntity saved = userSongJpaRepository.save(entity);
        return UserSongPersistenceMapper.toDomain(saved);
    }

    @Override
    public Optional<UserSong> findById(Long id) {
        return userSongJpaRepository.findById(id)
                .map(UserSongPersistenceMapper::toDomain);
    }

    @Override
    public List<UserSong> findAll() {
        return userSongJpaRepository.findAll()
                .stream()
                .map(UserSongPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        userSongJpaRepository.deleteById(id);
    }

    @Override
    public boolean existsByUserIdAndSongId(Long userId, Long songId){

        return userSongJpaRepository.existsByUserIdAndSongId(userId, songId);
    }

}
