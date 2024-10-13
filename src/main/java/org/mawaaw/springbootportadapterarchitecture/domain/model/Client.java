package org.mawaaw.springbootportadapterarchitecture.domain.model;

import lombok.*;

import java.util.List;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString @Builder
public class Client {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<Reservation> reservations;
}
