package org.mawaaw.springbootportadapterarchitecture.infrastructure.mapper;

import org.mawaaw.springbootportadapterarchitecture.application.dto.ClientDTO;
import org.mawaaw.springbootportadapterarchitecture.domain.model.Client;
import org.mawaaw.springbootportadapterarchitecture.infrastructure.entity.ClientEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ClientMapper {
    public Client fromClientEntityToClient(ClientEntity clientEntity) {
        Client client = new Client();
        BeanUtils.copyProperties(clientEntity, client);
        return client;
    }

    public ClientEntity fromClientToClientEntity(Client client) {
        ClientEntity clientEntity = new ClientEntity();
        BeanUtils.copyProperties(client, clientEntity);
        return clientEntity;
    }

    public ClientDTO fromClientToClientDTO(Client client) {
        return new ClientDTO(
                client.getId(),
                client.getFirstName(),
                client.getLastName(),
                client.getEmail(),
                client.getPassword()
        );
    }

    public Client fromClientDTOToClient(ClientDTO clientDTO) {
        Client client = new Client();
        BeanUtils.copyProperties(clientDTO, client);
        return client;
    }
}