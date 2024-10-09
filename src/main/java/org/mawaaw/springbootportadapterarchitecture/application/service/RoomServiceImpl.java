package org.mawaaw.springbootportadapterarchitecture.application.service;

import org.mawaaw.springbootportadapterarchitecture.application.dto.RoomDTO;
import org.mawaaw.springbootportadapterarchitecture.application.port.in.IRoomService;
import org.mawaaw.springbootportadapterarchitecture.application.port.out.RoomRepository;
import org.mawaaw.springbootportadapterarchitecture.domain.exception.DuplicateRoomException;
import org.mawaaw.springbootportadapterarchitecture.domain.exception.RoomNotFoundException;
import org.mawaaw.springbootportadapterarchitecture.domain.model.Room;
import org.mawaaw.springbootportadapterarchitecture.infrastructure.mapper.RoomMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements IRoomService {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    public RoomServiceImpl(RoomRepository roomRepository, RoomMapper roomMapper) {
        this.roomRepository = roomRepository;
        this.roomMapper = roomMapper;
    }

    @Override
    public RoomDTO addRoom(RoomDTO roomDTO) throws DuplicateRoomException {
        if (roomRepository.existsByRoomNumber(roomDTO.roomNumber())) {
            throw new DuplicateRoomException("Room with number " + roomDTO.roomNumber() + " already exists.");
        }
        Room room = roomMapper.fromRoomDTOToRoom(roomDTO);
        Room savedRoom = roomRepository.save(room);
        return roomMapper.fromRoomToRoomDTO(savedRoom);
    }

    @Override
    public RoomDTO updateRoom(RoomDTO roomDTO) throws RoomNotFoundException {
        if (!roomRepository.existsById(roomDTO.id())) {
            throw new RoomNotFoundException("Room with ID " + roomDTO.id() + " not found.");
        }
        Room room = roomMapper.fromRoomDTOToRoom(roomDTO);
        Room savedRoom = roomRepository.save(room);
        return roomMapper.fromRoomToRoomDTO(savedRoom);
    }

    @Override
    public List<RoomDTO> getAllRooms() {
        List<Room> allRooms = roomRepository.findAll();
        return allRooms.stream()
                .map(roomMapper::fromRoomToRoomDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RoomDTO getRoomByNumber(String roomNumber) throws RoomNotFoundException {
        Room room = roomRepository.findByRoomNumber(roomNumber);
        if (room == null || !room.getRoomNumber().equals(roomNumber)) {
            throw new RoomNotFoundException("Room with number " + roomNumber + " not found.");
        }
        return roomMapper.fromRoomToRoomDTO(room);
    }

    @Override
    public void deleteRoomById(Long id) throws RoomNotFoundException {
        if(!roomRepository.existsById(id)) {
            throw new RoomNotFoundException("Room with ID " + id + " not found.");
        }
        Optional<Room> roomOpt = roomRepository.findById(id);
        if (roomOpt.isEmpty()) {
            throw new RoomNotFoundException("Room with ID " + id + " not found.");
        }
        roomRepository.deleteById(roomOpt.get().getId());
    }

    @Override
    public RoomDTO findById(Long id) throws RoomNotFoundException {
        Room room = roomRepository.findById(id).orElseThrow(() -> new RoomNotFoundException("Room with ID " + id + " not found."));
        return roomMapper.fromRoomToRoomDTO(room);
    }
}