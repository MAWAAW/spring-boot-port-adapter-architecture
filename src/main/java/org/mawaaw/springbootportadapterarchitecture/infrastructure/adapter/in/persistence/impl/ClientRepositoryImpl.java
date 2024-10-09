package org.mawaaw.springbootportadapterarchitecture.infrastructure.adapter.in.persistence.impl;

import org.mawaaw.springbootportadapterarchitecture.application.port.out.ClientRepository;
import org.mawaaw.springbootportadapterarchitecture.domain.model.Client;
import org.mawaaw.springbootportadapterarchitecture.infrastructure.adapter.in.persistence.jpa.ClientJpaRepository;
import org.mawaaw.springbootportadapterarchitecture.infrastructure.entity.ClientEntity;
import org.mawaaw.springbootportadapterarchitecture.infrastructure.mapper.ClientMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ClientRepositoryImpl implements ClientRepository {

    private final ClientJpaRepository clientJpaRepository;
    private final ClientMapper clientMapper;

    public ClientRepositoryImpl(ClientJpaRepository clientJpaRepository, ClientMapper clientMapper) {
        this.clientJpaRepository = clientJpaRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    public Client save(Client client) {
        ClientEntity clientEntity = clientMapper.fromClientToClientEntity(client);
        ClientEntity savedEntity = clientJpaRepository.save(clientEntity);
        return clientMapper.fromClientEntityToClient(savedEntity);
    }

    @Override
    public Optional<Client> findById(Long id) {
        Optional<ClientEntity> clientEntityOpt = clientJpaRepository.findById(id);
        return clientEntityOpt.map(clientMapper::fromClientEntityToClient);
    }

    @Override
    public List<Client> findAll() {
        List<ClientEntity> clientEntities = clientJpaRepository.findAll();
        return clientEntities.stream().map(clientMapper::fromClientEntityToClient).collect(Collectors.toList());

    }

    @Override
    public void deleteById(Long id) {
        clientJpaRepository.deleteById(id);
    }

    @Override
    public Client findByEmail(String email) {
        ClientEntity clientEntity = clientJpaRepository.findByEmail(email);
        return clientMapper.fromClientEntityToClient(clientEntity);
    }

    @Override
    public boolean existsByEmail(String email) {
        return clientJpaRepository.existsByEmail(email);
    }

    @Override
    public boolean existsById(Long id) {
        return clientJpaRepository.existsById(id);
    }
}
