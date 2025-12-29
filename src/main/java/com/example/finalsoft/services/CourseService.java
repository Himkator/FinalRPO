package com.example.finalsoft.services;

import com.example.finalsoft.DTO.CourseRequestDTO;
import com.example.finalsoft.DTO.CourseResponseDTO;
import com.example.finalsoft.db.entities.Course;
import com.example.finalsoft.db.entities.User;
import com.example.finalsoft.db.enums.CourseStatusEnum;
import com.example.finalsoft.db.enums.RoleEnum;
import com.example.finalsoft.db.repositories.CourseRepository;
import com.example.finalsoft.mappers.CourseMapper;
import com.example.finalsoft.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository repository;
    private CourseMapper mapper;

    public List<CourseResponseDTO> getAllCourses() {
        return mapper.toListResponse(repository.findAll());
    }


    public CourseResponseDTO getCourse(Long id) {
        return mapper.toResponse(repository.findById(id).orElse(null));
    }


    public CourseResponseDTO createCourse(CourseRequestDTO dto) {
        Course course = mapper.toEntity(dto);
        course = repository.saveAndFlush(course);
        return mapper.toResponse(course);
    }

    public CourseResponseDTO editCourse(Long id, CourseRequestDTO dto) {
        Course course = mapper.toEntity(dto);
        course.setId(id);
        course = repository.save(course);
        return mapper.toResponse(course);
    }

    public String deleteCourse(Long id) {
        Course course = repository.findById(id).orElse(null);
        if(course == null) throw new UsernameNotFoundException("course doesnt exist");

        User user = SecurityUtils.getCurrentUser();

        if(course.getF1Team().getManager().getId() != user.getId() && !user.getRole().equals(RoleEnum.ADMIN) )
            throw new BadCredentialsException("Doenst have access");

        course.setStatus(CourseStatusEnum.DELETED);
        course.setDeletedAt(Instant.now());
        course = repository.save(course);
        return "ok";
    }

    public String archivedCourse(Long id) {
        Course course = repository.findById(id).orElse(null);
        if(course == null) throw new UsernameNotFoundException("course doesnt exist");
        User user = SecurityUtils.getCurrentUser();

        if(course.getF1Team().getManager().getId() != user.getId() && !user.getRole().equals(RoleEnum.ADMIN) )
            throw new BadCredentialsException("Doenst have access");

        course.setStatus(CourseStatusEnum.ARCHIVED);
        course = repository.save(course);
        return "ok";
    }

    public String unarchivedCourse(Long id) {
        Course course = repository.findById(id).orElse(null);
        if(course == null) throw new UsernameNotFoundException("course doesnt exist");
        User user = SecurityUtils.getCurrentUser();

        if(course.getF1Team().getManager().getId() != user.getId() && !user.getRole().equals(RoleEnum.ADMIN) )
            throw new BadCredentialsException("Doenst have access");

        course.setStatus(CourseStatusEnum.ACTIVE);
        course = repository.save(course);
        return "ok";
    }
}
