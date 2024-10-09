package org.mawaaw.springbootportadapterarchitecture.application.service;

import org.mawaaw.springbootportadapterarchitecture.application.dto.RoomDTO;
import org.mawaaw.springbootportadapterarchitecture.application.port.in.IRoomService;
import org.mawaaw.springbootportadapterarchitecture.domain.exception.DuplicateRoomException;
import org.mawaaw.springbootportadapterarchitecture.domain.exception.RoomNotFoundException;

import java.util.List;

public class RoomServiceImpl implements IRoomService {
    @Override
    public RoomDTO addRoom(RoomDTO room) throws DuplicateRoomException {
        return null;
    }

    @Override
    public RoomDTO updateRoom(RoomDTO room) throws RoomNotFoundException {
        return null;
    }

    @Override
    public List<RoomDTO> getAllRooms() {
        return List.of();
    }

    @Override
    public RoomDTO getRoomByNumber(String roomNumber) throws RoomNotFoundException {
        return null;
    }

    @Override
    public void deleteRoomById(Long id) throws RoomNotFoundException {

    }

    @Override
    public RoomDTO findById(Long id) throws RoomNotFoundException {
        return null;
    }
}
