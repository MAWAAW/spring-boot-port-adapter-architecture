package org.mawaaw.springbootportadapterarchitecture.application.port.out;

import org.mawaaw.springbootportadapterarchitecture.domain.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {
    Client save(Client client);
    Optional<Client> findById(Long id);
    List<Client> findAll();
    void deleteById(Long id);
    Client findByEmail(String email);
    boolean existsByEmail(String email);
}
