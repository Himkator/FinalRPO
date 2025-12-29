package com.example.finalsoft.mappers;

import com.example.finalsoft.DTO.EnrollmentRequestDTO;
import com.example.finalsoft.DTO.EnrollmentResponseDTO;
import com.example.finalsoft.db.entities.Course;
import com.example.finalsoft.db.entities.Enrollment;
import com.example.finalsoft.db.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = { CourseMapper.class, UserMapper.class })
public interface EnrollmentMapper {
    @Mapping(target = "status", expression = "java(enrollment.getStatus().name())")
    EnrollmentResponseDTO toResponse(Enrollment enrollment);
    List<EnrollmentResponseDTO> toListResponse(List<Enrollment> enrollments);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "completedAt", ignore = true)
    @Mapping(target = "cancelledAt", ignore = true)
    @Mapping(target = "status", expression = "java(com.example.finalsoft.db.enums.EnrollmentStatusEnum.valueOf(dto.getStatus()))")
    @Mapping(target = "student", source = "student", qualifiedByName = "mapManager")
    @Mapping(target = "course", source = "course", qualifiedByName = "mapManagerCourse")
    Enrollment toEntity(EnrollmentRequestDTO dto);

    @Named("mapManager")
    default User mapManager(Long managerId) {
        if (managerId == null) {
            return null;
        }
        return User.builder().id(managerId).build();
    }

    @Named("mapManagerCourse")
    default Course mapManagerCourse(Long managerId) {
        if (managerId == null) {
            return null;
        }
        return Course.builder().id(managerId).build();
    }
}
