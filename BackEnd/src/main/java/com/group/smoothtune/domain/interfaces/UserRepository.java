package com.group.smoothtune.domain.interfaces;


import com.group.smoothtune.domain.model.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findByEmail(String email);

    User save(User user, String encodedPassword);

    Optional<User> findByUsername(String username);
}
