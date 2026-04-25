package com.group.smoothtune.adapter.out.persistence.repository.jpa;

import com.group.smoothtune.adapter.out.persistence.entity.SongEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongJpaRepository extends JpaRepository<SongEntity, Long> {

    List<SongEntity> findByTitle(String title);
    List<SongEntity> findByArtist(String artist);

}
