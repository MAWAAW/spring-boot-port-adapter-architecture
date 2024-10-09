package org.mawaaw.springbootportadapterarchitecture.infrastructure.adapter.in.persistence.jpa;

import org.mawaaw.springbootportadapterarchitecture.infrastructure.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationJpaRepository extends JpaRepository<ReservationEntity, Long> {
}
