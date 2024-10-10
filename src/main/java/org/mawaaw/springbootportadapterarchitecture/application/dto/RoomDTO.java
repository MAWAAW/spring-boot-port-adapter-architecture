package org.mawaaw.springbootportadapterarchitecture.application.dto;

import jakarta.validation.constraints.NotBlank;
import org.mawaaw.springbootportadapterarchitecture.domain.model.RoomType;

import java.util.Objects;

public record RoomDTO(
        Long id,
        @NotBlank(message = "Room number is required")
        String roomNumber,
        RoomType roomType
) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoomDTO roomDTO)) return false;
        return Objects.equals(id, roomDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
