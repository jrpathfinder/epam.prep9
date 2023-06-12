package com.epam.learn.springdata.model;

import jakarta.persistence.*;
import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The above code defines a model class named "Ticket" that represents a ticket for an event. It has fields for id, event, user, category, place, and booked. The event and user fields are annotated with @OneToOne to indicate a one-to-one relationship with the Event and User classes. The category field is an enumerated type indicating the category of the ticket. The place field is an integer representing the seat number. The booked field is a boolean indicating whether the ticket has been booked or not. The class is also annotated with @Entity to indicate that it is a JPA entity and can be persisted in a database. Finally, it is annotated with @XmlRootElement to indicate that it can be serialized to XML.
 */
@Data
@XmlRootElement(name = "ticket")
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Event event;

    @OneToOne
    private User user;

    @Enumerated(EnumType.ORDINAL)
    TicketCategory category;

    Integer place;

    Boolean booked;

}
