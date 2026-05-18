package com.group.smoothtune.domain.exception;

public sealed class DomainException extends RuntimeException
        permits UserNotFoundException,
        UsernameAlreadyExistException,
        UserDontHavePermission,
        PlaylistUserSongNotFound,
        SongAlreadySaveException,
        SongAlreadySave,
        SongNotFoundException,
        ErrorWhileGettingDurationException,
        GenreNotFoundException,
        AccessDeniedException,
        PlaylistNotFoundException,
        UserSongNotFoundException,
        PlaylistUserSongNotFoundException,
        EmailAlreadyExistException,
        InvalidImageException,
        InvalidAudioException,
        ArtistNotFoundException{

    public DomainException(String message) {
        super(message);
    }
}
