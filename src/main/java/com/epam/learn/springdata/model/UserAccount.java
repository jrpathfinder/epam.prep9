package com.epam.learn.springdata.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * This is a model class for a user account in a system.
 * It also has a OneToOne relationship with the User class, which means that each User can have only one UserAccount.
 * The class also has a BigDecimal field for prepaidMoney,
 * which represents the amount of money that the user has prepaid into their account.
 */
@Entity
@Data
@Accessors(chain = true)
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private User user;
    private BigDecimal prepaidMoney;
}