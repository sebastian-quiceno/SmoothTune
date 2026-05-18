package com.group.smoothtune.adapter.out.persistence.repository.jpa;

import com.group.smoothtune.adapter.out.persistence.entity.UserSongEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSongJpaRepository extends JpaRepository<UserSongEntity, Long> {
    boolean existsByUserIdAndSongId(Long userId, Long songId);
    List<UserSongEntity> findTop10ByUserIdOrderByTimesPlayedDesc(Long userId);
    List<UserSongEntity> findAllByUserIdOrderBySavedAt(Long id);

}
