package org.fadhel.springbootlab12.config;


import lombok.RequiredArgsConstructor;
import org.fadhel.springbootlab12.model.User;
import org.fadhel.springbootlab12.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminInitializer implements CommandLineRunner {

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.admin.username:admin}")
    private String adminUsername;

    @Value("${app.admin.password:admin123}")
    private String adminPassword;

    @Override
    public void run(String... args) {
        if (authRepository.findUserByUsername(adminUsername) == null) {
            authRepository.save(User.builder()
                    .username(adminUsername.toLowerCase())
                    .password(passwordEncoder.encode(adminPassword))
                    .role("ROLE_ADMIN")
                    .build());
        }
    }
}

