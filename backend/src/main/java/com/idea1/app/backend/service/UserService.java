package com.idea1.app.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.idea1.app.backend.model.User;
import com.idea1.app.backend.repository.UserRepo;

@Service
public class UserService {
    
    @Autowired
    private UserRepo repo;

    @Autowired
    private JWTService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder; // inject the PasswordEncoder bean

    public User register(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword())); // hash the password before saving
        return repo.save(user);
    }

    // verify user credentials
    // maybe wrap this in a try-catch block later?
    public String verify(User user) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
            user.getUsername(), user.getPassword()
        ));

        if(authentication.isAuthenticated()) {

            return jwtService.generateToken(user.getUsername());

        }

        return "Authentication failed"; // this line should never be reached
    }

}
