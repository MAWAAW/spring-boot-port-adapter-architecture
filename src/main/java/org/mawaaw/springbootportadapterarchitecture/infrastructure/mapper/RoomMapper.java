package org.mawaaw.springbootportadapterarchitecture.infrastructure.mapper;

import org.mawaaw.springbootportadapterarchitecture.application.dto.RoomDTO;
import org.mawaaw.springbootportadapterarchitecture.domain.model.Room;
import org.mawaaw.springbootportadapterarchitecture.infrastructure.entity.RoomEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class RoomMapper {
    public Room fromRoomEntityToRoom(RoomEntity roomEntity) {
        Room room = new Room();
        BeanUtils.copyProperties(roomEntity, room);
        return room;
    }

    public RoomEntity fromRoomToRoomEntity(Room room) {
        RoomEntity roomEntity = new RoomEntity();
        BeanUtils.copyProperties(room, roomEntity);
        return roomEntity;
    }

    public RoomDTO fromRoomToRoomDTO(Room room) {
        return new RoomDTO(
                room.getId(),
                room.getRoomNumber(),
                room.getRoomType()
        );
    }

    public Room fromRoomDTOToRoom(RoomDTO roomDTO) {
        Room room = new Room();
        BeanUtils.copyProperties(roomDTO, room);
        return room;
    }
}