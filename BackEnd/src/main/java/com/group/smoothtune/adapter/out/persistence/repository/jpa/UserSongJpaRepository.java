package com.group.smoothtune.adapter.out.persistence.repository.jpa;

import com.group.smoothtune.adapter.out.persistence.entity.UserSongEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSongJpaRepository extends JpaRepository<UserSongEntity, Long> {
    boolean existsByUserIdAndSongId(Long userId, Long songId);
}
