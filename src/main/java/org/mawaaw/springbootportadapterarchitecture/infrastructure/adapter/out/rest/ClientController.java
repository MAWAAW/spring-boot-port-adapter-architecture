package org.mawaaw.springbootportadapterarchitecture.infrastructure.adapter.out.rest;

import org.mawaaw.springbootportadapterarchitecture.application.dto.ClientDTO;
import org.mawaaw.springbootportadapterarchitecture.application.service.ClientServiceImpl;
import org.mawaaw.springbootportadapterarchitecture.domain.exception.ClientNotFoundException;
import org.mawaaw.springbootportadapterarchitecture.domain.exception.EmailAlreadyExistsException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@CrossOrigin("*")
public class ClientController {
    private final ClientServiceImpl clientService;

    public ClientController(ClientServiceImpl clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/register")
    public ClientDTO registerClient(@RequestBody ClientDTO clientDTO) throws EmailAlreadyExistsException {
        return clientService.registerClient(clientDTO);
    }

    @PostMapping("/authenticate")
    public ClientDTO authenticateClient(@RequestParam String email, @RequestParam String password) throws ClientNotFoundException {
        return clientService.authenticateClient(email, password);
    }

    @PutMapping("/update")
    public ClientDTO updateClient(@RequestBody ClientDTO clientDTO) {
        return clientService.updateClient(clientDTO);
    }

    @GetMapping("/{email}")
    public ClientDTO getClientByMail(@PathVariable String email) throws ClientNotFoundException {
        return clientService.getClientByMail(email);
    }

    @GetMapping("/all")
    public List<ClientDTO> getAllClients() {
        return clientService.getAllClients();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteClientById(@PathVariable Long id) throws ClientNotFoundException {
        clientService.deleteClientById(id);
    }
}