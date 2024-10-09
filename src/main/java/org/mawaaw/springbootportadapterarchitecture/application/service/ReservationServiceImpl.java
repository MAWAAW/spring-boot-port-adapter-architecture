package org.mawaaw.springbootportadapterarchitecture.application.service;

import org.mawaaw.springbootportadapterarchitecture.application.dto.ClientDTO;
import org.mawaaw.springbootportadapterarchitecture.application.dto.ReservationDTO;
import org.mawaaw.springbootportadapterarchitecture.application.dto.RoomDTO;
import org.mawaaw.springbootportadapterarchitecture.application.port.in.IReservationService;
import org.mawaaw.springbootportadapterarchitecture.application.port.in.IRoomService;
import org.mawaaw.springbootportadapterarchitecture.application.port.out.ClientRepository;
import org.mawaaw.springbootportadapterarchitecture.application.port.out.ReservationRepository;
import org.mawaaw.springbootportadapterarchitecture.domain.exception.*;
import org.mawaaw.springbootportadapterarchitecture.domain.model.Client;
import org.mawaaw.springbootportadapterarchitecture.domain.model.Reservation;
import org.mawaaw.springbootportadapterarchitecture.domain.model.ReservationStatus;
import org.mawaaw.springbootportadapterarchitecture.domain.model.Room;
import org.mawaaw.springbootportadapterarchitecture.infrastructure.mapper.ClientMapper;
import org.mawaaw.springbootportadapterarchitecture.infrastructure.mapper.ReservationMapper;
import org.mawaaw.springbootportadapterarchitecture.infrastructure.mapper.RoomMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements IReservationService {

    private final ReservationRepository reservationRepository;
    private final ClientRepository clientRepository;
    private final IRoomService roomService;
    private final ReservationMapper reservationMapper;
    private final RoomMapper roomMapper;
    private final ClientMapper clientMapper;

    public ReservationServiceImpl(ReservationRepository reservationRepository, ClientRepository clientRepository, IRoomService roomService, ReservationMapper reservationMapper, RoomMapper roomMapper, ClientMapper clientMapper) {
        this.reservationRepository = reservationRepository;
        this.clientRepository = clientRepository;
        this.roomService = roomService;
        this.reservationMapper = reservationMapper;
        this.roomMapper = roomMapper;
        this.clientMapper = clientMapper;
    }

    @Override
    public boolean isRoomAvailable(RoomDTO roomDTO, LocalDate checkINDate, LocalDate checkOUTDate) {
        Room room = roomMapper.fromRoomDTOToRoom(roomDTO);

        // Ensure that room is not null before accessing its ID
        if (room == null) {
            return false; // Room not found, so it is not available
        }

        // Retrieve overlapping reservations
        List<Reservation> overlappingReservations = reservationRepository.findOverlappingReservations(
                room.getId(),
                checkINDate,
                checkOUTDate
        );

        // Convert List<Reservation> to List<ReservationDTO>
        List<ReservationDTO> overlappingReservationsDTO = overlappingReservations.stream()
                .map(reservationMapper::fromReservationToReservationDTO)
                .toList();

        // Check if the room is available (no overlapping reservations)
        return overlappingReservationsDTO.isEmpty();
    }

    @Override
    public ReservationDTO makeReservation(ClientDTO clientDTO, RoomDTO roomDTO, LocalDate checkINDate, LocalDate checkOUTDate) throws RoomNotFoundException, RoomNotAvailableException, ClientNotFoundException {
        if(!clientRepository.existsById(clientDTO.id())) {
            throw new ClientNotFoundException("Client with ID " + clientDTO.id() + " not found.");
        }
        RoomDTO foundRoom = roomService.getRoomByNumber(roomDTO.roomNumber());
        if (foundRoom == null) {
            throw new RoomNotFoundException("Room with number " + roomDTO.roomNumber() + " not found.");
        }
        if (!isRoomAvailable(foundRoom, checkINDate, checkOUTDate)) {
            throw new RoomNotAvailableException("Room with number " + foundRoom.roomNumber() + " is not available for the specified dates.");
        }
        if (checkINDate.isAfter(checkOUTDate)) {
            throw new IllegalArgumentException("Check-in date must be before the check-out date.");
        }

        ReservationDTO reservationDTO = new ReservationDTO(
                null,
                checkINDate,
                checkOUTDate,
                ReservationStatus.PENDING,
                clientDTO,
                foundRoom
        );
        Reservation reservation = reservationMapper.fromReservationDTOToReservation(reservationDTO);
        Reservation savedReservation = reservationRepository.save(reservation);
        return reservationMapper.fromReservationToReservationDTO(savedReservation);
    }

    @Override
    public void cancelReservation(Long reservationId) throws ReservationNotFoundException {
        if (!reservationRepository.existsById(reservationId) || reservationRepository.findById(reservationId).isEmpty()) {
            throw new ReservationNotFoundException("Reservation with ID " + reservationId + " not found.");
        }
        reservationRepository.deleteById(reservationId);
    }

    @Override
    public List<ReservationDTO> getReservationsByClient(ClientDTO clientDTO) throws ClientNotFoundException, ClientSaveException {
        Client client = clientMapper.fromClientDTOToClient(clientDTO);

        // Check if the client exists in the database
        Client existingClient = clientRepository.findByEmail(client.getEmail());

        if (existingClient != null) {
            // If the client exists, use the existing client to fetch reservations
            client = existingClient;
        } else {
            try {
                // If the client does not exist, save it in the database
                client = clientRepository.save(client);
            } catch (DataAccessException ex) {
                // If there's an issue while saving the client, throw a ClientSaveException
                throw new ClientSaveException("Failed to save the client in the database.", ex);
            }
        }

        // If the client is still null at this point, it means the client was not found in the database
        if (client == null) {
            throw new ClientNotFoundException("Client not found in the database.");
        }

        List<Reservation> reservations = reservationRepository.findByClient(client);

        return reservations.stream()
                .map(reservationMapper::fromReservationToReservationDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReservationDTO> getReservationsByRoom(RoomDTO roomDTO) {
        Room room = roomMapper.fromRoomDTOToRoom(roomDTO);

        List<Reservation> reservations = reservationRepository.findByRoom(room);

        return reservations.stream()
                .map(reservationMapper::fromReservationToReservationDTO)
                .collect(Collectors.toList());
    }
}
