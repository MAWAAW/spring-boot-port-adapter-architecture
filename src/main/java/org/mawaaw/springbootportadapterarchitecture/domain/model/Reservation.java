package org.mawaaw.springbootportadapterarchitecture.domain.model;

import java.time.LocalDate;

public record Reservation(Long id, LocalDate checkIN, LocalDate checkOUT, Client client, Room room, ReservationStatus status) {
}
