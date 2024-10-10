package org.mawaaw.springbootportadapterarchitecture.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ClientDTO(
        Long id,
        String firstName,
        String lastName,
        @Email(message = "Invalid email format")
        @NotBlank(message = "Email is required")
        String email,
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        String password
) { }
