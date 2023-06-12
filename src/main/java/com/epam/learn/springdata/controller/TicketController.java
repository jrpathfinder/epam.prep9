package com.epam.learn.springdata.controller;


import com.epam.learn.springdata.facade.BookingFacade;
import com.epam.learn.springdata.model.Event;
import com.epam.learn.springdata.model.Ticket;
import com.epam.learn.springdata.model.TicketCategory;
import com.epam.learn.springdata.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class TicketController {

    private final BookingFacade facade;

    @GetMapping("/ticket")
    public String bookTicket(Model model, @RequestParam("userId") Integer userId, @RequestParam("eventId") Integer eventId,
                             @RequestParam("place") Integer place, @RequestParam("category") TicketCategory category ){
        Ticket ticket = facade.bookTicket(userId, eventId, place, category);
        model.addAttribute("ticket", ticket);
        return "ticket.html";
    }

    @RequestMapping(value = "/ticket/user", method = RequestMethod.GET)
    public String getBookedTickets(Model model, @ModelAttribute Event event,
                                 @RequestParam("size") Optional<Integer> size,
                                 @RequestParam("num") Optional<Integer> num){
        final int currentNum = num.orElse(1);
        final int pageSize = size.orElse(5);
        List<Ticket> tickets = facade.getBookedTickets(event, pageSize, currentNum);
        model.addAttribute("tickets", tickets);
        return "tickets.html";
    }

    @RequestMapping(value = "/ticket/event", method = RequestMethod.GET)
    public String getBookedTickets(Model model, @ModelAttribute User user,
                                   @RequestParam("size") Optional<Integer> size,
                                   @RequestParam("num") Optional<Integer> num){
        final int currentNum = num.orElse(1);
        final int pageSize = size.orElse(5);
        List<Ticket> tickets = facade.getBookedTickets(user, pageSize, currentNum);
        model.addAttribute("tickets", tickets);
        return "tickets.html";
    }

    @RequestMapping(value = "/ticket", method = RequestMethod.PUT)
    public void cancel(@RequestParam("id") Integer id){
        facade.cancelTicket(id);
    }

    @GetMapping(name = "/preload")
    public void preload() throws IOException {
        facade.preloadTickets();
    }

}
