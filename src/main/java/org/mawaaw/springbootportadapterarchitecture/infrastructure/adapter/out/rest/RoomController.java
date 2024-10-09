package org.mawaaw.springbootportadapterarchitecture.infrastructure.adapter.out.rest;

import org.mawaaw.springbootportadapterarchitecture.application.dto.RoomDTO;
import org.mawaaw.springbootportadapterarchitecture.application.service.RoomServiceImpl;
import org.mawaaw.springbootportadapterarchitecture.domain.exception.DuplicateRoomException;
import org.mawaaw.springbootportadapterarchitecture.domain.exception.RoomNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
@CrossOrigin("*")
public class RoomController {
    private final RoomServiceImpl roomService;

    public RoomController(RoomServiceImpl roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/add")
    public RoomDTO addRoom(@RequestBody RoomDTO roomDTO) throws DuplicateRoomException {
        return roomService.addRoom(roomDTO);
    }

    @PutMapping("/update")
    public RoomDTO updateRoom(@RequestBody RoomDTO roomDTO) throws RoomNotFoundException {
        return roomService.updateRoom(roomDTO);
    }

    @GetMapping("/all")
    public List<RoomDTO> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping("/{roomNumber}")
    public RoomDTO getRoomByNumber(@PathVariable String roomNumber) throws RoomNotFoundException {
        return roomService.getRoomByNumber(roomNumber);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteClientById(@PathVariable Long id) throws RoomNotFoundException {
        roomService.deleteRoomById(id);
    }

    @GetMapping("/id/{id}")
    public RoomDTO getById(@PathVariable Long id) throws RoomNotFoundException {
        return roomService.findById(id);
    }
}