package com.group.smoothtune.adapter.out.persistence.mapper;

import com.group.smoothtune.domain.model.Song;
import com.group.smoothtune.adapter.out.persistence.entity.GenreEntity;
import com.group.smoothtune.adapter.out.persistence.entity.SongEntity;
import com.group.smoothtune.adapter.out.persistence.entity.UserEntity;
import com.group.smoothtune.adapter.out.persistence.entity.UserSongEntity;

public class SongPersistenceMapper {

    public static Song toDomain(SongEntity entity) {
        return new Song(
                entity.getId(),
                entity.getTitle(),
                entity.getImagePath(),
                entity.getAudioPath(),
                entity.getDuration(),
                entity.getSize(),
                entity.getArtist(),
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
        entity.setArtist(song.getArtist());

        UserEntity user = new UserEntity();
        user.setId(song.getUploaderId());

        GenreEntity genre = new GenreEntity();
        genre.setId(song.getGenreId());

        entity.setUploader(user);
        entity.setGenre(genre);

        return entity;
    }

}
