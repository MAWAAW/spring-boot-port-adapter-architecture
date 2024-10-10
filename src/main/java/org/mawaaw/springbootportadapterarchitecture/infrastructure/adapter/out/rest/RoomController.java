package org.mawaaw.springbootportadapterarchitecture.infrastructure.adapter.out.rest;

import org.mawaaw.springbootportadapterarchitecture.application.dto.RoomDTO;
import org.mawaaw.springbootportadapterarchitecture.application.service.RoomServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<RoomDTO> addRoom(@RequestBody RoomDTO roomDTO) {
        RoomDTO createdRoom = roomService.addRoom(roomDTO);
        return new ResponseEntity<>(createdRoom, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<RoomDTO> updateRoom(@RequestBody RoomDTO roomDTO) {
        RoomDTO updatedRoom = roomService.updateRoom(roomDTO);
        return new ResponseEntity<>(updatedRoom, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<RoomDTO>> getAllRooms() {
        List<RoomDTO> rooms = roomService.getAllRooms();
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    @GetMapping("/{roomNumber}")
    public ResponseEntity<RoomDTO> getRoomByNumber(@PathVariable String roomNumber) {
        RoomDTO room = roomService.getRoomByNumber(roomNumber);
        return new ResponseEntity<>(room, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRoomById(@PathVariable Long id) {
        roomService.deleteRoomById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<RoomDTO> getById(@PathVariable Long id) {
        RoomDTO room = roomService.findById(id);
        return new ResponseEntity<>(room, HttpStatus.OK);
    }
}