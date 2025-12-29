package com.example.finalsoft.contollers;

import com.example.finalsoft.DTO.CourseRequestDTO;
import com.example.finalsoft.DTO.CourseResponseDTO;
import com.example.finalsoft.services.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/course")
public class CourseController {
    private final CourseService service;

    @GetMapping
    public ResponseEntity<List<CourseResponseDTO>> getAllCourses(){
        return ResponseEntity.ok(service.getAllCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> getCourse(@PathVariable Long id){
        return ResponseEntity.ok(service.getCourse(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity<CourseResponseDTO> createCourse(@RequestBody CourseRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createCourse(dto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAllAuthorities('MANAGER', 'ADMIN')")
    public ResponseEntity<CourseResponseDTO> editCourse(@PathVariable Long id, @RequestBody CourseRequestDTO dto){
        return ResponseEntity.ok(service.editCourse(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAllAuthorities('MANAGER', 'ADMIN')")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id){
        return ResponseEntity.ok(service.deleteCourse(id));
    }

    @PutMapping("/{id}/archived")
    @PreAuthorize("hasAllAuthorities('MANAGER', 'ADMIN')")
    public ResponseEntity<String> archivedCourse(@PathVariable Long id){
        return ResponseEntity.ok(service.archivedCourse(id));
    }

    @PutMapping("/{id}/unarchived")
    @PreAuthorize("hasAllAuthorities('MANAGER', 'ADMIN')")
    public ResponseEntity<String> unarchivedCourse(@PathVariable Long id){
        return ResponseEntity.ok(service.unarchivedCourse(id));
    }
}
