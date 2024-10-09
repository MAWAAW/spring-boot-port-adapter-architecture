package org.mawaaw.springbootportadapterarchitecture.infrastructure.adapter.out.rest;

import org.mawaaw.springbootportadapterarchitecture.application.dto.ClientDTO;
import org.mawaaw.springbootportadapterarchitecture.application.dto.ReservationDTO;
import org.mawaaw.springbootportadapterarchitecture.application.dto.RoomDTO;
import org.mawaaw.springbootportadapterarchitecture.application.service.ReservationServiceImpl;
import org.mawaaw.springbootportadapterarchitecture.domain.exception.*;
import org.springframework.format.annotation.DateTimeFormat;
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
    public boolean isRoomAvailable(@RequestBody RoomDTO roomDTO,
                                   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkINDate,
                                   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOUTDate) {
        return reservationService.isRoomAvailable(roomDTO, checkINDate, checkOUTDate);
    }

    @PostMapping("/make-reservation")
    public ReservationDTO makeReservation(@RequestBody ReservationDTO reservationRequestDTO) throws RoomNotFoundException, RoomNotAvailableException, ClientNotFoundException {
        return reservationService.makeReservation(
                reservationRequestDTO.clientDTO(),
                reservationRequestDTO.roomDTO(),
                reservationRequestDTO.checkIn(),
                reservationRequestDTO.checkOut()
        );
    }

    @DeleteMapping("/cancel-reservation/{reservationId}")
    public void cancelReservation(@PathVariable Long reservationId) throws ReservationNotFoundException {
        reservationService.cancelReservation(reservationId);
    }

    @GetMapping("/client/{email}")
    public List<ReservationDTO> getReservationsByClient(@PathVariable String email) throws ClientNotFoundException, ClientSaveException {
        ClientDTO clientDTO = new ClientDTO(null,null,null,email,null);
        return reservationService.getReservationsByClient(clientDTO);
    }

    @GetMapping("/room/{roomNumber}")
    public List<ReservationDTO> getReservationsByRoom(@PathVariable String roomNumber) {
        RoomDTO roomDTO = new RoomDTO(null,roomNumber,null);
        return reservationService.getReservationsByRoom(roomDTO);
    }
}
