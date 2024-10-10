package org.mawaaw.springbootportadapterarchitecture.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.mawaaw.springbootportadapterarchitecture.domain.model.ReservationStatus;

import java.time.LocalDate;
import java.util.Objects;

public record ReservationDTO(
        Long id,
        @JsonFormat(pattern = "dd/MM/yyyy")
        @NotNull(message = "Check-in date must not be null")
        LocalDate checkIn,
        @JsonFormat(pattern = "dd/MM/yyyy")
        @NotNull(message = "Check-out date must not be null")
        @Future(message = "Check-out date must be in the future")
        LocalDate checkOut,
        ReservationStatus status,
        ClientDTO clientDTO,
        RoomDTO roomDTO
) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReservationDTO that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
