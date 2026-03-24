package com.group.smoothtune.domain.port;

import com.group.smoothtune.application.dtos.AuthResponseDTO;
import com.group.smoothtune.domain.model.AuthResult;

public interface AuthenticatePort {
    AuthResult authenticate(String email, String password);
}
