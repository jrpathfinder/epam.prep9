package com.epam.learn.springdata.service;

import com.epam.learn.springdata.dao.EventDao;
import com.epam.learn.springdata.model.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventDao dao;

    public Optional<Event> get(long eventId) {
        return dao.get(eventId);
    }
    public List<Event> get(String title) {
        return dao.get(title);
    }

    public List<Event> get(OffsetDateTime date) {
        return dao.get(date);
    }

    public Event saveOrUpdate(Event event) {
        return dao.saveOrUpdate(event);
    }

    public void deleteEvent(long eventId) {
      dao.delete(eventId);
    }
}
