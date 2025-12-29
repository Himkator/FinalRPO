package com.example.finalsoft.services;

import com.example.finalsoft.DTO.AuthDTO;
import com.example.finalsoft.DTO.LoginDTO;
import com.example.finalsoft.DTO.UserRequestDTO;
import com.example.finalsoft.db.entities.User;
import com.example.finalsoft.db.repositories.UserRepository;
import com.example.finalsoft.mappers.UserMapper;
import com.example.finalsoft.utils.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository repository;
    private final JWTUtils jwtUtils;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthDTO login(LoginDTO dto) {
        User user = repository.findByEmail(dto.getEmail());

        if (user == null)
            throw new UsernameNotFoundException("User not found");

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword())
            );
        }catch (Exception e) {
            throw new BadCredentialsException("Invalid username or password");
        }

        return AuthDTO.builder()
                .token(jwtUtils.generateToken(user))
                .build();
    }

    public AuthDTO register(UserRequestDTO dto) {
        if (repository.findByEmail(dto.getEmail()) != null)
            throw new IllegalStateException("Username is already exists");

        User user = mapper.toEntity(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        User savedUser =repository.saveAndFlush(user);

        if (savedUser == null || savedUser.getId() <= 0)
            throw new RuntimeException("User could not be saved");

        return AuthDTO.builder()
                .token(jwtUtils.generateToken(user))
                .build();
    }
}
