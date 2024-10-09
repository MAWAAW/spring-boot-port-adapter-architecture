package org.mawaaw.springbootportadapterarchitecture.infrastructure.mapper;

import org.mawaaw.springbootportadapterarchitecture.application.dto.ReservationDTO;
import org.mawaaw.springbootportadapterarchitecture.domain.model.Reservation;
import org.mawaaw.springbootportadapterarchitecture.infrastructure.entity.ReservationEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ReservationMapper {
    private final ClientMapper clientMapper;
    private final RoomMapper roomMapper;

    public ReservationMapper(ClientMapper clientMapper, RoomMapper roomMapper) {
        this.clientMapper = clientMapper;
        this.roomMapper = roomMapper;
    }

    public Reservation fromReservationEntityToReservation(ReservationEntity reservationEntity) {
        Reservation reservation = new Reservation();
        BeanUtils.copyProperties(reservationEntity, reservation);
        return reservation;
    }

    public ReservationEntity fromReservationToReservationEntity(Reservation reservation) {
        ReservationEntity reservationEntity = new ReservationEntity();
        BeanUtils.copyProperties(reservation, reservationEntity);
        return reservationEntity;
    }

    public ReservationDTO fromReservationToReservationDTO(Reservation reservation) {
        return new ReservationDTO(
                reservation.getId(),
                reservation.getCheckIN(),
                reservation.getCheckOUT(),
                reservation.getStatus(),
                clientMapper.fromClientToClientDTO(reservation.getClient()),
                roomMapper.fromRoomToRoomDTO(reservation.getRoom())
        );
    }

    public Reservation fromReservationDTOToReservation(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation();
        BeanUtils.copyProperties(reservationDTO, reservation);
        return reservation;
    }
}