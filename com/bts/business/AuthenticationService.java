package com.bts.business;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class AuthenticationService {
    private Map<String, User> users = new HashMap<>();

    public void register(String name, String email, String password, String role) {
        User user = new User(name, email, password, role);
        users.put(email, user);
    }
    
    public boolean isEmailRegistered(String email) {
        return users.containsKey(email);
    }


    public void login(String email, String password) {
        User user = users.get(email);
        if (user != null && user.getPassword().equals(password)) {
            user.setLastLoggedIn(LocalDateTime.now());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = user.getLastLoggedIn().format(formatter);
            System.out.println("Welcome, " + user.getName() + "!");
            System.out.println("Email: " + user.getEmail());
            System.out.println("Last Logged In: " + formattedDateTime);
        } else {
            System.out.println("Invalid email or password. Please try again.");
        }
    }
}
