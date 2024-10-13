package org.mawaaw.springbootportadapterarchitecture.domain.model;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString @Builder
public class Reservation {
    private Long id;
    private LocalDate checkIN;
    private LocalDate checkOUT;
    private ReservationStatus status;
    private Client client;
    private Room room;
}
