package org.fadhel.springbootlab12.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.fadhel.springbootlab12.api.ApiResponse;
import org.fadhel.springbootlab12.model.User;
import org.fadhel.springbootlab12.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid User user) {
        authService.registerUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("User added"));
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(authService.getUserById(userId));
    }

    @GetMapping("/get/username/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(authService.getUserByUsername(username));
    }

    @GetMapping("/get/users")
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(authService.getUsers());
    }


    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        authService.deleteUser(userId);
        return ResponseEntity.status(200).body(new ApiResponse("User deleted"));
    }
}
