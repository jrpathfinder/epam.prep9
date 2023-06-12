package com.epam.learn.springdata.service;

import com.epam.learn.springdata.dao.TicketDao;
import com.epam.learn.springdata.model.Event;
import com.epam.learn.springdata.model.Ticket;
import com.epam.learn.springdata.model.TicketCategory;
import com.epam.learn.springdata.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketDao dao;

    public Ticket bookTicket(long userId, long eventId, int place, TicketCategory category) {
        Ticket ticket = dao.find(userId, eventId, place, category);
        ticket.setBooked(true);
        return dao.saveOrUpdate(ticket);
    }

    public List<Ticket> getByUser(User user) {
        return dao.get(user);
    }

    public List<Ticket> getByEvent(Event event) {
        return dao.get(event);
    }

   public boolean cancel(long id) {
        Ticket ticket = dao.get(id).orElseThrow();
        ticket.setBooked(false);
        return dao.saveOrUpdate(ticket) != null;
    }
}
