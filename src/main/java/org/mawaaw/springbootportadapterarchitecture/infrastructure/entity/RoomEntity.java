package org.mawaaw.springbootportadapterarchitecture.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mawaaw.springbootportadapterarchitecture.domain.model.RoomType;

import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Builder
@Table(name = "room")
public class RoomEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "room_number")
    private String roomNumber;
    @Enumerated(EnumType.STRING)
    private RoomType roomType;
    @OneToMany(mappedBy = "room")
    private List<ReservationEntity> reservations;
}
