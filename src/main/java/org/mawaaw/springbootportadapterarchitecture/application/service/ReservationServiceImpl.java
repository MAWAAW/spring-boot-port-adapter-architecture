package org.mawaaw.springbootportadapterarchitecture.application.service;

import org.mawaaw.springbootportadapterarchitecture.application.dto.ClientDTO;
import org.mawaaw.springbootportadapterarchitecture.application.dto.ReservationDTO;
import org.mawaaw.springbootportadapterarchitecture.application.dto.RoomDTO;
import org.mawaaw.springbootportadapterarchitecture.application.port.in.IReservationService;
import org.mawaaw.springbootportadapterarchitecture.domain.exception.*;

import java.time.LocalDate;
import java.util.List;

public class ReservationServiceImpl implements IReservationService {
    @Override
    public boolean isRoomAvailable(RoomDTO roomDTO, LocalDate checkINDate, LocalDate checkOUTDate) {
        return false;
    }

    @Override
    public ReservationDTO makeReservation(ClientDTO client, RoomDTO roomDTO, LocalDate CheckINDate, LocalDate CheckOUTDate) throws RoomNotFoundException, RoomNotAvailableException, ClientNotFoundException {
        return null;
    }

    @Override
    public void cancelReservation(Long reservationId) throws ReservationNotFoundException {

    }

    @Override
    public List<ReservationDTO> getReservationsByClient(ClientDTO client) throws ClientNotFoundException, ClientSaveException {
        return List.of();
    }

    @Override
    public List<ReservationDTO> getReservationsByRoom(RoomDTO room) {
        return List.of();
    }
}
