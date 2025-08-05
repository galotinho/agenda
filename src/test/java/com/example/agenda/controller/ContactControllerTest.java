package com.example.agenda.controller;

import com.example.agenda.model.Contact;
import com.example.agenda.service.ContactService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ContactController.class)
public class ContactControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContactService contactService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateContact() throws Exception {
        Contact contact = new Contact("João Silva", "123456789");
        contact.setId(1L);

        when(contactService.createContact(any(Contact.class))).thenReturn(contact);

        mockMvc.perform(post("/api/agenda")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(contact)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("João Silva"))
                .andExpect(jsonPath("$.telefone").value("123456789"));
    }

    @Test
    public void testGetAllContacts() throws Exception {
        Contact contact1 = new Contact("João Silva", "123456789");
        contact1.setId(1L);
        Contact contact2 = new Contact("Maria Santos", "987654321");
        contact2.setId(2L);

        when(contactService.getAllContacts()).thenReturn(Arrays.asList(contact1, contact2));

        mockMvc.perform(get("/api/agenda"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].nome").value("João Silva"))
                .andExpect(jsonPath("$[1].nome").value("Maria Santos"));
    }

    @Test
    public void testGetContactById() throws Exception {
        Contact contact = new Contact("João Silva", "123456789");
        contact.setId(1L);

        when(contactService.getContactById(1L)).thenReturn(Optional.of(contact));

        mockMvc.perform(get("/api/agenda/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("João Silva"))
                .andExpect(jsonPath("$.telefone").value("123456789"));
    }

    @Test
    public void testGetContactByIdNotFound() throws Exception {
        when(contactService.getContactById(anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/agenda/999"))
                .andExpect(status().isNotFound());
    }
}
