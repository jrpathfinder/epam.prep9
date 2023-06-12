package com.epam.learn.springdata.dao;


import com.epam.learn.springdata.model.Event;
import com.epam.learn.springdata.model.Ticket;
import com.epam.learn.springdata.model.TicketCategory;
import com.epam.learn.springdata.model.User;
import com.epam.learn.springdata.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * The above code is a DAO (Data Access Object) class for the Ticket model.
 * It interacts with the TicketRepository to perform CRUD (Create, Read, Update, Delete) operations on the Ticket data.
 *  The class has methods to find a ticket based on user ID, event ID, place, and category, save or update a ticket,
 *  get a list of tickets for a user or event, and get a ticket by ID.
 *  The class is annotated with @Component to indicate that it is a Spring bean and @RequiredArgsConstructor to inject the
 *  TicketRepository dependency through constructor injection.
 *  Overall, this class provides a convenient and modular way to interact with the Ticket data in the application.
 */
@Component
@RequiredArgsConstructor
public class TicketDao {

    private final TicketRepository repository;

    public Ticket find(long userId, long eventId, int place, TicketCategory category) {
        return null;//return repository.findByUserIdEventIdPlaceTicketCategory(userId, eventId, place, category);
    }

    public Ticket saveOrUpdate(Ticket ticket) {
        return repository.save(ticket);
    }

    public List<Ticket> get(User user) {
        return repository.findByUser(user);
    }

    public List<Ticket> get(Event event) {
        return repository.findByEvent(event);
    }

    public Optional<Ticket> get(long id) {
        return repository.findById(id);
    }
}
