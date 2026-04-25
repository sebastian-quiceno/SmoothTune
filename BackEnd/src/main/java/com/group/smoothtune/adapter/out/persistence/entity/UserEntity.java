package com.group.smoothtune.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserEntity{

    public UserEntity(Long id, String email, String password, String username) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.enabled = true;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String username;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserSongEntity> savedSongs = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "uploader")
    private Set<SongEntity> uploadedSongs = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner", cascade = CascadeType.ALL)
    private Set<PlaylistEntity> playlists = new HashSet<>();

    @Column(nullable = false)
    private boolean enabled;

}
