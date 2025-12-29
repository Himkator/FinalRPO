package com.example.finalsoft;

import com.example.finalsoft.DTO.EnrollmentRequestDTO;
import com.example.finalsoft.DTO.EnrollmentResponseDTO;
import com.example.finalsoft.db.entities.Course;
import com.example.finalsoft.db.entities.Enrollment;
import com.example.finalsoft.db.enums.EnrollmentStatusEnum;
import com.example.finalsoft.db.repositories.CourseRepository;
import com.example.finalsoft.db.repositories.EnrollmentRepository;
import com.example.finalsoft.services.EnrollmentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

@SpringBootTest
class EnrollmentServiceTest {

    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Test
    void getAllEnrollmentTest() {
        List<EnrollmentResponseDTO> enrollments =
                enrollmentService.getAllEnrollment();

        Assertions.assertNotNull(enrollments);
        Assertions.assertNotEquals(0, enrollments.size());

        for (EnrollmentResponseDTO dto : enrollments) {
            Assertions.assertNotNull(dto);
            Assertions.assertNotNull(dto.getId());
            Assertions.assertNotNull(dto.getCourse());
            Assertions.assertNotNull(dto.getStudent());
            Assertions.assertNotNull(dto.getStatus());
        }
    }

    @Test
    void getEnrollmentByCourseTest() {
        List<Course> courses = courseRepository.findAll();
        Assertions.assertNotEquals(0, courses.size());

        Course randomCourse =
                courses.get(new Random().nextInt(courses.size()));

        List<EnrollmentResponseDTO> enrollments =
                enrollmentService.getEnrollmentByCourse(randomCourse.getId());

        Assertions.assertNotNull(enrollments);

        for (EnrollmentResponseDTO dto : enrollments) {
            Assertions.assertEquals(randomCourse.getId(), dto.getCourse().getId());
        }
    }

    @Test
    void createEnrollmentTest() {
        Course course = courseRepository.findAll().get(0);

        EnrollmentRequestDTO dto = EnrollmentRequestDTO.builder()
                .course(course.getId())
                .student(1L)
                .build();

        EnrollmentResponseDTO response =
                enrollmentService.createEnrollment(dto);

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getId());
        Assertions.assertEquals(course.getId(), response.getCourse().getId());
        Assertions.assertEquals(EnrollmentStatusEnum.ACTIVE, response.getStatus());
    }

    @Test
    void cancelEnrollmentTest() {
        Course course = courseRepository.findAll().get(0);

        EnrollmentRequestDTO dto = EnrollmentRequestDTO.builder()
                .course(course.getId())
                .student(1L)
                .build();

        EnrollmentResponseDTO created =
                enrollmentService.createEnrollment(dto);

        EnrollmentResponseDTO cancelled =
                enrollmentService.cancelEnrollment(created.getId());

        Assertions.assertNotNull(cancelled);
        Assertions.assertEquals(
                EnrollmentStatusEnum.CANCELLED,
                cancelled.getStatus()
        );

        Enrollment enrollment =
                enrollmentRepository.findById(created.getId()).orElseThrow();

        Assertions.assertEquals(
                EnrollmentStatusEnum.CANCELLED,
                enrollment.getStatus()
        );
    }
}
