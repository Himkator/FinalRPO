package com.example.finalsoft.contollers;

import com.example.finalsoft.DTO.UserResponseDTO;
import com.example.finalsoft.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    private final UserService service;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers(){
        return ResponseEntity.ok(service.getAllUsers());
    }

    @PostMapping("/{id}/block")
    public ResponseEntity<String> block(@PathVariable Long id){
        return ResponseEntity.ok(service.block(id));
    }

    @PostMapping("/{id}/activate")
    public ResponseEntity<String> acitvate(@PathVariable Long id){
        return ResponseEntity.ok(service.activate(id));
    }
}
