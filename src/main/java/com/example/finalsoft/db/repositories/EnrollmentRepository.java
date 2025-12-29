package com.example.finalsoft.db.repositories;

import com.example.finalsoft.db.entities.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findAllByCourse_Id(Long id);
}
