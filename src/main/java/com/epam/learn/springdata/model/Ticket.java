package com.epam.learn.springdata.model;

import jakarta.persistence.*;
import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The above code defines a model class named "Ticket" that represents a ticket for an event.
 * The category field is an enumerated type indicating the category of the ticket.
 * The place field is an integer representing the seat number.
 * The booked field is a boolean indicating whether the ticket has been booked or not.
 */
@Data
@XmlRootElement(name = "ticket")
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Event event;
    @OneToOne
    private User user;
    @Enumerated(EnumType.ORDINAL)
    private TicketCategory category;
    private Integer place;
    private Boolean booked;
}
