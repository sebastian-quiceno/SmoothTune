package com.group.smoothtune.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "playlist_user_songs")
public class PlaylistUserSongEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "playlist_id")
    private PlaylistEntity playlist;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_song_id")
    private UserSongEntity userSong;

    private LocalDateTime addedAt;

    private Integer position; // opcional (orden)

    @PrePersist
    void onCreate() {
        addedAt = LocalDateTime.now();
    }
}
