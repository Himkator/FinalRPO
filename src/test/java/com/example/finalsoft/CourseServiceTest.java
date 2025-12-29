package com.example.finalsoft;

import com.example.finalsoft.DTO.CourseRequestDTO;
import com.example.finalsoft.DTO.CourseResponseDTO;
import com.example.finalsoft.db.entities.Course;
import com.example.finalsoft.db.entities.F1Team;
import com.example.finalsoft.db.entities.User;
import com.example.finalsoft.db.enums.CourseStatusEnum;
import com.example.finalsoft.db.repositories.CourseRepository;
import com.example.finalsoft.db.repositories.F1TeamRepository;
import com.example.finalsoft.db.repositories.UserRepository;
import com.example.finalsoft.services.CourseService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Random;

@SpringBootTest
class CourseServiceTest {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private F1TeamRepository f1TeamRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void getAllCoursesTest() {
        List<CourseResponseDTO> courses = courseService.getAllCourses();

        Assertions.assertNotNull(courses);
        Assertions.assertNotEquals(0, courses.size());

        for (CourseResponseDTO dto : courses) {
            Assertions.assertNotNull(dto);
            Assertions.assertNotNull(dto.getId());
            Assertions.assertNotNull(dto.getTitle());
            Assertions.assertNotNull(dto.getStatus());
        }
    }

    @Test
    void getCourseByIdTest() {
        List<CourseResponseDTO> courses = courseService.getAllCourses();
        Long courseId = courses.get(new Random().nextInt(courses.size())).getId();

        CourseResponseDTO course = courseService.getCourse(courseId);

        Assertions.assertNotNull(course);
        Assertions.assertEquals(courseId, course.getId());

        CourseResponseDTO nullCourse = courseService.getCourse(-1L);
        Assertions.assertNull(nullCourse);
    }

    @Test
    void createCourseTest() {
        F1Team team = f1TeamRepository.findAll().get(0);

        CourseRequestDTO dto = CourseRequestDTO.builder()
                .title("Java Spring Boot")
                .description("Backend course")
                .f1Team(team.getId())
                .build();

        CourseResponseDTO response = courseService.createCourse(dto);

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getId());
        Assertions.assertEquals(dto.getTitle(), response.getTitle());
    }

    @Test
    void editCourseTest() {
        Course course = courseRepository.findAll().get(0);

        CourseRequestDTO dto = CourseRequestDTO.builder()
                .title("Updated title")
                .description("Updated description")
                .f1Team(course.getF1Team().getId())
                .build();

        CourseResponseDTO updated =
                courseService.editCourse(course.getId(), dto);

        Assertions.assertEquals("Updated title", updated.getTitle());
    }

    @Test
    void deleteCourseTest() {
        Course course = courseRepository.findAll().get(0);

        User manager = course.getF1Team().getManager();
        setAuthUser(manager);

        String result = courseService.deleteCourse(course.getId());

        Assertions.assertEquals("ok", result);

        Course deleted =
                courseRepository.findById(course.getId()).orElseThrow();

        Assertions.assertEquals(CourseStatusEnum.DELETED, deleted.getStatus());
        Assertions.assertNotNull(deleted.getDeletedAt());
    }

    @Test
    void archiveCourseTest() {
        Course course = courseRepository.findAll().get(0);

        User manager = course.getF1Team().getManager();
        setAuthUser(manager);

        String result = courseService.archivedCourse(course.getId());

        Assertions.assertEquals("ok", result);

        Course archived =
                courseRepository.findById(course.getId()).orElseThrow();

        Assertions.assertEquals(CourseStatusEnum.ARCHIVED, archived.getStatus());
    }

    @Test
    void unarchiveCourseTest() {
        Course course = courseRepository.findAll().get(0);

        User manager = course.getF1Team().getManager();
        setAuthUser(manager);

        String result = courseService.unarchivedCourse(course.getId());

        Assertions.assertEquals("ok", result);

        Course active =
                courseRepository.findById(course.getId()).orElseThrow();

        Assertions.assertEquals(CourseStatusEnum.ACTIVE, active.getStatus());
    }

    private void setAuthUser(User user) {
        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}
