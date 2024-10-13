package org.mawaaw.springbootportadapterarchitecture.domain.model;

import lombok.*;

import java.util.List;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString @Builder
public class Room {
    private Long id;
    private String roomNumber;
    private RoomType roomType;
    private List<Reservation> reservations;
}
