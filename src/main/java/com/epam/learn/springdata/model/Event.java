package com.epam.learn.springdata.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Date;

/**
 * This is a class representing an Event entity.
 * The ticket price is stored as a BigDecimal to ensure accuracy in financial calculations.
 */
@Data
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    @Temporal(TemporalType.DATE)
    private Date dateOld;
    private OffsetDateTime date;
    private BigDecimal ticketPrice;
}
