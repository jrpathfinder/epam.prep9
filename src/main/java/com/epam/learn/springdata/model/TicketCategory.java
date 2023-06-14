package com.epam.learn.springdata.model;

/**
 * The above code defines an enumeration class named "TicketCategory" that contains three constants:
 * STANDARD, PREMIUM, and BAR.
 * This enum can be used to represent different categories of tickets in a ticketing system.
 */
public enum TicketCategory {
    STANDARD(1),
    PREMIUM(2),
    BAR(3);

    private final int value;

    TicketCategory(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }
}
