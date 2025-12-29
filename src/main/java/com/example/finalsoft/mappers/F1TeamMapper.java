package com.example.finalsoft.mappers;

import com.example.finalsoft.DTO.F1TeamRequestDTO;
import com.example.finalsoft.DTO.F1TeamResponseDTO;
import com.example.finalsoft.db.entities.F1Team;
import com.example.finalsoft.db.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = { UserMapper.class })
public interface F1TeamMapper {
    @Mapping(target = "status", expression = "java(f1Team.getStatus().name())")
    F1TeamResponseDTO toResponse(F1Team f1Team);
    List<F1TeamResponseDTO> toListResponse(List<F1Team> f1Teams);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "courseList", ignore = true)
    @Mapping(target = "manager", source = "manager", qualifiedByName = "mapManager")
    F1Team toEntity(F1TeamRequestDTO dto);

    @Named("mapManager")
    default User mapManager(Long managerId) {
        if (managerId == null) {
            return null;
        }
        return User.builder().id(managerId).build();
    }
}
