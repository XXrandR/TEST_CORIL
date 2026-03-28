package com.examen.backend.service;

import com.examen.backend.dto.LoginRequest;
import com.examen.backend.dto.LoginResponse;
import com.examen.backend.entity.User;
import com.examen.backend.repository.UserRepository;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuario o clave incorrectos"));

        boolean validPassword = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if (!validPassword) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuario o clave incorrectos");
        }

        String tokenSource = user.getUsername() + ":" + Instant.now().toEpochMilli();
        String token = Base64.getEncoder().encodeToString(tokenSource.getBytes(StandardCharsets.UTF_8));

        return new LoginResponse(user.getUsername(), token);
    }
}
