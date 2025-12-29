package com.example.finalsoft.services;

import com.example.finalsoft.DTO.EnrollmentRequestDTO;
import com.example.finalsoft.DTO.EnrollmentResponseDTO;
import com.example.finalsoft.db.entities.Enrollment;
import com.example.finalsoft.db.enums.EnrollmentStatusEnum;
import com.example.finalsoft.db.repositories.EnrollmentRepository;
import com.example.finalsoft.mappers.EnrollmentMapper;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentService {
    private final EnrollmentRepository repository;
    private EnrollmentMapper mapper;

    public List<EnrollmentResponseDTO> getAllEnrollment() {
        return mapper.toListResponse(repository.findAll());
    }


    public List<EnrollmentResponseDTO> getEnrollmentByCourse(Long courseId) {
        return mapper.toListResponse(repository.findAllByCourse_Id(courseId));
    }

    public EnrollmentResponseDTO createEnrollment(EnrollmentRequestDTO dto) {
        Enrollment enrollment = mapper.toEntity(dto);
        enrollment = repository.saveAndFlush(enrollment);
        return mapper.toResponse(enrollment);
    }

    public @Nullable EnrollmentResponseDTO cancelEnrollment(Long id) {
        Enrollment enrollment = repository.findById(id).orElseThrow();
        enrollment.setStatus(EnrollmentStatusEnum.CANCELLED);
        enrollment = repository.save(enrollment);
        return mapper.toResponse(enrollment);
    }
}
