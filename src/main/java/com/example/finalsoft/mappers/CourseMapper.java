package com.example.finalsoft.mappers;

import com.example.finalsoft.DTO.CourseRequestDTO;
import com.example.finalsoft.DTO.CourseResponseDTO;
import com.example.finalsoft.db.entities.Course;
import com.example.finalsoft.db.entities.F1Team;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = { F1TeamMapper.class })
public interface CourseMapper {
    @Mapping(target = "status", expression = "java(course.getStatus().name())")
    @Mapping(target = "level", expression = "java(course.getLevel().name())")
    CourseResponseDTO toResponse(Course course);
    List<CourseResponseDTO> toListResponse(List<Course> courses);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "enrollments", ignore = true)
    @Mapping(target = "level", expression = "java(com.example.finalsoft.db.enums.LevelEnum.valueOf(dto.getLevel()))")
    @Mapping(target = "f1Team", source = "f1Team", qualifiedByName = "mapManager")
    Course toEntity(CourseRequestDTO dto);

    @Named("mapManager")
    default F1Team mapManager(Long managerId) {
        if (managerId == null) {
            return null;
        }
        return F1Team.builder().id(managerId).build();
    }
}
