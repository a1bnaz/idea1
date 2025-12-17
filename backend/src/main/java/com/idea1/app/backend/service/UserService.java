package com.idea1.app.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.idea1.app.backend.model.User;
import com.idea1.app.backend.repository.UserRepo;

@Service
public class UserService {
    
    @Autowired
    private UserRepo repo;

    @Autowired
    private PasswordEncoder passwordEncoder; // inject the PasswordEncoder bean

    public User register(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword())); // hash the password before saving
        return repo.save(user);
    }

}
