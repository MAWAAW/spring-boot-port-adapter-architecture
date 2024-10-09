package org.mawaaw.springbootportadapterarchitecture.domain.model;

import java.util.List;

public record Room(Long id, String roomNumber, RoomType roomType, List<Reservation> reservations) {
}
