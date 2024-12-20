package com.example.ExpenseTracker.service;


import com.example.ExpenseTracker.model.UserDetails;
import com.example.ExpenseTracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserDetails registerUser(UserDetails user) {
        return userRepository.save(user);
    }

    public Optional<UserDetails> login(String email, String password) {
        Optional<UserDetails> user = userRepository.findByEmail(email);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return user;
        }
        return Optional.empty();
    }
}
