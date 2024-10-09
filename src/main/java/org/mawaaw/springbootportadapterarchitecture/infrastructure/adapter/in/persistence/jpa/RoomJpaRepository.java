package org.mawaaw.springbootportadapterarchitecture.infrastructure.adapter.in.persistence.jpa;

import org.mawaaw.springbootportadapterarchitecture.infrastructure.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoomJpaRepository extends JpaRepository<RoomEntity, Long> {
    RoomEntity findByRoomNumber(String roomNumber);
    @Query("SELECT COUNT(r) > 0 FROM RoomEntity r WHERE r.roomNumber = ?1")
    boolean existsByRoomNumber(String roomNumber);
}
