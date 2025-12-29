package com.example.finalsoft.contollers;

import com.example.finalsoft.DTO.LessonRequestDTO;
import com.example.finalsoft.DTO.LessonResponseDTO;
import com.example.finalsoft.services.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/lesson")
public class LessonController {
    private final LessonService service;

    @GetMapping
    public ResponseEntity<List<LessonResponseDTO>> getAllResponse(){
        return ResponseEntity.ok(service.getAllLesson());
    }

    @GetMapping("/course/{id}")
    public ResponseEntity<List<LessonResponseDTO>> getAllByCourse(@PathVariable Long id){
        return ResponseEntity.ok(service.getAllByCourse(id));
    }

    @PostMapping
    public ResponseEntity<LessonResponseDTO> create(@RequestBody LessonRequestDTO dto){
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LessonResponseDTO> edit(@RequestBody LessonRequestDTO dto, @PathVariable Long id){
        return ResponseEntity.ok(service.edit(dto, id));
    }
}

