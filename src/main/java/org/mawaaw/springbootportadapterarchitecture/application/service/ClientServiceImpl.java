package org.mawaaw.springbootportadapterarchitecture.application.service;

import org.mawaaw.springbootportadapterarchitecture.application.dto.ClientDTO;
import org.mawaaw.springbootportadapterarchitecture.application.port.in.IClientService;
import org.mawaaw.springbootportadapterarchitecture.application.port.out.ClientRepository;
import org.mawaaw.springbootportadapterarchitecture.domain.exception.ClientNotFoundException;
import org.mawaaw.springbootportadapterarchitecture.domain.exception.EmailAlreadyExistsException;
import org.mawaaw.springbootportadapterarchitecture.domain.model.Client;
import org.mawaaw.springbootportadapterarchitecture.infrastructure.mapper.ClientMapper;
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
    public ClientDTO registerClient(ClientDTO clientDTO) throws EmailAlreadyExistsException {
        if (clientRepository.existsByEmail(clientDTO.email())) {
            throw new EmailAlreadyExistsException("Email " + clientDTO.email() + " already exists.");
        }
        Client client = clientMapper.fromClientDTOToClient(clientDTO);
        Client savedClient = clientRepository.save(client);
        return clientMapper.fromClientToClientDTO(savedClient);
    }

    @Override
    public ClientDTO authenticateClient(String email, String password) throws ClientNotFoundException {
        Client client = clientRepository.findByEmail(email);
        if (client != null && client.getPassword() != null && client.getPassword().equals(password)) {
            return clientMapper.fromClientToClientDTO(client);
        } else {
            throw new ClientNotFoundException("Client with email " + email + " not found or invalid credentials.");
        }
    }

    @Override
    public ClientDTO updateClient(ClientDTO clientDTO) {
        Client client = clientMapper.fromClientDTOToClient(clientDTO);
        Client savedClient = clientRepository.save(client);
        return clientMapper.fromClientToClientDTO(savedClient);
    }

    @Override
    public ClientDTO getClientByMail(String email) throws ClientNotFoundException {
        Client client = clientRepository.findByEmail(email);
        if (client == null || !client.getEmail().equals(email)) {
            throw new ClientNotFoundException("Client with email " + email + " not found.");
        }
        return clientMapper.fromClientToClientDTO(client);
    }

    @Override
    public List<ClientDTO> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream()
                .map(clientMapper::fromClientToClientDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteClientById(Long id) throws ClientNotFoundException {
        if(!clientRepository.existsById(id)) {
            throw new ClientNotFoundException("Client with ID " + id + " not found.");
        }
        Optional<Client> clientOpt = clientRepository.findById(id);
        if (clientOpt.isEmpty()) {
            throw new ClientNotFoundException("Room with ID " + id + " not found.");
        }
        clientRepository.deleteById(clientOpt.get().getId());
    }
}