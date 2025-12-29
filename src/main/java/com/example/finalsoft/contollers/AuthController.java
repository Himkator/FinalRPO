package com.example.finalsoft.contollers;

import com.example.finalsoft.DTO.AuthDTO;
import com.example.finalsoft.DTO.LoginDTO;
import com.example.finalsoft.DTO.UserRequestDTO;
import com.example.finalsoft.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService service;
    private final AuthService authService;

    @PostMapping("register")
    public ResponseEntity<AuthDTO> register(@RequestBody UserRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthDTO> login(@RequestBody LoginDTO dto){
        return ResponseEntity.ok(service.login(dto));
    }
}
