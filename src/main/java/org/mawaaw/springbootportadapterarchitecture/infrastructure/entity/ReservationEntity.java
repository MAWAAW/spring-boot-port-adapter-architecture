package org.mawaaw.springbootportadapterarchitecture.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;
import org.mawaaw.springbootportadapterarchitecture.domain.model.ReservationStatus;

import java.time.LocalDate;

@Entity
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString @Builder
@Table(name = "reservation")
public class ReservationEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "check_in")
    private LocalDate checkIN;
    @Column(name = "check_out")
    private LocalDate checkOUT;
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;
    @ManyToOne(fetch = FetchType.LAZY)
    private ClientEntity client;
    @ManyToOne(fetch = FetchType.LAZY)
    private RoomEntity room;
}
