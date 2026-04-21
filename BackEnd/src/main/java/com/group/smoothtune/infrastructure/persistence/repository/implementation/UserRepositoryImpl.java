package com.group.smoothtune.infrastructure.persistence.repository.implementation;

import com.group.smoothtune.domain.exception.UserNotFoundException;
import com.group.smoothtune.domain.model.User;
import com.group.smoothtune.domain.port.UserRepository;
import com.group.smoothtune.infrastructure.persistence.entity.UserEntity;
import com.group.smoothtune.infrastructure.persistence.entity.UserSongEntity;
import com.group.smoothtune.infrastructure.persistence.mapper.UserPersistenceMapper;
import com.group.smoothtune.infrastructure.persistence.repository.UserJpaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    public UserRepositoryImpl(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    @Transactional
    public User save(User user) {
        UserEntity entity = UserPersistenceMapper.toEntity(user);
        UserEntity saved = userJpaRepository.save(entity);
        return UserPersistenceMapper.toDomain(saved);
    }

    @Override
    @Transactional
    public Optional<User> findById(Long id) {
        return userJpaRepository.findFullById(id)
                .map(UserPersistenceMapper::toDomain);
    }

    @Override
    @Transactional
    public Optional<User> findByEmail(String email) {
        return userJpaRepository.findByEmail(email)
                .map(UserPersistenceMapper::toDomain);
    }

    @Override
    @Transactional
    public Optional<User> findByUsername(String username) {
        return userJpaRepository.findByUsername(username)
                .map(UserPersistenceMapper::toDomain);
    }

    @Override
    @Transactional
    public List<User> findAll() {
        return userJpaRepository.findAll()
                .stream()
                .map(UserPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userJpaRepository.deleteById(id);
    }
}
