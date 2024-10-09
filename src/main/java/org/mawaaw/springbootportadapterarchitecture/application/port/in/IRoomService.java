package org.mawaaw.springbootportadapterarchitecture.application.port.in;

import org.mawaaw.springbootportadapterarchitecture.application.dto.RoomDTO;
import org.mawaaw.springbootportadapterarchitecture.domain.exception.DuplicateRoomException;
import org.mawaaw.springbootportadapterarchitecture.domain.exception.RoomNotFoundException;

import java.util.List;

public interface IRoomService {
    RoomDTO addRoom(RoomDTO room) throws DuplicateRoomException;
    RoomDTO updateRoom(RoomDTO room) throws RoomNotFoundException;
    List<RoomDTO> getAllRooms();
    RoomDTO getRoomByNumber(String roomNumber) throws RoomNotFoundException;
    void deleteRoomById(Long id) throws RoomNotFoundException;
    RoomDTO findById(Long id) throws RoomNotFoundException;
}
