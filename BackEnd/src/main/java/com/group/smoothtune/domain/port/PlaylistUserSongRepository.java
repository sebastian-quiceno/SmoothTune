package com.group.smoothtune.domain.port;

import com.group.smoothtune.domain.model.PlaylistUserSong;

import java.util.List;
import java.util.Optional;

public interface PlaylistUserSongRepository {
    PlaylistUserSong save(PlaylistUserSong playlistUserSong);
    Optional<PlaylistUserSong> findById(Long id);
    List<PlaylistUserSong> findAll();
    void deleteById(Long id);

    boolean existsByPlaylistIdAndUserSongId(Long playlistId, Long userSongId);
}
