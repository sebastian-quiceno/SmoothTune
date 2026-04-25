package com.group.smoothtune.domain.exception;

public final class SongAlreadySaveException extends DomainException{
    public SongAlreadySaveException(String message) {
        super(message);
    }
}
