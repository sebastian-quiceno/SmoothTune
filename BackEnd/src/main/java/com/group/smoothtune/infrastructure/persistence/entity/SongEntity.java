package com.group.smoothtune.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "songs")
@Data
public class SongEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String artist;

    @ManyToOne
    @JoinColumn(name = "uploaded_by")
    private UserEntity uploader;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private GenreEntity genre;

    private String filePath;
    private String url;
    private String contentType;

    private Float duration;
    private Long size;

    @OneToMany(mappedBy = "song")
    private List<UserSongEntity> userSongs = new ArrayList<>();

}