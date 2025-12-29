package com.example.finalsoft.db.repositories;

import com.example.finalsoft.db.entities.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
    List<Lesson> findAllByCourse_Id(Long id);
}
