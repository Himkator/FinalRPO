package com.example.finalsoft.mappers;

import com.example.finalsoft.DTO.UserRequestDTO;
import com.example.finalsoft.DTO.UserResponseDTO;
import com.example.finalsoft.db.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "role", expression = "java(user.getRole().name())")
    @Mapping(target = "status", expression = "java(user.getStatus().name())")
    UserResponseDTO toResponse(User user);
    List<UserResponseDTO> toListResponseDTO(List<User> users);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "blockedAt", ignore = true)
    @Mapping(target = "teams", ignore = true)
    @Mapping(target = "enrollments", ignore = true)
    @Mapping(target = "role", expression = "java(com.example.finalsoft.db.enums.RoleEnum.valueOf(dto.getRole()))")
    User toEntity(UserRequestDTO dto);
}
