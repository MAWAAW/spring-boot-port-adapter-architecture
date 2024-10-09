package org.mawaaw.springbootportadapterarchitecture.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mawaaw.springbootportadapterarchitecture.infrastructure.entity.ReservationEntity;

import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Client {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<ReservationEntity> reservations;
}
