package com.epam.learn.springdata.controller;

import com.epam.learn.springdata.exceptions.IdNotFoundException;
import com.epam.learn.springdata.facade.BookingFacade;
import com.epam.learn.springdata.model.Event;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequiredArgsConstructor
public class EventsController {

    private final BookingFacade facade;

    @GetMapping(value = "/event")
    public String getEventById(Model model, @RequestParam("id") Integer id) throws IdNotFoundException {
        Optional<Event> event = facade.getEventById(id);
        model.addAttribute("event", event.orElseThrow());
        if (id.equals(-1)) {
            log.error("id -1 is not correct value!");
            throw new IdNotFoundException("Id -1 is not acceptable!");
        }
        return "event.html";
    }

    @RequestMapping(value = "/events/title", method = RequestMethod.GET)
    public String getEventsByTitle(Model model, @RequestParam("title") String title,
                                   @RequestParam("size") Optional<Integer> size,
                                   @RequestParam("num") Optional<Integer> num) {
        final int currentNum = num.orElse(1);
        final int pageSize = size.orElse(5);
        List<Event> eventList = facade.getEventsByTitle(title, pageSize, currentNum);
        model.addAttribute("events", eventList);
        return "events.html";
    }

    @RequestMapping(value = "/events/day", method = RequestMethod.GET)
    public String getEventsForADay(Model model, @RequestParam("day") OffsetDateTime day,
                                   @RequestParam("size") Optional<Integer> size,
                                   @RequestParam("num") Optional<Integer> num) {
        final int currentNum = num.orElse(1);
        final int pageSize = size.orElse(5);
        List<Event> eventList = facade.getEventsForDay(day, pageSize, currentNum);
        model.addAttribute("events", eventList);
        return "events.html";
    }

    @RequestMapping(value = "/event", method = RequestMethod.POST)
    public void createEvent(@ModelAttribute Event event) {
        facade.createEvent(event);
    }

    @RequestMapping(value = "/event", method = RequestMethod.PUT)
    public void updateEvent(@ModelAttribute Event event) {
        facade.updateEvent(event);
    }

    @RequestMapping(value = "/event", method = RequestMethod.DELETE)
    public void delete(@RequestParam("id") Integer id) {
        facade.deleteEvent(id);
    }
}
