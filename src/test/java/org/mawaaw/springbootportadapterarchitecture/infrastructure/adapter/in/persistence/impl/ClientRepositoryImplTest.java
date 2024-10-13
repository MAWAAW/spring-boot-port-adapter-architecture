package org.mawaaw.springbootportadapterarchitecture.infrastructure.adapter.in.persistence.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mawaaw.springbootportadapterarchitecture.application.port.out.ClientRepository;
import org.mawaaw.springbootportadapterarchitecture.domain.model.Client;
import org.mawaaw.springbootportadapterarchitecture.infrastructure.adapter.in.persistence.jpa.ClientJpaRepository;
import org.mawaaw.springbootportadapterarchitecture.infrastructure.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class ClientRepositoryImplTest {

    @Autowired
    private ClientRepository clientRepository;

    private Client client;

    @BeforeEach
    public void setUp() {
        client = new Client();
        //client.setId(1L);
        client.setFirstName("john");
        client.setLastName("doe");
        client.setEmail("john.doe@example.com");
        client.setPassword("password123");
    }

    @Test
    public void testSave() {
        Client savedClient = clientRepository.save(client);

        assertNotNull(savedClient.getId());
        assertEquals(client.getEmail(), savedClient.getEmail());

        Optional<Client> clientOpt = clientRepository.findById(savedClient.getId());
        assertTrue(clientOpt.isPresent());
        assertEquals(client.getEmail(), clientOpt.get().getEmail());
    }

    @Test
    public void testFindById() {
        Client savedClient = clientRepository.save(client);

        Optional<Client> foundClient = clientRepository.findById(savedClient.getId());

        assertTrue(foundClient.isPresent());
        assertEquals(savedClient.getId(), foundClient.get().getId());
        assertEquals(savedClient.getEmail(), foundClient.get().getEmail());
    }

    @Test
    public void testFindAll() {
        clientRepository.save(client);

        List<Client> clients = clientRepository.findAll();

        assertNotNull(clients);
        assertEquals(1, clients.size());
        assertEquals(client.getEmail(), clients.get(0).getEmail());
    }

    @Test
    public void testDeleteById() {
        Client savedClient = clientRepository.save(client);

        clientRepository.deleteById(savedClient.getId());

        Optional<Client> foundClient = clientRepository.findById(savedClient.getId());
        assertFalse(foundClient.isPresent());
    }

    @Test
    public void testFindByEmail() {
        clientRepository.save(client);

        Client foundClient = clientRepository.findByEmail(client.getEmail());

        assertNotNull(foundClient);
        assertEquals(client.getEmail(), foundClient.getEmail());
    }

    @Test
    public void testExistsByEmail() {
        clientRepository.save(client);

        boolean exists = clientRepository.existsByEmail(client.getEmail());

        assertTrue(exists);
    }

    @Test
    public void testExistsById() {
        Client savedClient = clientRepository.save(client);

        boolean exists = clientRepository.existsById(savedClient.getId());

        assertTrue(exists);
    }

    @TestConfiguration
    static class TestConfig {

        @Bean
        public ClientRepository clientRepository(ClientJpaRepository clientJpaRepository, ClientMapper clientMapper) {
            return new ClientRepositoryImpl(clientJpaRepository, clientMapper);
        }

        @Bean
        public ClientMapper clientMapper() {
            return new ClientMapper();
        }
    }
}
