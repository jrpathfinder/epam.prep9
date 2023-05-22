package com.epam.learn.springdata.service;

import com.epam.learn.springdata.dao.EventDao;
import com.epam.learn.springdata.dao.TicketDao;
import com.epam.learn.springdata.dao.UserAccountDao;
import com.epam.learn.springdata.dao.UserDao;
import com.epam.learn.springdata.exceptions.IdNotFoundException;
import com.epam.learn.springdata.exceptions.InsufficientFundsException;
import com.epam.learn.springdata.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class TicketServiceTest {

    @Mock
    private TicketDao dao;

    @Mock
    private EventDao eventDao;

    @Mock
    private UserDao userDao;

    @Mock
    private UserAccountDao accountDao;

    @InjectMocks
    private TicketService ticketService;

    @BeforeEach
    public void setUp() {
        openMocks(this);
    }


    @Test
    @DisplayName("Should cancel the ticket and return true when the ticket id is valid")
    void cancelTicketWhenIdIsValid() throws IdNotFoundException {
        long ticketId = 1L;
        Ticket ticket = new Ticket();
        ticket.setId(ticketId);
        ticket.setBooked(true);

        when(dao.get(ticketId)).thenReturn(Optional.of(ticket));
        when(dao.saveOrUpdate(ticket)).thenReturn(ticket);

        boolean result = ticketService.cancel(ticketId);

        assertTrue(result);
        assertFalse(ticket.getBooked());
        verify(dao, times(1)).get(ticketId);
        verify(dao, times(1)).saveOrUpdate(ticket);
    }

    @Test
    @DisplayName("Should throw an exception when the ticket id is not found")
    void cancelTicketWhenIdIsNotFoundThenThrowException() {
        long ticketId = 1L;
        when(dao.get(ticketId)).thenReturn(Optional.empty());

        assertThrows(IdNotFoundException.class,
                () -> ticketService.cancel(ticketId),
                "Expected IdNotFoundException to be thrown"
        );

        verify(dao, times(1)).get(ticketId);
        verifyNoMoreInteractions(dao, eventDao, userDao, accountDao);
    }

    @Test
    @DisplayName("Should throw IdNotFoundException when user id is not found")
    void bookTicketWhenUserIdNotFoundThenThrowException() {
        long userId = 1L;
        long eventId = 2L;
        int place = 3;
        TicketCategory category = TicketCategory.STANDARD;

        when(eventDao.get(eventId)).thenReturn(Optional.of(new Event()));
        when(userDao.get(userId)).thenReturn(Optional.empty());

        assertThrows(IdNotFoundException.class, () -> ticketService.bookTicket(userId, eventId, place, category));

        verify(userDao, times(1)).get(userId);
    }

    @Test
    @DisplayName("Should throw IdNotFoundException when event id is not found")
    void bookTicketWhenEventIdNotFoundThenThrowException() {
        long userId = 1L;
        long eventId = 2L;
        int place = 1;
        TicketCategory category = TicketCategory.STANDARD;

        when(eventDao.get(eventId)).thenReturn(Optional.empty());

        assertThrows(IdNotFoundException.class, () -> ticketService.bookTicket(userId, eventId, place, category));

        verify(eventDao, times(1)).get(eventId);
        verifyNoMoreInteractions(eventDao, userDao, accountDao, dao);
    }

    @Test
    @DisplayName("Should throw IdNotFoundException when user account id is not found")
    void bookTicketWhenUserAccountIdNotFoundThenThrowException() {
        long userId = 1L;
        long eventId = 2L;
        int place = 3;
        TicketCategory category = TicketCategory.STANDARD;

        when(eventDao.get(eventId)).thenReturn(Optional.of(new Event()));
        when(userDao.get(userId)).thenReturn(Optional.of(new User()));
        when(accountDao.getById(userId)).thenReturn(Optional.empty());

        assertThrows(IdNotFoundException.class, () -> ticketService.bookTicket(userId, eventId, place, category));

        verify(eventDao, times(1)).get(eventId);
        verify(userDao, times(1)).get(userId);
        verify(accountDao, times(1)).getById(userId);
        verifyNoMoreInteractions(eventDao, userDao, accountDao);
    }

    @Test
    @DisplayName("Should throw InsufficientFundsException when user has insufficient funds")
    void bookTicketWhenUserHasInsufficientFundsThenThrowException() {// create test data
        User user = new User();
        user.setId(1L);
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");

        Event event = new Event();
        event.setId(1L);
        event.setTitle("Test Event");
        event.setDate(OffsetDateTime.now());
        event.setTicketPrice(BigDecimal.valueOf(100));

        UserAccount userAccount = new UserAccount();
        userAccount.setId(1L);
        userAccount.setUser(user);
        userAccount.setPrepaidMoney(BigDecimal.valueOf(50));

        TicketCategory category = TicketCategory.STANDARD;

        // mock dependencies
        when(eventDao.get(event.getId())).thenReturn(Optional.of(event));
        when(userDao.get(user.getId())).thenReturn(Optional.of(user));
        when(accountDao.getById(user.getId())).thenReturn(Optional.of(userAccount));

        // execute the method under test
        assertThrows(InsufficientFundsException.class, () -> {
            ticketService.bookTicket(user.getId(), event.getId(), 1, category);
        });

        // verify that the dao methods were not called
        verify(dao, never()).saveOrUpdate(any(Ticket.class));
        verify(userDao, never()).saveOrUpdate(any(User.class));
        verify(accountDao, never()).saveOrUpdate(any(UserAccount.class));
    }

    @Test
    @DisplayName("Should book a ticket when user has sufficient funds and valid ids")
    void bookTicketWhenUserHasSufficientFundsAndValidIds() {// create test data
        long userId = 1L;
        long eventId = 2L;
        int place = 3;
        TicketCategory category = TicketCategory.STANDARD;
        BigDecimal ticketPrice = new BigDecimal("10.00");

        User user = new User();
        user.setId(userId);
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");

        Event event = new Event();
        event.setId(eventId);
        event.setTitle("Test Event");
        event.setDate(OffsetDateTime.now());
        event.setTicketPrice(ticketPrice);

        UserAccount userAccount = new UserAccount();
        userAccount.setId(userId);
        userAccount.setUser(user);
        userAccount.setPrepaidMoney(new BigDecimal("20.00"));

        Ticket ticket = new Ticket();
        ticket.setId(1L);
        ticket.setEvent(event);
        ticket.setUser(user);
        ticket.setCategory(category);
        ticket.setPlace(place);
        ticket.setBooked(true);

        // mock dependencies
        when(eventDao.get(eventId)).thenReturn(Optional.of(event));
        when(userDao.get(userId)).thenReturn(Optional.of(user));
        when(accountDao.getById(userId)).thenReturn(Optional.of(userAccount));
        when(dao.saveOrUpdate(any(Ticket.class))).thenReturn(ticket);

        // call the method under test
        try {
            Ticket bookedTicket = ticketService.bookTicket(userId, eventId, place, category);

            // verify the result
            assertNotNull(bookedTicket);
            assertEquals(ticket.getId(), bookedTicket.getId());
            assertEquals(ticket.getEvent(), bookedTicket.getEvent());
            assertEquals(ticket.getUser(), bookedTicket.getUser());
            assertEquals(ticket.getCategory(), bookedTicket.getCategory());
            assertEquals(ticket.getPlace(), bookedTicket.getPlace());
            assertEquals(ticket.getBooked(), bookedTicket.getBooked());

            // verify the interactions with dependencies
            verify(eventDao, times(1)).get(eventId);
            verify(userDao, times(1)).get(userId);
            verify(accountDao, times(1)).getById(userId);
            verify(dao, times(1)).saveOrUpdate(any(Ticket.class));
        } catch (InsufficientFundsException | IdNotFoundException e) {
            fail("Unexpected exception thrown: " + e.getMessage());
        }
    }
}