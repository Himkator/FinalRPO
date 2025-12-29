package com.example.finalsoft.services;

import com.example.finalsoft.DTO.UserResponseDTO;
import com.example.finalsoft.db.entities.User;
import com.example.finalsoft.db.enums.StatusEnum;
import com.example.finalsoft.db.repositories.UserRepository;
import com.example.finalsoft.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private UserMapper mapper;

    public List<UserResponseDTO> getAllUsers() {
        return mapper.toListResponseDTO(repository.findAll());
    }

    public String block(Long id) {
        User user = repository.findById(id).orElseThrow();
        user.setStatus(StatusEnum.BLOCKED);
        user = repository.save(user);
        return "blocked";
    }

    public String activate(Long id) {
        User user = repository.findById(id).orElseThrow();
        user.setStatus(StatusEnum.BLOCKED);
        user = repository.save(user);
        return "activated";
    }
}
