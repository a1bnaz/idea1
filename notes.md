
**12/17/25 - the linear flow: from postman to "access granted" before implementing JWT**
> 1. the request hits the building: postman sends a request with a username/password. 
> 2. The SecurityConfig intercepts it: it sees httpBasic() is enabled, so it extracts the username and password from the header.
> 3. spring called MyUserDetailsService: it says "hey, "i have a username 'albert'. go find him."
> 4. MyUserDetailsService talks to the database: it finds the user in PostgreSQL.
> 5. the wrapping: MyUserDetailsService takes that database user and puts it inside a UserPrincipal.
> 6. the hand-off: the UserPrincipal (now acting as the official ID card) is handed back to spring.
> 7. the comparison: spring takes the password from your UserPrincipal (the database password) and uses the passwordEncoder method defined in SecurityConfig to see if it matches what postman sent.
> 8. result: if they match, the "gatekeeper" in SecurityConfig (anyRequest().authenticated()) lets the request through to your controller.