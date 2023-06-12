package com.epam.learn.springdata.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

/**
 *This is a model class named "User" which represents a user entity in the application.
 * It has three instance variables - "id", "name", and "email". The "id" is a unique identifier for the user and is generated automatically using the @GeneratedValue annotation.
 * The "name" and "email" variables store the user's name and email respectively. This class is annotated with @Entity, which indicates that it is a JPA entity and can be persisted in a database.
 * The @Data annotation is used to generate getters, setters, and other boilerplate code automatically.
 */
@Data
@Entity(name = "TicketUser")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
}
