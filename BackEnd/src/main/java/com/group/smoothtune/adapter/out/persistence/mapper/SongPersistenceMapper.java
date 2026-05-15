package com.group.smoothtune.adapter.out.persistence.mapper;

import com.group.smoothtune.adapter.out.persistence.entity.*;
import com.group.smoothtune.domain.model.Song;

public class SongPersistenceMapper {

    public static Song toDomain(SongEntity entity) {
        return new Song(
                entity.getId(),
                entity.getTitle(),
                entity.getImagePath(),
                entity.getAudioPath(),
                entity.getDuration(),
                entity.getSize(),
                entity.getArtist().getId(),
                entity.getUploader().getId(),
                entity.getGenre().getId(),
                entity.getUserSongs().stream().map(UserSongEntity::getId).toList()
        );
    }

    public static SongEntity toEntity(Song song) {
        SongEntity entity = new SongEntity();

        entity.setId(song.getId());
        entity.setTitle(song.getTitle());
        entity.setImagePath(song.getImagePath());
        entity.setAudioPath(song.getAudioPath());
        entity.setDuration(song.getDuration());
        entity.setSize(song.getSize());

        UserEntity user = new UserEntity();
        user.setId(song.getUploaderId());

        ArtistEntity artist = new ArtistEntity();
        artist.setId(song.getArtistId());

        GenreEntity genre = new GenreEntity();
        genre.setId(song.getGenreId());

        entity.setUploader(user);
        entity.setArtist(artist);
        entity.setGenre(genre);

        return entity;
    }

}
