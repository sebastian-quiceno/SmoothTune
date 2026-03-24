package com.group.smoothtune.domain.port;

public interface PasswordEncoderPort {
    String encode(String rawPassword);
}
