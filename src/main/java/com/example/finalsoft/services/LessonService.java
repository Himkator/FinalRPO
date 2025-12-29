package com.example.finalsoft.services;

import com.example.finalsoft.DTO.LessonRequestDTO;
import com.example.finalsoft.DTO.LessonResponseDTO;
import com.example.finalsoft.db.entities.Lesson;
import com.example.finalsoft.db.repositories.LessonRepository;
import com.example.finalsoft.mappers.LessonMapper;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonRepository repository;
    private LessonMapper mapper;

    public List<LessonResponseDTO> getAllLesson() {
        return mapper.toListResponse(repository.findAll());
    }


    public List<LessonResponseDTO> getAllByCourse(Long id) {
        return mapper.toListResponse(repository.findAllByCourse_Id(id));
    }

    public LessonResponseDTO create(LessonRequestDTO dto) {
        Lesson lesson = mapper.toEntity(dto);
        lesson = repository.saveAndFlush(lesson);
        return mapper.toResponse(lesson);
    }


    public LessonResponseDTO edit(LessonRequestDTO dto, Long id) {
        Lesson lesson = mapper.toEntity(dto);
        lesson.setId(id);
        lesson = repository.saveAndFlush(lesson);
        return mapper.toResponse(lesson);
    }
}
