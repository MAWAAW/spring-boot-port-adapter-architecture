package org.mawaaw.springbootportadapterarchitecture.infrastructure.adapter.in.persistence.impl;

import org.mawaaw.springbootportadapterarchitecture.application.port.out.RoomRepository;
import org.mawaaw.springbootportadapterarchitecture.domain.model.Room;
import org.mawaaw.springbootportadapterarchitecture.infrastructure.adapter.in.persistence.jpa.RoomJpaRepository;
import org.mawaaw.springbootportadapterarchitecture.infrastructure.entity.RoomEntity;
import org.mawaaw.springbootportadapterarchitecture.infrastructure.mapper.RoomMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class RoomRepositoryImpl implements RoomRepository {

    private final RoomJpaRepository roomJpaRepository;
    private final RoomMapper roomMapper;

    public RoomRepositoryImpl(RoomJpaRepository roomJpaRepository, RoomMapper roomMapper) {
        this.roomJpaRepository = roomJpaRepository;
        this.roomMapper = roomMapper;
    }

    @Override
    public Room save(Room room) {
        RoomEntity roomEntity = roomMapper.fromRoomToRoomEntity(room);
        RoomEntity savedRoomEntity = roomJpaRepository.save(roomEntity);
        return roomMapper.fromRoomEntityToRoom(savedRoomEntity);
    }

    @Override
    public Optional<Room> findById(Long id) {
        Optional<RoomEntity> roomEntityOpt = roomJpaRepository.findById(id);
        return roomEntityOpt.map(roomMapper::fromRoomEntityToRoom);
    }

    @Override
    public List<Room> findAll() {
        List<RoomEntity> roomEntities = roomJpaRepository.findAll();
        return roomEntities.stream().map(roomMapper::fromRoomEntityToRoom).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        roomJpaRepository.deleteById(id);
    }

    @Override
    public Room findByRoomNumber(String roomNumber) {
        RoomEntity roomEntity = roomJpaRepository.findByRoomNumber(roomNumber);
        return roomMapper.fromRoomEntityToRoom(roomEntity);
    }

    @Override
    public boolean existsByRoomNumber(String roomNumber) {
        return roomJpaRepository.existsByRoomNumber(roomNumber);
    }

    @Override
    public boolean existsById(Long id) {
        return roomJpaRepository.existsById(id);
    }
}