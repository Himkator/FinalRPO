package com.example.finalsoft.contollers;

import com.example.finalsoft.DTO.EnrollmentRequestDTO;
import com.example.finalsoft.DTO.EnrollmentResponseDTO;
import com.example.finalsoft.services.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/enrollment")
public class EnrollmentController {
    private final EnrollmentService service;

    @GetMapping
    public ResponseEntity<List<EnrollmentResponseDTO>> getAllEnrollment(){
        return ResponseEntity.ok(service.getAllEnrollment());
    }

    @GetMapping("/course/{course_id}")
    @PreAuthorize("hasAllAuthorities('MANAGER', 'ADMIN')")
    public ResponseEntity<List<EnrollmentResponseDTO>> getAllEnrollmentByCourse(@PathVariable Long course_id){
        return ResponseEntity.ok(service.getEnrollmentByCourse(course_id));
    }

    @PostMapping
    public ResponseEntity<EnrollmentResponseDTO> createEnrollment(@RequestBody EnrollmentRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createEnrollment(dto));
    }

    @PostMapping("{id}/canceled")
    public ResponseEntity<EnrollmentResponseDTO> cancelEnrollment(@PathVariable Long id){
        return ResponseEntity.ok(service.cancelEnrollment(id));
    }
}
