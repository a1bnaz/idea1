package com.idea1.app.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.idea1.app.backend.repository.UserRepo;
import com.idea1.app.backend.model.User;
import com.idea1.app.backend.model.UserPrincipal;

// the UserDetailsService interface is used to retrieve user-related data just from the username. MyUserDetailsService is our custom implementation of UserDetailsService.
/*
this file acts as the data fetcher (the librarian). it is the only file that knows how to talk to your PostgreSQL database.
- Role: data retrieval
- What it does: it takes a username (like "albert") and goes to the filing cabinet (the database) to find the corresponding user record (it pulls out your raw User entity).
- the problem - spring security doens't know what your User entity is. your User entity might have fields for username, password, email, roles, etc., but Spring Security only cares about a few specific details (like username, password, and authorities).
- connection: it fetches the User from the database and then wraps it inside the necessary UserDetails implementation (UserPrincipal) that Spring Security understands.
*/
@Service
public class MyUserDetailsService implements UserDetailsService { 

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        User user = userRepo.findByUsername(username);

        if(user == null){
            System.out.println("user not found");
            throw new UsernameNotFoundException("User not found");
        }

        return new UserPrincipal(user);
    }
    
}
