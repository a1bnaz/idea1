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
import com.idea1.app.backend.dto.AuthResponse;

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

    public AuthResponse register(User user){
        // Hash the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        // Save the user to the database
        User savedUser = repo.save(user);

        // Generate JWT token for the newly registered user so they can immediately access protected endpoints
        String token = jwtService.generateToken(savedUser.getUsername());

        // return both the saved user and token
        return new AuthResponse(savedUser, token);
    }

    // verify user credentials
    public AuthResponse verify(User user) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                user.getUsername(), user.getPassword()
            ));

            if(authentication.isAuthenticated()) {
                String token = jwtService.generateToken(user.getUsername());
                User dbUser = repo.findByUsername(user.getUsername());
                return new AuthResponse(dbUser, token);
            }

            throw new BadCredentialsException("Authentication failed");
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid username or password", e);
        }
    }

}
