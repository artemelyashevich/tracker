package com.elyashevich.tracker.service.impl;

import com.elyashevich.tracker.api.dto.RecordDto;
import com.elyashevich.tracker.entity.Record;
import com.elyashevich.tracker.exception.InvalidTimeException;
import com.elyashevich.tracker.exception.ResourceNotFoundException;
import com.elyashevich.tracker.repository.RecordRepository;
import com.elyashevich.tracker.service.ProjectService;
import com.elyashevich.tracker.service.RecordService;
import com.elyashevich.tracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RecordServiceImpl implements RecordService {

    private final RecordRepository recordRepository;
    private final UserService userService;
    private final ProjectService projectService;

    @Override
    public List<Record> findAll() {
        return this.recordRepository.findAll();
    }

    @Override
    public Record findById(final UUID id) {
        return this.recordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Record with id '%s' was not found".formatted(id)));
    }

    @Transactional
    @Override
    public Record create(final RecordDto dto) {
        validateTime(dto.startTime(), dto.endTime());
        var user = this.userService.findById(dto.userId());
        var project = this.projectService.findById(dto.projectId());
        var candidate = Record.builder()
                .user(user)
                .project(project)
                .startTime(dto.startTime())
                .endTime(dto.endTime())
                .build();
        return this.recordRepository.save(candidate);
    }

    @Transactional
    @Override
    public Record update(final UUID id, final RecordDto dto) {
        var candidate = this.findById(id);
        validateTime(dto.startTime(), dto.endTime());
        var user = this.userService.findById(dto.userId());
        var project = this.projectService.findById(dto.projectId());
        candidate.setUser(user);
        candidate.setProject(project);
        candidate.setStartTime(dto.startTime());
        candidate.setEndTime(dto.endTime());
        return null;
    }

    @Transactional
    @Override
    public void delete(final UUID id) {
        var candidate = this.findById(id);
        this.recordRepository.delete(candidate);
    }

    private static void validateTime(final LocalDateTime startTime, final LocalDateTime endTime) {
        if (endTime.isBefore(startTime)) {
            throw new InvalidTimeException("Start time must be before end time.");
        }
    }
}
