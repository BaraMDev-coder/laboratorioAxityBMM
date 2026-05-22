package com.axity.dinosaurpark;

import com.axity.dinosaurpark.model.Ticket;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TicketTest {

    @Test
    void testTicketId() {
        //Verifica que el ID del ticket
        Ticket ticket = new Ticket(1L, 1, 25.0, "BASIC");
        assertEquals(1L, ticket.getId());
    }

    @Test
    void testTicketTouristId() {
        //Verifica que el ID del turista asociado al ticket
        Ticket ticket = new Ticket(1L, 1, 25.0, "BASIC");
        assertEquals(1, ticket.getTouristId());
    }

    @Test
    void testTicketPrice() {
        //Verifica que el precio del ticket
        Ticket ticket = new Ticket(1L, 1, 25.0, "BASIC");
        assertEquals(25.0, ticket.getPrice());
    }

    @Test
    void testTicketCategory() {
        //Verifica que la categoría del ticket
        Ticket ticket = new Ticket(1L, 1, 25.0, "BASIC");
        assertEquals("BASIC", ticket.getCategory());
    }

    @Test
    void testTicketIssuedAt() {
        //Verifica que la fecha de emisión no sea nula
        Ticket ticket = new Ticket(1L, 1, 25.0, "BASIC");
        assertNotNull(ticket.getIssuedAt());
    }
}