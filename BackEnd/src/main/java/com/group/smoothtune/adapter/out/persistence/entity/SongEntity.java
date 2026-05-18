package com.group.smoothtune.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "songs")
@Getter
@Setter
public class SongEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String imagePath;
    private String audioPath;
    private Float duration;
    private Integer size;
    private LocalDateTime uploadedAt;

    @ManyToOne
    @JoinColumn(name = "uploaded_by")
    private UserEntity uploader;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private GenreEntity genre;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private ArtistEntity artist;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "song")
    private Set<UserSongEntity> userSongs = new HashSet<>();

    @PrePersist
    void onCreate() {
        uploadedAt = LocalDateTime.now();
    }
}