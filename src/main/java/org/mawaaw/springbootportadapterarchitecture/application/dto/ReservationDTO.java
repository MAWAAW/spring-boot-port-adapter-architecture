package org.mawaaw.springbootportadapterarchitecture.application.dto;

import org.mawaaw.springbootportadapterarchitecture.domain.model.ReservationStatus;

import java.time.LocalDate;
import java.util.Objects;

public record ReservationDTO(Long id, LocalDate checkIn, LocalDate checkOut, ReservationStatus status, ClientDTO clientDTO, RoomDTO roomDTO) {

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
