package com.group.smoothtune.domain.port;

public interface AuthenticatePort {
    void authenticate(String email, String password);
}
