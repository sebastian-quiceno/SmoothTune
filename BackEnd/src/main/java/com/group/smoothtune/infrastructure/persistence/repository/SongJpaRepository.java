package com.group.smoothtune.infrastructure.persistence.repository;

import com.group.smoothtune.domain.model.Song;
import com.group.smoothtune.infrastructure.persistence.entity.SongEntity;
import com.group.smoothtune.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongJpaRepository extends JpaRepository<SongEntity, Long> {

    List<SongEntity> findByTitle(String title);
    List<SongEntity> findByArtist(String artist);

}
