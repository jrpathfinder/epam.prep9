package com.epam.learn.springdata.facade;

import com.epam.learn.springdata.model.*;


import com.epam.learn.springdata.service.EventService;
import com.epam.learn.springdata.service.TicketService;
import com.epam.learn.springdata.service.UserAccountService;
import com.epam.learn.springdata.service.UserService;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookingFacade {
    private final EventService eventService;
    private final UserService userService;
    private final TicketService ticketService;
    private final UserAccountService userAccountService;

    public void refillAccount(Long userId, BigDecimal amount) {
        UserAccount userAccount = userAccountService.getById(userId).orElseThrow();
        userAccount.setPrepaidMoney(userAccount.getPrepaidMoney().add(amount));
        userAccountService.save(userAccount);
    }

    public Optional<Event> getEventById(long eventId) {
        return eventService.get(eventId);
    }

    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        return eventService.get(title);
    }

    public List<Event> getEventsForDay(OffsetDateTime day, int pageSize, int pageNum) {
        return eventService.get(day);
    }

    public Event createEvent(Event event) {
        return eventService.saveOrUpdate(event);
    }

    public Event updateEvent(Event event) {
        return eventService.saveOrUpdate(event);
    }

    public void deleteEvent(long eventId) {
        eventService.deleteEvent(eventId);
    }

    public Optional<User> getUserById(long userId) {
        return userService.get(userId);
    }

    public User getUserByEmail(String email) {
        return userService.getByEmail(email);
    }

    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        return userService.getByNames(name);
    }

    public User createUser(User user) {
        return userService.saveOrUpdate(user);
    }

    public User updateUser(User user) {
        return userService.saveOrUpdate(user);
    }

    public void deleteUser(long userId) {
        userService.deleteUser(userId);
    }

    public Ticket bookTicket(long userId, long eventId, int place, TicketCategory category) {
        return ticketService.bookTicket(userId, eventId, place, category);
    }

    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
        return ticketService.getByUser(user);
    }

    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
        return ticketService.getByEvent(event);
    }

    public boolean cancelTicket(long ticketId) {
        return ticketService.cancel(ticketId);
    }

    public void preloadTickets() throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        List<Ticket> value = xmlMapper.readValue(new File("tickets.xml"), List.class);
        for (Ticket t : value) {
            ticketService.bookTicket(t.getUser().getId(), t.getEvent().getId(), t.getPlace(), t.getCategory());
        }
        System.out.println(value);
    }
}

