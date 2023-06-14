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
