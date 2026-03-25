package com.group.smoothtune.infrastructure.persistence.repository;

import com.group.smoothtune.infrastructure.persistence.entity.PlaylistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistJpaRepository extends JpaRepository<PlaylistEntity, Long> {
}
