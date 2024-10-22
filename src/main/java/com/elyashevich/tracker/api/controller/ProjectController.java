package com.elyashevich.tracker.api.controller;

import com.elyashevich.tracker.api.dto.ProjectDto;
import com.elyashevich.tracker.api.mapper.ProjectMapper;
import com.elyashevich.tracker.service.ProjectService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectMapper projectMapper;

    @GetMapping
    public List<ProjectDto> findAll() {
        var projects = this.projectService.findAll();
        return this.projectMapper.toDto(projects);
    }

    @GetMapping("/{id}")
    public ProjectDto findById(final @PathVariable("id") UUID id) {
        var project = this.projectService.findById(id);
        return this.projectMapper.toDto(project);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectDto create(final @Valid @RequestBody ProjectDto dto) {
        var project = this.projectService.create(this.projectMapper.toEntity(dto));
        return this.projectMapper.toDto(project);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectDto update(final @PathVariable("id") UUID id, final @Valid @RequestBody ProjectDto dto) {
        var project = this.projectService.update(id, this.projectMapper.toEntity(dto));
        return this.projectMapper.toDto(project);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(final @PathVariable("id") UUID id) {
        this.projectService.delete(id);
    }
}
