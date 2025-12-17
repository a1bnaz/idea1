package com.idea1.app.backend.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.idea1.app.backend.model.User;
import com.idea1.app.backend.repository.UserRepo;

@Configuration
// DataSeeder class to seed initial data into the database (temporary)
public class DataSeeder {
    
    @Bean
    public CommandLineRunner initDatabase(UserRepo repository, JdbcTemplate jdbcTemplate, PasswordEncoder passwordEncoder) {

        return args -> {
            System.out.println("clearing old data and resetting sequence...");

            //1. delete all data from the table
            jdbcTemplate.execute("DELETE FROM users");

            //2. reset the id sequence counter back to 1
            jdbcTemplate.execute("ALTER SEQUENCE users_id_seq RESTART WITH 1");

            System.out.println("seeding temporary using data...");

            repository.save(new User("albert", passwordEncoder.encode("123")));
            repository.save(new User("david", passwordEncoder.encode("123")));
        };
    }
}
