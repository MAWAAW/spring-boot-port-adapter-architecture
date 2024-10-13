package org.mawaaw.springbootportadapterarchitecture.infrastructure.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mawaaw.springbootportadapterarchitecture.application.dto.ClientDTO;
import org.mawaaw.springbootportadapterarchitecture.domain.model.Client;
import org.mawaaw.springbootportadapterarchitecture.infrastructure.entity.ClientEntity;

import static org.junit.jupiter.api.Assertions.*;

public class ClientMapperTest {

    private ClientMapper clientMapper;
    private ClientDTO clientDTO;
    private Client client;
    private ClientEntity clientEntity;

    @BeforeEach
    public void setUp() {
        clientMapper = new ClientMapper();

        clientDTO = new ClientDTO(1L, "john", "doe", "john.doe@example.com", "password123");

        client = new Client();
        client.setId(1L);
        client.setFirstName("john");
        client.setLastName("doe");
        client.setEmail("john.doe@example.com");
        client.setPassword("password123");

        clientEntity = new ClientEntity();
        clientEntity.setId(1L);
        clientEntity.setFirstName("john");
        clientEntity.setLastName("doe");
        clientEntity.setEmail("john.doe@example.com");
        clientEntity.setPassword("password123");
    }

    @Test
    public void testFromClientEntityToClient() {
        Client result = clientMapper.fromClientEntityToClient(clientEntity);

        assertNotNull(result);
        assertEquals(clientEntity.getId(), result.getId());
        assertEquals(clientEntity.getFirstName(), result.getFirstName());
        assertEquals(clientEntity.getLastName(), result.getLastName());
        assertEquals(clientEntity.getEmail(), result.getEmail());
        assertEquals(clientEntity.getPassword(), result.getPassword());
    }

    @Test
    public void testFromClientToClientEntity() {
        ClientEntity result = clientMapper.fromClientToClientEntity(client);

        assertNotNull(result);
        assertEquals(client.getId(), result.getId());
        assertEquals(client.getFirstName(), result.getFirstName());
        assertEquals(client.getLastName(), result.getLastName());
        assertEquals(client.getEmail(), result.getEmail());
        assertEquals(client.getPassword(), result.getPassword());
    }

    @Test
    public void testFromClientToClientDTO() {
        ClientDTO result = clientMapper.fromClientToClientDTO(client);

        assertNotNull(result);
        assertEquals(client.getId(), result.id());
        assertEquals(client.getFirstName(), result.firstName());
        assertEquals(client.getLastName(), result.lastName());
        assertEquals(client.getEmail(), result.email());
        assertEquals(client.getPassword(), result.password());
    }

    @Test
    public void testFromClientDTOToClient() {
        Client result = clientMapper.fromClientDTOToClient(clientDTO);

        assertNotNull(result);
        assertEquals(clientDTO.id(), result.getId());
        assertEquals(clientDTO.firstName(), result.getFirstName());
        assertEquals(clientDTO.lastName(), result.getLastName());
        assertEquals(clientDTO.email(), result.getEmail());
        assertEquals(clientDTO.password(), result.getPassword());
    }
}
