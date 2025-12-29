package com.example.finalsoft.mappers;

import com.example.finalsoft.DTO.LessonRequestDTO;
import com.example.finalsoft.DTO.LessonResponseDTO;
import com.example.finalsoft.db.entities.Course;
import com.example.finalsoft.db.entities.Lesson;
import com.example.finalsoft.db.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = { CourseMapper.class })
public interface LessonMapper {
    LessonResponseDTO toResponse(Lesson lesson);
    List<LessonResponseDTO> toListResponse(List<Lesson> lessons);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "course", source = "course", qualifiedByName = "mapManager")
    Lesson toEntity(LessonRequestDTO dto);

    @Named("mapManager")
    default Course mapManager(Long managerId) {
        if (managerId == null) {
            return null;
        }
        return Course.builder().id(managerId).build();
    }
}

