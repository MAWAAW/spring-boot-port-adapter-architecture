package org.mawaaw.springbootportadapterarchitecture.infrastructure.adapter.out.rest;

import org.mawaaw.springbootportadapterarchitecture.application.dto.ClientDTO;
import org.mawaaw.springbootportadapterarchitecture.application.service.ClientServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ClientDTO> registerClient(@RequestBody ClientDTO clientDTO) {
        ClientDTO createdClient = clientService.registerClient(clientDTO);
        return new ResponseEntity<>(createdClient, HttpStatus.CREATED);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<ClientDTO> authenticateClient(@RequestParam String email, @RequestParam String password) {
        ClientDTO authenticatedClient = clientService.authenticateClient(email, password);
        return new ResponseEntity<>(authenticatedClient, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ClientDTO> updateClient(@RequestBody ClientDTO clientDTO) {
        ClientDTO updatedClient = clientService.updateClient(clientDTO);
        return new ResponseEntity<>(updatedClient, HttpStatus.OK);

    }

    @GetMapping("/{email}")
    public ResponseEntity<ClientDTO> getClientByMail(@PathVariable String email) {
        ClientDTO client = clientService.getClientByMail(email);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        List<ClientDTO> clients = clientService.getAllClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteClientById(@PathVariable Long id)  {
        clientService.deleteClientById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}