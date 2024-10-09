package org.mawaaw.springbootportadapterarchitecture.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mawaaw.springbootportadapterarchitecture.infrastructure.entity.ClientEntity;
import org.mawaaw.springbootportadapterarchitecture.infrastructure.entity.RoomEntity;

import java.time.LocalDate;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Reservation {
    private Long id;
    private LocalDate checkIN;
    private LocalDate checkOUT;
    private ReservationStatus status;
    private ClientEntity client;
    private RoomEntity room;
}
