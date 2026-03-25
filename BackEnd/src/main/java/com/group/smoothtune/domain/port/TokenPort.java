package com.group.smoothtune.domain.port;

import com.group.smoothtune.domain.model.AuthResult;

public interface TokenPort {
    String generateToken(AuthResult authResult);
}
