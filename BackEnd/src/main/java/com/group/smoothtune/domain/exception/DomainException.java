package com.group.smoothtune.domain.exception;

public sealed class DomainException extends RuntimeException
        permits UserNotFoundException,
        UsernameAlreadyExistException,
        SongAlreadySaveException,
        SongNotFoundException,
        ErrorWhileGettingDurationException,
        GenreNotFoundException,
        AccessDeniedException,
        PlaylistNotFoundException,
        UserSongNotFoundException,
        PlaylistUserSongNotFoundException,
        EmailAlreadyExistException,
        InvalidImageException,
        InvalidAudioException{

    public DomainException(String message) {
        super(message);
    }
}
