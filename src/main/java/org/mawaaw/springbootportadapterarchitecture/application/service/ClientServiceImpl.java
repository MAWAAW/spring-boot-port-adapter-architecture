package org.mawaaw.springbootportadapterarchitecture.application.service;

import org.mawaaw.springbootportadapterarchitecture.application.dto.ClientDTO;
import org.mawaaw.springbootportadapterarchitecture.application.port.in.IClientService;
import org.mawaaw.springbootportadapterarchitecture.application.port.out.ClientRepository;
import org.mawaaw.springbootportadapterarchitecture.domain.exception.ClientNotFoundException;
import org.mawaaw.springbootportadapterarchitecture.domain.exception.EmailAlreadyExistsException;
import org.mawaaw.springbootportadapterarchitecture.domain.model.Client;
import org.mawaaw.springbootportadapterarchitecture.infrastructure.mapper.ClientMapper;
import org.mawaaw.springbootportadapterarchitecture.infrastructure.security.CheckRole;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements IClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientServiceImpl(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    public ClientDTO registerClient(ClientDTO clientDTO) {
        if (clientRepository.existsByEmail(clientDTO.email())) {
            throw new EmailAlreadyExistsException("Email " + clientDTO.email() + " already exists.");
        }
        Client client = clientMapper.fromClientDTOToClient(clientDTO);
        Client savedClient = clientRepository.save(client);
        return clientMapper.fromClientToClientDTO(savedClient);
    }

    @Override
    public ClientDTO authenticateClient(String email, String password) {
        Client client = clientRepository.findByEmail(email);
        if (client != null && client.getPassword() != null && client.getPassword().equals(password)) {
            return clientMapper.fromClientToClientDTO(client);
        } else {
            throw new ClientNotFoundException("Client with email " + email + " not found or invalid credentials.");
        }
    }

    @Override
    @CheckRole("USER")
    public ClientDTO updateClient(ClientDTO clientDTO) {
        Client client = clientMapper.fromClientDTOToClient(clientDTO);
        Client savedClient = clientRepository.save(client);
        return clientMapper.fromClientToClientDTO(savedClient);
    }

    @Override
    @CheckRole("ADMIN")
    public ClientDTO getClientByMail(String email) {
        Client client = clientRepository.findByEmail(email);
        if (client == null || !client.getEmail().equals(email)) {
            throw new ClientNotFoundException("Client with email " + email + " not found.");
        }
        return clientMapper.fromClientToClientDTO(client);
    }

    @Override
    @CheckRole("ADMIN")
    public List<ClientDTO> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream()
                .map(clientMapper::fromClientToClientDTO)
                .collect(Collectors.toList());
    }

    @Override
    @CheckRole("ADMIN")
    public void deleteClientById(Long id) {
        Optional<Client> clientOpt = clientRepository.findById(id);
        if (clientOpt.isEmpty()) {
            throw new ClientNotFoundException("Client with ID " + id + " not found.");
        }
        clientRepository.deleteById(clientOpt.get().getId());
    }
}