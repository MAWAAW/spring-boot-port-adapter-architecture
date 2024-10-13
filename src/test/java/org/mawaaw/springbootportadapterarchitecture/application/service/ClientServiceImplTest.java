package org.mawaaw.springbootportadapterarchitecture.application.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mawaaw.springbootportadapterarchitecture.application.dto.ClientDTO;
import org.mawaaw.springbootportadapterarchitecture.application.port.out.ClientRepository;
import org.mawaaw.springbootportadapterarchitecture.domain.exception.ClientNotFoundException;
import org.mawaaw.springbootportadapterarchitecture.domain.exception.EmailAlreadyExistsException;
import org.mawaaw.springbootportadapterarchitecture.domain.model.Client;
import org.mawaaw.springbootportadapterarchitecture.infrastructure.mapper.ClientMapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientServiceImplTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ClientMapper clientMapper;

    @InjectMocks
    private ClientServiceImpl clientService;

    private ClientDTO clientDTO;
    private Client client;

    @BeforeEach
    public void setUp() {
        clientDTO = new ClientDTO(1L, "john", "doe", "john.doe@example.com", "password123");
        client = new Client(1L, "john", "doe", "john.doe@example.com", "password123",null);
    }

    @Test
    public void testRegisterClient_Success() {
        when(clientRepository.existsByEmail(clientDTO.email())).thenReturn(false);
        when(clientMapper.fromClientDTOToClient(clientDTO)).thenReturn(client);
        when(clientRepository.save(client)).thenReturn(client);
        when(clientMapper.fromClientToClientDTO(client)).thenReturn(clientDTO);

        ClientDTO result = clientService.registerClient(clientDTO);

        assertNotNull(result);
        assertEquals(clientDTO.email(), result.email());
        verify(clientRepository).save(client);
    }

    @Test
    public void testRegisterClient_EmailAlreadyExists() {
        when(clientRepository.existsByEmail(clientDTO.email())).thenReturn(true);

        assertThrows(EmailAlreadyExistsException.class, () -> {
            clientService.registerClient(clientDTO);
        });

        verify(clientRepository, never()).save(any());
    }

    @Test
    public void testAuthenticateClient_Success() {
        when(clientRepository.findByEmail(clientDTO.email())).thenReturn(client);
        when(clientMapper.fromClientToClientDTO(client)).thenReturn(clientDTO);

        ClientDTO result = clientService.authenticateClient(clientDTO.email(), clientDTO.password());

        assertNotNull(result);
        assertEquals(clientDTO.email(), result.email());
        verify(clientRepository).findByEmail(clientDTO.email());
    }

    @Test
    public void testAuthenticateClient_InvalidCredentials() {
        when(clientRepository.findByEmail(clientDTO.email())).thenReturn(client);

        assertThrows(ClientNotFoundException.class, () -> {
            clientService.authenticateClient(clientDTO.email(), "wrongpassword");
        });

        verify(clientRepository).findByEmail(clientDTO.email());
    }

    @Test
    public void testUpdateClient_Success() {
        when(clientMapper.fromClientDTOToClient(clientDTO)).thenReturn(client);
        when(clientRepository.save(client)).thenReturn(client);
        when(clientMapper.fromClientToClientDTO(client)).thenReturn(clientDTO);

        ClientDTO result = clientService.updateClient(clientDTO);

        assertNotNull(result);
        assertEquals(clientDTO.email(), result.email());
        verify(clientRepository).save(client);
    }

    @Test
    public void testGetClientByMail_Success() {
        when(clientRepository.findByEmail(clientDTO.email())).thenReturn(client);
        when(clientMapper.fromClientToClientDTO(client)).thenReturn(clientDTO);

        ClientDTO result = clientService.getClientByMail(clientDTO.email());

        assertNotNull(result);
        assertEquals(clientDTO.email(), result.email());
        verify(clientRepository).findByEmail(clientDTO.email());
    }

    @Test
    public void testGetClientByMail_NotFound() {
        when(clientRepository.findByEmail(clientDTO.email())).thenReturn(null);

        assertThrows(ClientNotFoundException.class, () -> {
            clientService.getClientByMail(clientDTO.email());
        });

        verify(clientRepository).findByEmail(clientDTO.email());
    }

    @Test
    public void testGetAllClients() {
        List<Client> clients = Arrays.asList(client);
        List<ClientDTO> clientDTOs = Arrays.asList(clientDTO);

        when(clientRepository.findAll()).thenReturn(clients);
        when(clientMapper.fromClientToClientDTO(client)).thenReturn(clientDTO);

        List<ClientDTO> result = clientService.getAllClients();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(clientRepository).findAll();
    }

    @Test
    public void testDeleteClientById_Success() {
        when(clientRepository.findById(client.getId())).thenReturn(Optional.of(client));

        clientService.deleteClientById(client.getId());

        verify(clientRepository).deleteById(client.getId());
    }

    @Test
    public void testDeleteClientById_NotFound() {
        when(clientRepository.findById(client.getId())).thenReturn(Optional.empty());

        assertThrows(ClientNotFoundException.class, () -> {
            clientService.deleteClientById(client.getId());
        });

        verify(clientRepository, never()).deleteById(anyLong());
    }
}
