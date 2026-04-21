package com.group.smoothtune.domain.exception;

public sealed class DomainException extends RuntimeException
        permits UserNotFoundException,
        UsernameAlreadyExistException,
        SongAlreadySave,
        SongNotFoundException,
        ErrorWhileGettingDurationException,
        GenreNotFoundException,
        UserDontHavePermission,
        PlaylistNotFoundException,
        UserSongNotFoundException,
        PlaylistUserSongNotFound,
        EmailAlreadyExistException,
        InvalidImageException,
        InvalidAudioException{

    public DomainException(String message) {
        super(message);
    }
}
