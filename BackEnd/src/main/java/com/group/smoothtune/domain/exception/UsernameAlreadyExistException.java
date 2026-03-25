package com.group.smoothtune.domain.exception;

public final class UsernameAlreadyExistException extends DomainException{
    public UsernameAlreadyExistException(String message) {
        super(message);
    }
}
