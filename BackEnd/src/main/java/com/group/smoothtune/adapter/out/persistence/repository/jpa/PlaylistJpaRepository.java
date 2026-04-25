package com.group.smoothtune.adapter.out.persistence.repository.jpa;

import com.group.smoothtune.adapter.out.persistence.entity.PlaylistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistJpaRepository extends JpaRepository<PlaylistEntity, Long> {
}
