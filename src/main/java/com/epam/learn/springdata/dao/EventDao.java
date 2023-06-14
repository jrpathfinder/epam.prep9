package com.epam.learn.springdata.dao;

import com.epam.learn.springdata.model.Event;
import com.epam.learn.springdata.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

/**
 * The above code is a DAO (Data Access Object) class for the Event entity.
 * */
@Component
@RequiredArgsConstructor
public class EventDao {

    private final EventRepository repository;
    public Optional<Event> get(long eventId) {
        return repository.findById(eventId);
    }

    public List<Event> get(String title) {
        return repository.findByTitle(title);
    }

    public List<Event> get(OffsetDateTime date) {
        return repository.findByDate(date);
    }

    public Event saveOrUpdate(Event event) {

        return repository.save(event);
    }

    public void delete(long eventId) {
        repository.deleteById(eventId);
    }
}
