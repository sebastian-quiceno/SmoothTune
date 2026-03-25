package com.group.smoothtune.domain.exception;

public final class UserNotFoundException extends DomainException{
    public UserNotFoundException(String message) {
        super(message);
    }
}
