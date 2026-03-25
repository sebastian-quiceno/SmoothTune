package com.group.smoothtune.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "genres")
public class GenreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;
}