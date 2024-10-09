package org.mawaaw.springbootportadapterarchitecture.infrastructure.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mawaaw.springbootportadapterarchitecture.domain.model.ReservationStatus;

import java.time.LocalDate;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class ReservationEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Check-in date must not be null")
    private LocalDate checkIN;
    @NotNull(message = "Check-out date must not be null")
    @Future(message = "Check-out date must be in the future")
    private LocalDate checkOUT;
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;
    @ManyToOne(fetch = FetchType.LAZY)
    private ClientEntity client;
    @ManyToOne(fetch = FetchType.LAZY)
    private RoomEntity room;
}
