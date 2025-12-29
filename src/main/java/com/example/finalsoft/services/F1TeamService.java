package com.example.finalsoft.services;

import com.example.finalsoft.DTO.F1TeamRequestDTO;
import com.example.finalsoft.DTO.F1TeamResponseDTO;
import com.example.finalsoft.db.entities.F1Team;
import com.example.finalsoft.db.enums.TeamStatusEnum;
import com.example.finalsoft.db.repositories.F1TeamRepository;
import com.example.finalsoft.mappers.F1TeamMapper;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class F1TeamService {
    private final F1TeamRepository repository;
    private F1TeamMapper mapper;

    public List<F1TeamResponseDTO> getAllF1Teams() {
        return mapper.toListResponse(repository.findAll());
    }

    public F1TeamResponseDTO getAllF1TeamsById(Long id) {
        return mapper.toResponse(repository.findById(id).orElseThrow());
    }

    public F1TeamResponseDTO createF1Team(F1TeamRequestDTO dto) {
        F1Team f1Team = mapper.toEntity(dto);
        f1Team = repository.saveAndFlush(f1Team);
        return mapper.toResponse(f1Team);
    }

    public F1TeamResponseDTO editF1Team(Long id, F1TeamRequestDTO dto) {
        F1Team f1Team = mapper.toEntity(dto);
        f1Team.setId(id);
        f1Team = repository.saveAndFlush(f1Team);
        return mapper.toResponse(f1Team);
    }

    public String delete(Long id) {
        F1Team f1Team = repository.findById(id).orElseThrow();
        f1Team.setStatus(TeamStatusEnum.DELETED);
        f1Team.setDeletedAt(Instant.now());
        f1Team = repository.save(f1Team);
        return "ok";
    }
}
