package org.mawaaw.springbootportadapterarchitecture.application.port.in;

import org.mawaaw.springbootportadapterarchitecture.application.dto.ClientDTO;
import org.mawaaw.springbootportadapterarchitecture.application.dto.ReservationDTO;
import org.mawaaw.springbootportadapterarchitecture.application.dto.RoomDTO;

import java.time.LocalDate;
import java.util.List;

public interface IReservationService {
    boolean isRoomAvailable(RoomDTO roomDTO, LocalDate checkINDate, LocalDate checkOUTDate);
    ReservationDTO makeReservation(ClientDTO client, RoomDTO roomDTO, LocalDate checkINDate, LocalDate checkOUTDate);
    void cancelReservation(Long reservationId);
    List<ReservationDTO> getReservationsByClient(ClientDTO client);
    List<ReservationDTO> getReservationsByRoom(RoomDTO room);
}
