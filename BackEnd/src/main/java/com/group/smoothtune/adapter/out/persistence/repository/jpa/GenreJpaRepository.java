package com.group.smoothtune.adapter.out.persistence.repository.jpa;

import com.group.smoothtune.adapter.out.persistence.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreJpaRepository extends JpaRepository<GenreEntity, Long> {
}
