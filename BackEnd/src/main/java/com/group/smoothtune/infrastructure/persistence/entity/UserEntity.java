package com.group.smoothtune.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String username;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserSongEntity> savedSongs = new ArrayList<>();

    @OneToMany(mappedBy = "uploader")
    private List<SongEntity> uploadedSongs = new ArrayList<>();

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<PlaylistEntity> playlists = new ArrayList<>();

    @Column(nullable = false)
    private boolean enabled;

}
