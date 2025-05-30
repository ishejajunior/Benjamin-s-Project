package com.benjamin.ecommerce.service;

import com.benjamin.ecommerce.model.User;

import java.util.Optional;

public interface UserService {
    User registerNewUser(User user);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}