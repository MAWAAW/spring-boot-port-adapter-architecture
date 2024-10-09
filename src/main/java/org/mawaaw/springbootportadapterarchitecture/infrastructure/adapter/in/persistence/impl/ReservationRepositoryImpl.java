package org.mawaaw.springbootportadapterarchitecture.infrastructure.adapter.in.persistence.impl;

import org.mawaaw.springbootportadapterarchitecture.application.port.out.ReservationRepository;
import org.mawaaw.springbootportadapterarchitecture.domain.model.Client;
import org.mawaaw.springbootportadapterarchitecture.domain.model.Reservation;
import org.mawaaw.springbootportadapterarchitecture.domain.model.Room;
import org.mawaaw.springbootportadapterarchitecture.infrastructure.adapter.in.persistence.jpa.ReservationJpaRepository;
import org.mawaaw.springbootportadapterarchitecture.infrastructure.entity.ClientEntity;
import org.mawaaw.springbootportadapterarchitecture.infrastructure.entity.ReservationEntity;
import org.mawaaw.springbootportadapterarchitecture.infrastructure.entity.RoomEntity;
import org.mawaaw.springbootportadapterarchitecture.infrastructure.mapper.ClientMapper;
import org.mawaaw.springbootportadapterarchitecture.infrastructure.mapper.ReservationMapper;
import org.mawaaw.springbootportadapterarchitecture.infrastructure.mapper.RoomMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ReservationRepositoryImpl implements ReservationRepository {

    private final ReservationJpaRepository reservationJpaRepository;
    private final ReservationMapper reservationMapper;
    private final RoomMapper roomMapper;
    private final ClientMapper clientMapper;

    public ReservationRepositoryImpl(ReservationJpaRepository reservationJpaRepository, ReservationMapper reservationMapper, RoomMapper roomMapper, ClientMapper clientMapper) {
        this.reservationJpaRepository = reservationJpaRepository;
        this.reservationMapper = reservationMapper;
        this.roomMapper = roomMapper;
        this.clientMapper = clientMapper;
    }

    @Override
    public Reservation save(Reservation reservation) {
        ReservationEntity reservationEntity = reservationMapper.fromReservationToReservationEntity(reservation);
        ReservationEntity savedReservation = reservationJpaRepository.save(reservationEntity);
        return reservationMapper.fromReservationEntityToReservation(savedReservation);
    }

    @Override
    public Optional<Reservation> findById(Long id) {
        Optional<ReservationEntity> reservationEntity = reservationJpaRepository.findById(id);
        return reservationEntity.map(reservationMapper::fromReservationEntityToReservation);
    }

    @Override
    public List<Reservation> findAll() {
        List<ReservationEntity> reservationEntities = reservationJpaRepository.findAll();
        return reservationEntities.stream().map(reservationMapper::fromReservationEntityToReservation).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        reservationJpaRepository.deleteById(id);
    }

    @Override
    public List<Reservation> findByClient(Client client) {
        ClientEntity clientEntity = clientMapper.fromClientToClientEntity(client);
        List<ReservationEntity> reservationEntities = reservationJpaRepository.findByClient(clientEntity);
        return reservationEntities.stream().map(reservationMapper::fromReservationEntityToReservation).collect(Collectors.toList());
    }

    @Override
    public List<Reservation> findByRoom(Room room) {
        RoomEntity roomEntity = roomMapper.fromRoomToRoomEntity(room);
        List<ReservationEntity> reservationEntities = reservationJpaRepository.findByRoom(roomEntity);
        return reservationEntities.stream().map(reservationMapper::fromReservationEntityToReservation).collect(Collectors.toList());
    }

    @Override
    public List<Reservation> findOverlappingReservations(Long roomId, LocalDate checkIN, LocalDate checkOUT) {
        List<ReservationEntity> reservationEntities = reservationJpaRepository.findOverlappingReservations(roomId, checkIN, checkOUT);
        return reservationEntities.stream().map(reservationMapper::fromReservationEntityToReservation).collect(Collectors.toList());
    }

    @Override
    public boolean existsById(Long reservationId) {
        return reservationJpaRepository.existsById(reservationId);
    }
}
