package org.fadhel.springbootlab12.config;


import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@org.springframework.context.annotation.Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(csrf -> csrf.disable())

                .sessionManagement(sm ->
                        sm.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // Spring Security creates a session when necessary, rather than creating one for every request.
                )

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/auth/register").permitAll()
                        .requestMatchers("/api/v1/blog/get", "/api/v1/auth/get/{userId}", "/api/v1/auth/get/username/{username}","/api/v1/auth/get/users", "/api/v1/auth/delete/{userId}").hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/api/v1/blog/get/my/blog", "/api/v1/blog/add","/api/v1/blog/update/{blogId}", "/api/v1/blog/delete/{blogId}", "/api/v1/auth/get/{blogId}", "/api/v1/auth/get/title/{title}").hasAuthority("ROLE_USER")
                        .anyRequest().authenticated() // any request not included must be authenticated
                )
                .logout(logout -> logout
                        .logoutUrl("/api/v1/auth/logout")

                        .invalidateHttpSession(true)
                )
                .httpBasic(basic -> {}) // basic auth
                .build();
    }
}
