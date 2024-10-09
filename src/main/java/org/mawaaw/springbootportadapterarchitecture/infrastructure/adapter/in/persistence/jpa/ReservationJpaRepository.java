package org.mawaaw.springbootportadapterarchitecture.infrastructure.adapter.in.persistence.jpa;

import org.mawaaw.springbootportadapterarchitecture.infrastructure.entity.ClientEntity;
import org.mawaaw.springbootportadapterarchitecture.infrastructure.entity.ReservationEntity;
import org.mawaaw.springbootportadapterarchitecture.infrastructure.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReservationJpaRepository extends JpaRepository<ReservationEntity, Long> {
    List<ReservationEntity> findByClient(ClientEntity client);
    List<ReservationEntity> findByRoom(RoomEntity room);

    @Query("SELECT r FROM ReservationEntity r " +
            "WHERE r.room.id = :roomId " +
            "AND ((r.checkIN >= :checkInDate AND r.checkIN < :checkOutDate) " +
            "     OR (r.checkOUT > :checkInDate AND r.checkOUT <= :checkOutDate) " +
            "     OR (r.checkIN <= :checkInDate AND r.checkOUT >= :checkOutDate))")
    List<ReservationEntity> findOverlappingReservations(@Param("roomId") Long roomId, @Param("checkInDate") LocalDate checkIN, @Param("checkOutDate") LocalDate checkOUT);
}
