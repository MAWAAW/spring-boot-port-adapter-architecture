package org.mawaaw.springbootportadapterarchitecture.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Room {
    private Long id;
    private String roomNumber;
    private RoomType roomType;
    private List<Reservation> reservations;
}
