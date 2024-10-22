package com.elyashevich.tracker.service;

import com.elyashevich.tracker.api.dto.RecordDto;
import com.elyashevich.tracker.entity.Record;
import org.apache.coyote.BadRequestException;

import java.util.List;
import java.util.UUID;

public interface RecordService {

    List<Record> findAll();

    Record findById(final UUID id);

    Record create(final RecordDto dto);

    Record update(final UUID id, RecordDto dto);

    void delete(final UUID id);
}
