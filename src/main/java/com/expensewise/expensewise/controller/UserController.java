package com.expensewise.expensewise.controller;

import com.expensewise.expensewise.dto.UserResponseDTO;
import com.expensewise.expensewise.entity.User;
import com.expensewise.expensewise.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserResponseDTO createUser(@RequestBody User user) {
        User savedUser = userService.createUser(user);
        return savedUser.toResponseDTO();
    }

    @GetMapping("/{id}")
    public UserResponseDTO getUser(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return user.toResponseDTO();
    }
}