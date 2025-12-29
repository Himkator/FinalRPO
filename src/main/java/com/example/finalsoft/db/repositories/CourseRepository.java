package com.example.finalsoft.db.repositories;

import com.example.finalsoft.db.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
