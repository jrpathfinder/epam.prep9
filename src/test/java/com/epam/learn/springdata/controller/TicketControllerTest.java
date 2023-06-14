package com.epam.learn.springdata.controller;

import com.epam.learn.springdata.exceptions.IdNotFoundException;
import com.epam.learn.springdata.exceptions.InsufficientFundsException;
import com.epam.learn.springdata.facade.BookingFacade;
import com.epam.learn.springdata.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Optional;

import static com.epam.learn.springdata.model.TicketCategory.STANDARD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class TicketControllerTest {

    private BookingFacade facade;

    private TicketController ticketController;

    @BeforeEach
    public void setUp(){
        facade = mock(BookingFacade.class);
        ticketController = new TicketController(facade);
    }
    @Test
    @DisplayName("Should throw IdNotFoundException when userId or eventId is not found")
    void bookTicketWhenIdNotFoundThenThrowException() throws InsufficientFundsException, IdNotFoundException {


        Long userId = 1l;
        Long eventId = 2l;
        Integer place = 3;
        TicketCategory category = STANDARD;

        Model model = mock(Model.class);

        when(facade.bookTicket(anyLong(), anyLong(), anyInt(), any(TicketCategory.class)))
                .thenThrow(new IdNotFoundException("User or Event not found"));

        // Act and Assert
        assertThrows(IdNotFoundException.class, () -> ticketController.bookTicket(model, userId, eventId, place, STANDARD));
    }

    @Test
    @DisplayName("Should throw InsufficientFundsException when user has insufficient funds")
    void bookTicketWhenInsufficientFundsThenThrowException() throws InsufficientFundsException, IdNotFoundException {
        User user = new User();
        user.setId(1L);
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");

        Event event = new Event();
        event.setId(1L);
        event.setTitle("Test Event");
        event.setDate(OffsetDateTime.now());
        event.setTicketPrice(BigDecimal.valueOf(10));

        Model model = mock(Model.class);


        when(facade.getUserById(anyLong())).thenReturn(Optional.of(user));
        when(facade.getEventById(anyLong())).thenReturn(Optional.of(event));
        when(facade.bookTicket(anyLong(), anyLong(), anyInt(), any(TicketCategory.class))).thenThrow(new InsufficientFundsException("Insufficient funds"));

        assertThrows(InsufficientFundsException.class, () -> {
            ticketController.bookTicket(model, 1l, 1l, 1, TicketCategory.STANDARD);
        });
    }

    @Test
    @DisplayName("Should book a ticket and return ticket.html when valid inputs are provided")
    void bookTicketWithValidInputs() throws InsufficientFundsException, IdNotFoundException {
        User user = new User();
        user.setId(1L);
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");

        Event event = new Event();
        event.setId(1L);
        event.setTitle("Test Event");
        event.setDate(OffsetDateTime.now());
        event.setTicketPrice(BigDecimal.valueOf(10));

        Ticket ticket = new Ticket();
        ticket.setId(1L);
        ticket.setUser(user);
        ticket.setEvent(event);
        ticket.setCategory(STANDARD);
        ticket.setPlace(1);
        ticket.setBooked(true);

        when(facade.bookTicket(anyLong(), anyLong(), anyInt(), any(TicketCategory.class))).thenReturn(ticket);

        Model model = mock(Model.class);

        String viewName = ticketController.bookTicket(model, 1l, 1l, 1, STANDARD);

        assertEquals("ticket.html", viewName);
        verify(model, times(1)).addAttribute(eq("ticket"), eq(ticket));
    }

}