package org.mawaaw.springbootportadapterarchitecture.infrastructure.adapter.in.persistence.jpa;

import org.mawaaw.springbootportadapterarchitecture.infrastructure.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomJpaRepository extends JpaRepository<RoomEntity, Long> {
}
