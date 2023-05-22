package com.epam.learn.springdata.repository;

import com.epam.learn.springdata.model.Event;
import com.epam.learn.springdata.model.Ticket;
import com.epam.learn.springdata.model.TicketCategory;
import com.epam.learn.springdata.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long> {
    List<Ticket> findByUser(User user);

    List<Ticket> findByEvent(Event event);

    //Ticket findByUserIdEventIdPlaceTicketCategory(long userId, long eventId, int place, TicketCategory category);
}
