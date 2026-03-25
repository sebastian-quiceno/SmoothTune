package com.group.smoothtune.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "songs")
@Data
public class SongEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String artist;
    private String album;
    private String genre;

    @Lob
    @Column(nullable = false)
    private byte[] audioData;

    private String contentType;
}