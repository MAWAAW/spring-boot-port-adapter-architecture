package org.mawaaw.springbootportadapterarchitecture.infrastructure.adapter.out.rest;

import org.mawaaw.springbootportadapterarchitecture.application.dto.ClientDTO;
import org.mawaaw.springbootportadapterarchitecture.application.service.ClientServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mawaaw.springbootportadapterarchitecture.domain.exception.ClientNotFoundException;
import org.mawaaw.springbootportadapterarchitecture.domain.exception.EmailAlreadyExistsException;
import org.mawaaw.springbootportadapterarchitecture.infrastructure.exception.GlobalExceptionHandler;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class ClientControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ClientServiceImpl clientService;

    @InjectMocks
    private ClientController clientController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(clientController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    public void testRegisterClient() throws Exception {
        ClientDTO clientDTO = new ClientDTO(1L,"john","doe","john.doe@example.com","password123");
        when(clientService.registerClient(any(ClientDTO.class))).thenReturn(clientDTO);

        mockMvc.perform(post("/clients/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clientDTO)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(clientDTO.id()))
                .andExpect(jsonPath("$.firstName").value(clientDTO.firstName()))
                .andExpect(jsonPath("$.lastName").value(clientDTO.lastName()))
                .andExpect(jsonPath("$.email").value(clientDTO.email()))
        ;

        verify(clientService, times(1)).registerClient(any(ClientDTO.class));
    }

    @Test
    public void testRegisterClient_EmailAlreadyExists() throws Exception {
        ClientDTO clientDTO = new ClientDTO(null, "john", "doe", "john.doe@example.com", "password123");

        when(clientService.registerClient(any(ClientDTO.class)))
                .thenThrow(new EmailAlreadyExistsException("Email " + clientDTO.email() + " already exists."));

        mockMvc.perform(post("/clients/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clientDTO)))
                .andDo(print())
                .andExpect(status().isConflict())
                .andExpect(content().string("Email john.doe@example.com already exists."));

        verify(clientService, times(1)).registerClient(any(ClientDTO.class));
    }

    @Test
    public void testRegisterClient_InvalidData() throws Exception {
        ClientDTO invalidClientDTO = new ClientDTO(null, "john", "doe", "invalidemail", "password123");

        mockMvc.perform(post("/clients/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidClientDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Invalid email format")));

        verify(clientService, times(0)).registerClient(any(ClientDTO.class));
    }

    @Test
    public void testAuthenticateClient() throws Exception {
        String email = "john.doe@example.com";
        String password = "password123";
        ClientDTO clientDTO = new ClientDTO(1L,"john","doe",email,password);
        when(clientService.authenticateClient(email, password)).thenReturn(clientDTO);

        mockMvc.perform(post("/clients/authenticate")
                        .param("email", email)
                        .param("password", password))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(clientDTO.id()))
                .andExpect(jsonPath("$.firstName").value(clientDTO.firstName()))
                .andExpect(jsonPath("$.lastName").value(clientDTO.lastName()))
                .andExpect(jsonPath("$.email").value(clientDTO.email()))
        ;

        verify(clientService, times(1)).authenticateClient(email, password);
    }

    @Test
    public void testAuthenticateClient_InvalidCredentials() throws Exception {
        String email = "john.doe@example.com";
        String password = "wrongpassword";

        when(clientService.authenticateClient(email, password))
                .thenThrow(new ClientNotFoundException("Client with email " + email + " not found or invalid credentials."));

        mockMvc.perform(post("/clients/authenticate")
                        .param("email", email)
                        .param("password", password))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string("Client with email john.doe@example.com not found or invalid credentials."));

        verify(clientService, times(1)).authenticateClient(email, password);
    }

    @Test
    public void testUpdateClient() throws Exception {
        ClientDTO clientDTO = new ClientDTO(1L,"john","doe","john.doe@example.com","password123");
        when(clientService.updateClient(any(ClientDTO.class))).thenReturn(clientDTO);

        mockMvc.perform(put("/clients/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clientDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(clientDTO.id()))
                .andExpect(jsonPath("$.firstName").value(clientDTO.firstName()))
                .andExpect(jsonPath("$.lastName").value(clientDTO.lastName()))
                .andExpect(jsonPath("$.email").value(clientDTO.email()))
        ;

        verify(clientService, times(1)).updateClient(any(ClientDTO.class));
    }

    @Test
    public void testUpdateClient_InvalidData() throws Exception {
        ClientDTO invalidClientDTO = new ClientDTO(1L, "", "", "invalidemail", "");

        mockMvc.perform(put("/clients/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidClientDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Invalid email format")));

        verify(clientService, times(0)).updateClient(any(ClientDTO.class));
    }

    @Test
    public void testGetClientByMail() throws Exception {
        String email = "john.doe@example.com";
        ClientDTO clientDTO = new ClientDTO(1L,"john","doe",email,"password123");
        when(clientService.getClientByMail(email)).thenReturn(clientDTO);

        mockMvc.perform(get("/clients/{email}", email))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(clientDTO.id()))
                .andExpect(jsonPath("$.firstName").value(clientDTO.firstName()))
                .andExpect(jsonPath("$.lastName").value(clientDTO.lastName()))
                .andExpect(jsonPath("$.email").value(clientDTO.email()))
        ;

        verify(clientService, times(1)).getClientByMail(email);
    }

    @Test
    public void testGetClientByMail_NotFound() throws Exception {
        String email = "nonexistent@example.com";

        when(clientService.getClientByMail(email))
                .thenThrow(new ClientNotFoundException("Client with email " + email + " not found."));

        mockMvc.perform(get("/clients/{email}", email))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string("Client with email nonexistent@example.com not found."));

        verify(clientService, times(1)).getClientByMail(email);
    }

    @Test
    public void testGetAllClients() throws Exception {
        List<ClientDTO> clients = Arrays.asList(
                new ClientDTO(1L,"john","doe","john.doe@example.com","password123"),
                new ClientDTO(2L,"jane","smith","jane.smith@example.com","password456")
        );
        when(clientService.getAllClients()).thenReturn(clients);

        mockMvc.perform(get("/clients/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(clients.size()))
        ;

        verify(clientService, times(1)).getAllClients();
    }

    @Test
    public void testDeleteClientById() throws Exception {
        Long clientId = 1L;
        doNothing().when(clientService).deleteClientById(clientId);

        mockMvc.perform(delete("/clients/delete/{id}", clientId))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(clientService, times(1)).deleteClientById(clientId);
    }

    @Test
    public void testDeleteClientById_NotFound() throws Exception {
        Long clientId = 99L;

        doThrow(new ClientNotFoundException("Client with ID " + clientId + " not found."))
                .when(clientService).deleteClientById(clientId);

        mockMvc.perform(delete("/clients/delete/{id}", clientId))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string("Client with ID 99 not found."));

        verify(clientService, times(1)).deleteClientById(clientId);
    }
}
