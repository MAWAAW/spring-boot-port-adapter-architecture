package org.mawaaw.springbootportadapterarchitecture.application.port.in;

import org.mawaaw.springbootportadapterarchitecture.application.dto.ClientDTO;
import org.mawaaw.springbootportadapterarchitecture.application.dto.ReservationDTO;
import org.mawaaw.springbootportadapterarchitecture.application.dto.RoomDTO;
import org.mawaaw.springbootportadapterarchitecture.domain.exception.*;

import java.time.LocalDate;
import java.util.List;

public interface IReservationService {
    boolean isRoomAvailable(RoomDTO roomDTO, LocalDate checkINDate, LocalDate checkOUTDate);
    ReservationDTO makeReservation(ClientDTO client, RoomDTO roomDTO, LocalDate CheckINDate, LocalDate CheckOUTDate) throws RoomNotFoundException, RoomNotAvailableException, ClientNotFoundException;
    void cancelReservation(Long reservationId) throws ReservationNotFoundException;
    List<ReservationDTO> getReservationsByClient(ClientDTO client) throws ClientNotFoundException, ClientSaveException;
    List<ReservationDTO> getReservationsByRoom(RoomDTO room);
}
