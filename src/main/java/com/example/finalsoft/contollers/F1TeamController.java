package com.example.finalsoft.contollers;

import com.example.finalsoft.DTO.F1TeamRequestDTO;
import com.example.finalsoft.DTO.F1TeamResponseDTO;
import com.example.finalsoft.services.F1TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/f1team")
@PreAuthorize("hasAllAuthorities('MANAGER', 'ADMIN')")
public class F1TeamController {
    private final F1TeamService service;

    @GetMapping
    public ResponseEntity<List<F1TeamResponseDTO>> getAllF1Teams(){
        return ResponseEntity.ok(service.getAllF1Teams());
    }

    @GetMapping("/{id}")
    public ResponseEntity<F1TeamResponseDTO> getAllF1TeamsById(@PathVariable Long id){
        return ResponseEntity.ok(service.getAllF1TeamsById(id));
    }

    @PostMapping
    public ResponseEntity<F1TeamResponseDTO> createF1Team(@RequestBody F1TeamRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createF1Team(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<F1TeamResponseDTO> editF1Team(@RequestBody F1TeamRequestDTO dto, @PathVariable Long id){
        return ResponseEntity.ok(service.editF1Team(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        return ResponseEntity.ok(service.delete(id));
    }
}
