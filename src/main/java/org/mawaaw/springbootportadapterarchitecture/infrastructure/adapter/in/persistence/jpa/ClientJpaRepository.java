package org.mawaaw.springbootportadapterarchitecture.infrastructure.adapter.in.persistence.jpa;

import org.mawaaw.springbootportadapterarchitecture.infrastructure.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientJpaRepository extends JpaRepository<ClientEntity, Long> {
}
