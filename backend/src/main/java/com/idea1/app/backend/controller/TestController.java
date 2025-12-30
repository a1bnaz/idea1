package com.idea1.app.backend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.idea1.app.backend.repository.UserRepo;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api")
public class TestController {
    
    private UserRepo userRepo;
    // temporary, delete later
    public TestController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/")
    public String sayHello() {
        return "Hello from the backend!! :)";
    }
}
