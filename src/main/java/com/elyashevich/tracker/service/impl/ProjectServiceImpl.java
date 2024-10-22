package com.elyashevich.tracker.service.impl;

import com.elyashevich.tracker.entity.Project;
import com.elyashevich.tracker.exception.ResourceNotFoundException;
import com.elyashevich.tracker.repository.ProjectRepository;
import com.elyashevich.tracker.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Override
    public List<Project> findAll() {
        return this.projectRepository.findAll();
    }

    @Override
    public Project findById(final UUID id) {
        return this.projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project with id '%s' was nor found".formatted(id)));
    }

    @Override
    public Project create(final Project project) {
        var candidate = Project.builder()
                .name(project.getName())
                .description(project.getDescription())
                .build();
        return this.projectRepository.save(candidate);
    }

    @Transactional
    @Override
    public Project update(final UUID id, final Project project) {
        var candidate = this.findById(id);
        candidate.setName(project.getName());
        candidate.setDescription(project.getDescription());
        return this.projectRepository.save(candidate);
    }

    @Transactional
    @Override
    public void delete(final UUID id) {
        var candidate = this.findById(id);
        this.projectRepository.delete(candidate);
    }
}
