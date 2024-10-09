package org.mawaaw.springbootportadapterarchitecture.infrastructure.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mawaaw.springbootportadapterarchitecture.domain.model.RoomType;

import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class RoomEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Room number is required")
    private String roomNumber;
    private RoomType roomType;
    @OneToMany(mappedBy = "room")
    private List<ReservationEntity> reservations;
}
