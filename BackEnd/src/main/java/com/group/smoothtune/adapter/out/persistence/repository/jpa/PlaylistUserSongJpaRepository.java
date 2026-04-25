package com.group.smoothtune.adapter.out.persistence.repository.jpa;

import com.group.smoothtune.adapter.out.persistence.entity.PlaylistUserSongEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistUserSongJpaRepository extends JpaRepository<PlaylistUserSongEntity, Long> {
    boolean existsByPlaylistIdAndUserSongId(Long playlistId, Long userSongId);
}
