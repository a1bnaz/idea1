package com.idea1.app.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.idea1.app.backend.model.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);

}
