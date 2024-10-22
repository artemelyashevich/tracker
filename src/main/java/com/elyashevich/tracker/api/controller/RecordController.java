package com.elyashevich.tracker.api.controller;

import com.elyashevich.tracker.api.dto.RecordDto;
import com.elyashevich.tracker.api.mapper.RecordMapper;
import com.elyashevich.tracker.service.RecordService;
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
@RequestMapping("/api/v1/records")
@RequiredArgsConstructor
public class RecordController {

    private final RecordService recordService;
    private final RecordMapper recordMapper;

    @GetMapping
    public List<RecordDto> findAll() {
        var records = this.recordService.findAll();
        return this.recordMapper.toDto(records);
    }

    @GetMapping("/{id}")
    public RecordDto findById(final @PathVariable("id") UUID id) {
        var record = this.recordService.findById(id);
        return this.recordMapper.toDto(record);
    }

    @PostMapping
    public RecordDto create(final @Valid @RequestBody RecordDto dto) {
        var record = this.recordService.create(dto);
        return this.recordMapper.toDto(record);
    }

    @PutMapping("/{id}")
    public RecordDto update(final @PathVariable("id") UUID id, final @Valid @RequestBody RecordDto dto) {
        var record = this.recordService.update(id, dto);
        return this.recordMapper.toDto(record);
    }

    @DeleteMapping("/{id}")
    public void delete(final @PathVariable("id") UUID id) {
        this.recordService.delete(id);
    }
}
