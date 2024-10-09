package org.mawaaw.springbootportadapterarchitecture.application.port.out;

import org.mawaaw.springbootportadapterarchitecture.domain.model.Room;

import java.util.List;
import java.util.Optional;

public interface RoomRepository {
    Room save(Room room);
    Optional<Room> findById(Long id);
    List<Room> findAll();
    void deleteById(Long id);
    Room findByRoomNumber(String roomNumber);
    boolean existsByRoomNumber(String roomNumber);
    boolean existsById(Long id);
}
