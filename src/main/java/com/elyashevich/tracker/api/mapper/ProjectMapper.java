package com.elyashevich.tracker.api.mapper;

import com.elyashevich.tracker.api.dto.ProjectDto;
import com.elyashevich.tracker.entity.Project;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectMapper extends Mappable<Project, ProjectDto> {
}
