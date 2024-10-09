package org.mawaaw.springbootportadapterarchitecture.application.service;

import org.mawaaw.springbootportadapterarchitecture.application.dto.ClientDTO;
import org.mawaaw.springbootportadapterarchitecture.application.port.in.IClientService;
import org.mawaaw.springbootportadapterarchitecture.domain.exception.ClientNotFoundException;
import org.mawaaw.springbootportadapterarchitecture.domain.exception.EmailAlreadyExistsException;

import java.util.List;

public class ClientServiceImpl implements IClientService {
    @Override
    public ClientDTO registerClient(ClientDTO clientDTO) throws EmailAlreadyExistsException {
        return null;
    }

    @Override
    public ClientDTO authenticateClient(String email, String password) throws ClientNotFoundException {
        return null;
    }

    @Override
    public ClientDTO updateClient(ClientDTO client) {
        return null;
    }

    @Override
    public ClientDTO getClientByMail(String email) throws ClientNotFoundException {
        return null;
    }

    @Override
    public List<ClientDTO> getAllClients() {
        return List.of();
    }

    @Override
    public void deleteClientById(Long id) throws ClientNotFoundException {

    }
}
