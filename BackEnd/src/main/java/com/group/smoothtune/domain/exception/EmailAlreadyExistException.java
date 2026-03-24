package com.group.smoothtune.domain.exception;

public final class EmailAlreadyExistException extends DomainException {
    public EmailAlreadyExistException(String message) {
        super(message);
    }
}
