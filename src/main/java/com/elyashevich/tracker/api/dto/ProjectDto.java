package com.elyashevich.tracker.api.dto;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

public record ProjectDto(

        UUID id,

        @NotNull(message = "Name must be not null.")
        @Length(
                min = 1,
                max = 255,
                message = "Name must be in {min} and {max}."
        )
        String name,

        @NotNull(message = "Description must be not null.")
        @Length(
                min = 1,
                max = 255,
                message = "Description must be in {min} and {max}."
        )
        String description
) {
}
