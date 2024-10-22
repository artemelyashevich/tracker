package com.elyashevich.tracker.api.mapper;

import com.elyashevich.tracker.api.dto.RecordDto;
import com.elyashevich.tracker.entity.Record;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RecordMapper extends Mappable<Record, RecordDto> {
}
