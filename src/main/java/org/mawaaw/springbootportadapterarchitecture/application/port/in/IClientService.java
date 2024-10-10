package org.mawaaw.springbootportadapterarchitecture.application.port.in;

import org.mawaaw.springbootportadapterarchitecture.application.dto.ClientDTO;

import java.util.List;

public interface IClientService {

    ClientDTO registerClient(ClientDTO clientDTO);
    ClientDTO authenticateClient(String email, String password);
    ClientDTO updateClient(ClientDTO client);
    ClientDTO getClientByMail(String email);
    List<ClientDTO> getAllClients();
    void deleteClientById(Long id);
}
