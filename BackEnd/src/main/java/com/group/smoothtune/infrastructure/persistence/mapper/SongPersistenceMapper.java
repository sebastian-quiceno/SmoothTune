package com.group.smoothtune.infrastructure.persistence.mapper;

import com.group.smoothtune.domain.model.Song;
import com.group.smoothtune.infrastructure.persistence.entity.SongEntity;
import com.group.smoothtune.infrastructure.persistence.entity.UserSongEntity;

public class SongPersistenceMapper {

    public static Song toDomain(SongEntity entity) {
        Song song = new Song(
                entity.getId(),
                entity.getTitle(),
                entity.getFilePath(),
                entity.getContentType(),
                entity.getDuration(),
                entity.getSize(),
                entity.getArtist(),
                entity.getUploader().getId(),
                entity.getGenre().getId(),
                entity.getUserSongs().stream().map(UserSongEntity::getId).toList()
        );

        return song;
    }

    public static SongEntity toEntity(Song song) {
        SongEntity entity = new SongEntity();

        entity.setId(song.getId());
        entity.setTitle(song.getTitle());
        entity.setFilePath(song.getFilePath());
        entity.setContentType(song.getContentType());
        entity.setDuration(song.getDuration());
        entity.setSize(song.getSize());
        entity.setArtist(song.getArtist());

        return entity;
    }
}
