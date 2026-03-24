package com.group.smoothtune.domain.exception;

public final class UserDontHavePermission extends DomainException {
    public UserDontHavePermission(String message) {
        super(message);
    }
}
