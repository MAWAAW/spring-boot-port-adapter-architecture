package org.mawaaw.springbootportadapterarchitecture.application.port.in;

import org.mawaaw.springbootportadapterarchitecture.application.dto.RoomDTO;

import java.util.List;

public interface IRoomService {
    RoomDTO addRoom(RoomDTO room);
    RoomDTO updateRoom(RoomDTO room);
    List<RoomDTO> getAllRooms();
    RoomDTO getRoomByNumber(String roomNumber);
    void deleteRoomById(Long id);
    RoomDTO findById(Long id);
}
