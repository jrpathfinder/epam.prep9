package com.epam.learn.springdata.service;

import com.epam.learn.springdata.dao.EventDao;
import com.epam.learn.springdata.dao.TicketDao;
import com.epam.learn.springdata.dao.UserAccountDao;
import com.epam.learn.springdata.dao.UserDao;
import com.epam.learn.springdata.exceptions.IdNotFoundException;
import com.epam.learn.springdata.exceptions.InsufficientFundsException;
import com.epam.learn.springdata.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketDao dao;
    private final EventDao eventDao;
    private final UserDao userDao;
    private final UserAccountDao accountDao;

    @Transactional
    public Ticket bookTicket(long userId, long eventId, int place, TicketCategory category) throws InsufficientFundsException, IdNotFoundException {

        Event event = eventDao.get(eventId).orElseThrow(() -> new IdNotFoundException("Event Id not found!"));
        User user = userDao.get(userId).orElseThrow(() -> new IdNotFoundException("User Id not found!"));
        UserAccount userAccount = accountDao.getById(userId).orElseThrow(() -> new IdNotFoundException("UserAccount Id not found!"));
        BigDecimal ticketPrice = event.getTicketPrice();

        if (userAccount.getPrepaidMoney().compareTo(ticketPrice) < 0) {
            throw new InsufficientFundsException("User does not have enough prepaid money to book the ticket");
        }
        userAccount.setPrepaidMoney(userAccount.getPrepaidMoney().subtract(ticketPrice));
        userDao.saveOrUpdate(user);

        Ticket ticket = new Ticket();
        ticket.setEvent(event);
        ticket.setUser(user);
        ticket.setCategory(category);
        ticket.setPlace(place);
        ticket.setBooked(true);

        return dao.saveOrUpdate(ticket);
    }

    public List<Ticket> getByUser(User user) {
        return dao.get(user);
    }

    public List<Ticket> getByEvent(Event event) {
        return dao.get(event);
    }

   public boolean cancel(long id) throws IdNotFoundException {
        Ticket ticket = dao.get(id).orElseThrow(() -> new IdNotFoundException("Ticket Id not found!"));
        ticket.setBooked(false);
        return dao.saveOrUpdate(ticket) != null;
    }
}
