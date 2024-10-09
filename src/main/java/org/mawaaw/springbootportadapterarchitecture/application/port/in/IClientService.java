package org.mawaaw.springbootportadapterarchitecture.application.port.in;

import org.mawaaw.springbootportadapterarchitecture.application.dto.ClientDTO;
import org.mawaaw.springbootportadapterarchitecture.domain.exception.ClientNotFoundException;
import org.mawaaw.springbootportadapterarchitecture.domain.exception.EmailAlreadyExistsException;

import java.util.List;

public interface IClientService {

    ClientDTO registerClient(ClientDTO clientDTO) throws EmailAlreadyExistsException;
    ClientDTO authenticateClient(String email, String password) throws ClientNotFoundException;
    ClientDTO updateClient(ClientDTO client);
    ClientDTO getClientByMail(String email) throws ClientNotFoundException;
    List<ClientDTO> getAllClients();
    void deleteClientById(Long id) throws ClientNotFoundException;
}
