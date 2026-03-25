package com.group.smoothtune.infrastructure.persistence.repository;

import com.group.smoothtune.infrastructure.persistence.entity.UserSongEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSongJpaRepository extends JpaRepository<UserSongEntity, Long> {
    boolean existsByUserIdAndSongId(Long userId, Long songId);
}
