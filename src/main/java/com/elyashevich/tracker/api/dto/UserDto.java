package com.elyashevich.tracker.api.dto;

import com.elyashevich.tracker.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.Set;
import java.util.UUID;

public record UserDto(

        UUID id,

        @NotNull(message = "Email must be not empty.")
        @Email(message = "Invalid email format.")
        String email,

        @NotNull(message = "Password mus be not empty.")
        @Length(
                min = 8,
                max = 255,
                message = "Password must be in {min} and {max}."
        )
        String password,

        Set<Role> roles
) {
}
