package com.group.smoothtune.domain.port;


import com.group.smoothtune.domain.model.UserSong;

import java.util.List;
import java.util.Optional;

public interface UserSongRepository {
    UserSong save(UserSong userSong);
    Optional<UserSong> findById(Long id);
    List<UserSong> findAll();
    void deleteById(Long id);

    boolean existsByUserIdAndSongId(Long userId, Long songId);
}
