package com.group.smoothtune.domain.port;

import com.group.smoothtune.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String userName);
    List<User> findAll();
    void deleteById(Long id);
}
