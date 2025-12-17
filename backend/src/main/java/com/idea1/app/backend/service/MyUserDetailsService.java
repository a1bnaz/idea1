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
