package org.mawaaw.springbootportadapterarchitecture.infrastructure.adapter.out.rest;

import org.mawaaw.springbootportadapterarchitecture.application.dto.ClientDTO;
import org.mawaaw.springbootportadapterarchitecture.application.dto.ReservationDTO;
import org.mawaaw.springbootportadapterarchitecture.application.dto.RoomDTO;
import org.mawaaw.springbootportadapterarchitecture.application.service.ReservationServiceImpl;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reservations")
@CrossOrigin("*")
public class ReservationController {

    private final ReservationServiceImpl reservationService;

    public ReservationController(ReservationServiceImpl reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/check-availability")
    public ResponseEntity<Boolean> isRoomAvailable(
            @RequestBody RoomDTO roomDTO,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkINDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOUTDate) {
        boolean available = reservationService.isRoomAvailable(roomDTO, checkINDate, checkOUTDate);
        return new ResponseEntity<>(available, HttpStatus.OK);
    }

    @PostMapping("/make-reservation")
    public ResponseEntity<ReservationDTO> makeReservation(@RequestBody ReservationDTO reservationRequestDTO) {
        ReservationDTO createdReservation = reservationService.makeReservation(
                reservationRequestDTO.clientDTO(),
                reservationRequestDTO.roomDTO(),
                reservationRequestDTO.checkIn(),
                reservationRequestDTO.checkOut()
        );
        return new ResponseEntity<>(createdReservation, HttpStatus.CREATED);
    }

    @DeleteMapping("/cancel-reservation/{reservationId}")
    public ResponseEntity<Void> cancelReservation(@PathVariable Long reservationId) {
        reservationService.cancelReservation(reservationId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/client/{email}")
    public ResponseEntity<List<ReservationDTO>> getReservationsByClient(@PathVariable String email) {
        ClientDTO clientDTO = new ClientDTO(null, null, null, email, null);
        List<ReservationDTO> reservations = reservationService.getReservationsByClient(clientDTO);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping("/room/{roomNumber}")
    public ResponseEntity<List<ReservationDTO>> getReservationsByRoom(@PathVariable String roomNumber) {
        RoomDTO roomDTO = new RoomDTO(null, roomNumber, null);
        List<ReservationDTO> reservations = reservationService.getReservationsByRoom(roomDTO);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }
}
