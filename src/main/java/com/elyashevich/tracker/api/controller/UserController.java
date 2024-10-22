package com.elyashevich.tracker.api.controller;

import com.elyashevich.tracker.api.dto.UserDto;
import com.elyashevich.tracker.api.mapper.UserMapper;
import com.elyashevich.tracker.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public List<UserDto> findAll() {
        var users = this.userService.findAll();
        return this.userMapper.toDto(users);
    }

    @GetMapping("/{id}")
    public UserDto findById(final @PathVariable("id") UUID id) {
        var user = this.userService.findById(id);
        return this.userMapper.toDto(user);
    }

    @PostMapping
    public UserDto create(final @Valid @RequestBody UserDto dto) {
        var user = this.userService.create(this.userMapper.toEntity(dto));
        return this.userMapper.toDto(user);
    }

    @PutMapping("/{id}")
    public UserDto update(final @PathVariable("id") UUID id, final @Valid @RequestBody UserDto dto) {
        var user = this.userService.update(id, this.userMapper.toEntity(dto));
        return this.userMapper.toDto(user);
    }

    @DeleteMapping("/{id}")
    public void delete(final @PathVariable("id") UUID id) {
        this.userService.delete(id);
    }
}
