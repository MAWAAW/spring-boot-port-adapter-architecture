package org.mawaaw.springbootportadapterarchitecture.application.port.out;

import org.mawaaw.springbootportadapterarchitecture.domain.model.Client;
import org.mawaaw.springbootportadapterarchitecture.domain.model.Reservation;
import org.mawaaw.springbootportadapterarchitecture.domain.model.Room;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository {
    Reservation save(Reservation reservation);
    Optional<Reservation> findById(Long id);
    List<Reservation> findAll();
    void deleteById(Long id);
    List<Reservation> findByClient(Client client);
    List<Reservation> findByRoom(Room room);
    List<Reservation> findOverlappingReservations(Long roomId, LocalDate checkIN, LocalDate checkOUT);
}
