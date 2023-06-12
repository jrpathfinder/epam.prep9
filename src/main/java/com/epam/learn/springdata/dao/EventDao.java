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
 * It uses the EventRepository interface to perform CRUD (Create, Read, Update, Delete) operations on the database.
 *  The class has a constructor that takes an EventRepository object as an argument. This object is used to perform database operations.
 *  The get() method is used to retrieve an event from the database based on its ID or title or date.
 *  It returns an Optional object that may or may not contain an Event.
 *  The saveOrUpdate() method is used to save or update an event in the database.
 *  It takes an Event object as an argument and returns the saved or updated Event object.
 *  The delete() method is used to delete an event from the database based on its ID.
 *  Overall, this class provides a simple interface to perform database operations on the Event entity.
 */
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
