package com.elyashevich.tracker.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

public record RecordDto(
        UUID id,

        @NotNull(message = "User id must be not empty.")
        UUID userId,

        @NotNull(message = "Project id must be not empty.")
        UUID projectId,

        @NotNull(message = "Description must be not empty.")
        String description,

        @NotNull(message = "Start time must be not empty.")
        LocalDateTime startTime,

        @NotNull(message = "End time must be not empty.")
        LocalDateTime endTime
) {
}
