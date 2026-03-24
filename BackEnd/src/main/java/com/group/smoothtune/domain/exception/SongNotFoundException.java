package com.group.smoothtune.domain.exception;

public final class SongNotFoundException extends DomainException {
    public SongNotFoundException(String message) {
        super(message);
    }
}
