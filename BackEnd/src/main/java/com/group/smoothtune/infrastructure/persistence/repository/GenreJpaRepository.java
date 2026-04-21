package com.group.smoothtune.infrastructure.persistence.repository;

import com.group.smoothtune.infrastructure.persistence.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreJpaRepository extends JpaRepository<GenreEntity, Long> {
}
