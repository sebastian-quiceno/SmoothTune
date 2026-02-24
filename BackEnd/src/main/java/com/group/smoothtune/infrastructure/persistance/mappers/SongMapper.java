package com.group.smoothtune.infrastructure.persistance.mappers;

import com.group.smoothtune.domain.model.Song;
import com.group.smoothtune.infrastructure.persistance.entities.SongEntity;

public class SongMapper {

    public static Song toDomain(SongEntity entity) {
        return new Song(
                entity.getId(),
                entity.getTitle(),
                entity.getArtist(),
                entity.getAlbum(),
                entity.getGenre()

        );
    }

    public static SongEntity toEntity(Song song, byte[] audio, String contentType) {
        SongEntity entity = new SongEntity();
        entity.setId(song.getId());
        entity.setTitle(song.getTitle());
        entity.setArtist(song.getArtist());
        entity.setAlbum(song.getAlbum());
        entity.setGenre(song.getGenre());
        entity.setAudioData(audio);
        entity.setContentType(contentType);
        return entity;
    }

}
