package com.group.smoothtune.infrastructure.persistance.interface_imp;

import com.group.smoothtune.domain.interfaces.UserRepository;
import com.group.smoothtune.domain.model.User;
import com.group.smoothtune.infrastructure.persistance.entities.UserEntity;
import com.group.smoothtune.infrastructure.persistance.mappers.UserMapper;
import com.group.smoothtune.infrastructure.persistance.repositories.JpaUserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImp implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    public UserRepositoryImp(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaUserRepository.findByEmail(email)
                .map(UserMapper::toDomain);
    }

    @Override
    public User save(User user, String encodedPassword) {
        UserEntity entity = UserMapper.toEntity(user, encodedPassword);
        return UserMapper.toDomain(jpaUserRepository.save(entity));
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.empty();
    }
}
