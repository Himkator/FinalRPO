package com.example.finalsoft;

import com.example.finalsoft.DTO.F1TeamRequestDTO;
import com.example.finalsoft.DTO.F1TeamResponseDTO;
import com.example.finalsoft.db.entities.F1Team;
import com.example.finalsoft.db.enums.TeamStatusEnum;
import com.example.finalsoft.db.repositories.F1TeamRepository;
import com.example.finalsoft.services.F1TeamService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

@SpringBootTest
class F1TeamServiceTest {

    @Autowired
    private F1TeamService f1TeamService;

    @Autowired
    private F1TeamRepository f1TeamRepository;

    @Test
    void getAllF1TeamsTest() {
        List<F1TeamResponseDTO> teams = f1TeamService.getAllF1Teams();

        Assertions.assertNotNull(teams);
        Assertions.assertNotEquals(0, teams.size());

        for (F1TeamResponseDTO dto : teams) {
            Assertions.assertNotNull(dto);
            Assertions.assertNotNull(dto.getId());
            Assertions.assertNotNull(dto.getName());
            Assertions.assertNotNull(dto.getStatus());
        }
    }

    @Test
    void getF1TeamByIdTest() {
        List<F1TeamResponseDTO> teams = f1TeamService.getAllF1Teams();
        Assertions.assertNotEquals(0, teams.size());

        Random random = new Random();
        Long teamId = teams.get(random.nextInt(teams.size())).getId();

        F1TeamResponseDTO dto = f1TeamService.getAllF1TeamsById(teamId);

        Assertions.assertNotNull(dto);
        Assertions.assertEquals(teamId, dto.getId());
        Assertions.assertNotNull(dto.getName());
    }

    @Test
    void createF1TeamTest() {
        F1TeamRequestDTO dto = F1TeamRequestDTO.builder()
                .name("Aston Martin F1")
                .country("UK")
                .build();

        F1TeamResponseDTO response = f1TeamService.createF1Team(dto);

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getId());
        Assertions.assertEquals(dto.getName(), response.getName());
        Assertions.assertEquals(TeamStatusEnum.ACTIVE, response.getStatus());
    }

    @Test
    void editF1TeamTest() {
        F1TeamRequestDTO createDto = F1TeamRequestDTO.builder()
                .name("AlphaTauri")
                .country("Italy")
                .build();

        F1TeamResponseDTO created = f1TeamService.createF1Team(createDto);

        F1TeamRequestDTO editDto = F1TeamRequestDTO.builder()
                .name("Visa Cash App RB")
                .country("Italy")
                .build();

        F1TeamResponseDTO updated =
                f1TeamService.editF1Team(created.getId(), editDto);

        Assertions.assertNotNull(updated);
        Assertions.assertEquals(created.getId(), updated.getId());
        Assertions.assertEquals("Visa Cash App RB", updated.getName());
    }

    @Test
    void deleteF1TeamTest() {
        F1TeamRequestDTO dto = F1TeamRequestDTO.builder()
                .name("Haas F1 Team")
                .country("USA")
                .build();

        F1TeamResponseDTO created = f1TeamService.createF1Team(dto);

        String result = f1TeamService.delete(created.getId());

        Assertions.assertEquals("ok", result);

        F1Team deletedTeam =
                f1TeamRepository.findById(created.getId()).orElseThrow();

        Assertions.assertEquals(TeamStatusEnum.DELETED, deletedTeam.getStatus());
        Assertions.assertNotNull(deletedTeam.getDeletedAt());
    }
}

