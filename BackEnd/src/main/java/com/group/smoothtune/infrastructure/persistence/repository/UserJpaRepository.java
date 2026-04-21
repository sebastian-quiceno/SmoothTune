package com.group.smoothtune.infrastructure.persistence.repository;

import com.group.smoothtune.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {

    @Query("""
        SELECT u FROM UserEntity u
        LEFT JOIN FETCH u.savedSongs
        LEFT JOIN FETCH u.uploadedSongs
        LEFT JOIN FETCH u.playlists
        WHERE u.id = :id
    """)
    Optional<UserEntity> findFullById(Long id);

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByUsername(String username);
}
