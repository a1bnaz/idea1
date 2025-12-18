package com.idea1.app.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
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

    public String register(User user){
        // Hash the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        // Save the user to the database
        User savedUser = repo.save(user);
        
        // Generate JWT token for the newly registered user so they can immediately access protected endpoints
        return jwtService.generateToken(savedUser.getUsername());
    }

    // verify user credentials
    public String verify(User user) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                user.getUsername(), user.getPassword()
            ));

            if(authentication.isAuthenticated()) {
                return jwtService.generateToken(user.getUsername());
            }
            
            throw new BadCredentialsException("Authentication failed");
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid username or password", e);
        }
    }

}
