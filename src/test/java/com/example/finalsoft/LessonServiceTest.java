package com.example.finalsoft;
import com.example.finalsoft.DTO.LessonRequestDTO;
import com.example.finalsoft.DTO.LessonResponseDTO;
import com.example.finalsoft.db.entities.Course;
import com.example.finalsoft.db.entities.Lesson;
import com.example.finalsoft.db.repositories.CourseRepository;
import com.example.finalsoft.db.repositories.LessonRepository;
import com.example.finalsoft.mappers.LessonMapper;
import com.example.finalsoft.services.LessonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LessonServiceTest {

    private LessonRepository lessonRepository;
    private CourseRepository courseRepository;
    private LessonMapper lessonMapper;
    private LessonService lessonService;

    @BeforeEach
    void setUp() {
        lessonRepository = mock(LessonRepository.class);
        courseRepository = mock(CourseRepository.class);
        lessonMapper = mock(LessonMapper.class);

        lessonService = new LessonService(
                lessonRepository
        );
    }

    @Test
    void getAllLessonTest() {
        Course course = new Course();
        course.setId(1L);

        Lesson lesson = new Lesson();
        lesson.setId(1L);
        lesson.setTitle("Lesson 1");
        lesson.setCourse(course);

        LessonResponseDTO responseDTO = LessonResponseDTO.builder()
                .id(1L)
                .title("Lesson 1")
                .build();

        when(lessonRepository.findAll()).thenReturn(List.of(lesson));
        when(lessonMapper.toResponse(lesson)).thenReturn(responseDTO);

        List<LessonResponseDTO> lessons = lessonService.getAllLesson();

        assertNotNull(lessons);
        assertEquals(1, lessons.size());
        assertEquals("Lesson 1", lessons.get(0).getTitle());
    }

    @Test
    void getAllByCourseTest() {
        Course course = new Course();
        course.setId(10L);

        Lesson lesson = new Lesson();
        lesson.setId(1L);
        lesson.setCourse(course);

        LessonResponseDTO dto = LessonResponseDTO.builder()
                .id(1L)
                .build();

        when(courseRepository.findById(10L)).thenReturn(Optional.of(course));
        when(lessonRepository.findAllByCourse_Id(10L)).thenReturn(List.of(lesson));
        when(lessonMapper.toResponse(lesson)).thenReturn(dto);

        List<LessonResponseDTO> lessons = lessonService.getAllByCourse(10L);

        assertNotNull(lessons);
        assertEquals(1, lessons.size());
        assertEquals(10L, lessons.get(0).getCourse().getId());
    }

    @Test
    void createLessonTest() {
        Course course = new Course();
        course.setId(5L);

        LessonRequestDTO requestDTO = LessonRequestDTO.builder()
                .title("Spring Boot Intro")
                .content("Basics")
                .course(5L)
                .build();

        Lesson lesson = new Lesson();
        lesson.setId(1L);
        lesson.setTitle("Spring Boot Intro");
        lesson.setCourse(course);

        LessonResponseDTO responseDTO = LessonResponseDTO.builder()
                .id(1L)
                .title("Spring Boot Intro")
                .build();

        when(courseRepository.findById(5L)).thenReturn(Optional.of(course));
        when(lessonMapper.toEntity(requestDTO)).thenReturn(lesson);
        when(lessonRepository.save(lesson)).thenReturn(lesson);
        when(lessonMapper.toResponse(lesson)).thenReturn(responseDTO);

        LessonResponseDTO response = lessonService.create(requestDTO);

        assertNotNull(response);
        assertEquals("Spring Boot Intro", response.getTitle());
        assertEquals(5L, response.getCourse().getId());
    }

    @Test
    void editLessonTest() {
        Course course = new Course();
        course.setId(7L);

        Lesson existing = new Lesson();
        existing.setId(1L);
        existing.setTitle("Old Title");
        existing.setCourse(course);

        LessonRequestDTO editDTO = LessonRequestDTO.builder()
                .title("New Title")
                .content("New Content")
                .course(7L)
                .build();

        Lesson updated = new Lesson();
        updated.setId(1L);
        updated.setTitle("New Title");
        updated.setCourse(course);

        LessonResponseDTO responseDTO = LessonResponseDTO.builder()
                .id(1L)
                .title("New Title")
                .build();

        when(lessonRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(courseRepository.findById(7L)).thenReturn(Optional.of(course));
        when(lessonMapper.toEntity(editDTO)).thenReturn(updated);
        when(lessonRepository.save(updated)).thenReturn(updated);
        when(lessonMapper.toResponse(updated)).thenReturn(responseDTO);

        LessonResponseDTO result = lessonService.edit(editDTO, 1L);

        assertEquals("New Title", result.getTitle());
        assertEquals(1L, result.getId());
    }
}
