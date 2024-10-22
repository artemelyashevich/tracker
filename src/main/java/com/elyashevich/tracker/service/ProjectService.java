package com.elyashevich.tracker.service;

import com.elyashevich.tracker.entity.Project;

import java.util.List;
import java.util.UUID;

public interface ProjectService {

    List<Project> findAll();

    Project findById(final UUID id);

    Project create(final Project project);

    Project update(final UUID id, final Project project);

    void delete(final UUID id);
}
