package com.group.smoothtune.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "user_songs")
public class UserSongEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "song_id", nullable = false)
    private SongEntity song;

    @OneToMany(mappedBy = "userSong")
    private List<PlaylistUserSongEntity> playlistSongs = new ArrayList<>();

    private LocalDateTime savedAt;

    @PrePersist
    void onCreate() {
        savedAt = LocalDateTime.now();
    }

}
