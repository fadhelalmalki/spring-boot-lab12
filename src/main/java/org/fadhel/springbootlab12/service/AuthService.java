package org.fadhel.springbootlab12.service;

import lombok.RequiredArgsConstructor;
import org.fadhel.springbootlab12.api.ApiException;
import org.fadhel.springbootlab12.model.User;
import org.fadhel.springbootlab12.repository.AuthRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;

    public void registerUser(User user) {
        User existingUser = authRepository.findUserByUsername(user.getUsername());
        if (existingUser != null) {
            throw new ApiException("Username already exists");
        }
        user.setUsername(user.getUsername().toLowerCase());
        String hash = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hash);
        user.setRole("ROLE_USER");
        authRepository.save(user);
    }

    public User getUserById(Long userId) {
        return authRepository.findUserById(userId);
    }

    public User getUserByUsername(String username) {
        return authRepository.findUserByUsername(username);
    }

    public void deleteUser(Long userId) {
        authRepository.delete(getUserById(userId));
    }

    public List<User> getUsers() {

        return authRepository.findAll();
    }
}
