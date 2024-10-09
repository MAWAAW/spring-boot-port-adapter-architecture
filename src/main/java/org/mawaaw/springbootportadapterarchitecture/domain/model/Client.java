package org.mawaaw.springbootportadapterarchitecture.domain.model;

import java.util.List;

public record Client(Long id, String firstName, String lastName, String email, String password, List<Reservation> reservations) {
}
