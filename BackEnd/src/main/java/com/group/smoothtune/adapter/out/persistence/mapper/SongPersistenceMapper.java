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
                entity.getUploadedAt(),
                UserPersistenceMapper.toDomain(entity.getUploader()),
                GenrePersistenceMapper.toDomain(entity.getGenre()),
                ArtistPersistenceMapper.toDomain(entity.getArtist()),
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
        entity.setUploadedAt(song.getUploadedAt());

        UserEntity user = new UserEntity();
        user.setId(song.getUploader().getId());

        ArtistEntity artist = new ArtistEntity();
        artist.setId(song.getArtist().getId());

        GenreEntity genre = new GenreEntity();
        genre.setId(song.getGenre().getId());

        entity.setUploader(user);
        entity.setArtist(artist);
        entity.setGenre(genre);

        return entity;
    }

}
