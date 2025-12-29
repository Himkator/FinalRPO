package com.example.finalsoft;
import com.example.finalsoft.DTO.UserResponseDTO;
import com.example.finalsoft.db.entities.User;
import com.example.finalsoft.db.enums.RoleEnum;
import com.example.finalsoft.db.enums.StatusEnum;
import com.example.finalsoft.db.repositories.UserRepository;
import com.example.finalsoft.mappers.UserMapper;
import com.example.finalsoft.db.repositories.UserRepository;
import com.example.finalsoft.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private UserRepository userRepository;
    private UserMapper userMapper;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userMapper = mock(UserMapper.class);

        userService = new UserService(userRepository);
    }

    @Test
    void getAllUsersTest() {
        User user1 = new User();
        user1.setId(1L);
        user1.setEmail("test1@example.com");
        user1.setStatus(StatusEnum.ACTIVE);

        User user2 = new User();
        user2.setId(2L);
        user2.setEmail("test2@example.com");
        user2.setStatus(StatusEnum.BLOCKED);

        when(userRepository.findAll()).thenReturn(List.of(user1, user2));
        when(userMapper.toResponse(user1)).thenReturn(new UserResponseDTO(1L, "test1@example.com", "Nurym", "S", RoleEnum.USER.name(),StatusEnum.ACTIVE.name()));
        when(userMapper.toResponse(user2)).thenReturn(new UserResponseDTO(2L, "test2@example.com", "N", "S", RoleEnum.USER.name(), StatusEnum.BLOCKED.name()));

        List<UserResponseDTO> users = userService.getAllUsers();

        assertNotNull(users);
        assertEquals(2, users.size());
    }

    @Test
    void blockUserTest() {
        User user = new User();
        user.setId(1L);
        user.setStatus(StatusEnum.ACTIVE);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);

        String result = userService.block(1L);

        assertEquals("blocked", result);
        assertEquals(StatusEnum.BLOCKED, user.getStatus());
    }

    @Test
    void activateUserTest() {
        User user = new User();
        user.setId(1L);
        user.setStatus(StatusEnum.BLOCKED);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);

        String result = userService.activate(1L);

        assertEquals("activated", result);
        assertEquals(StatusEnum.ACTIVE, user.getStatus());
    }
}
