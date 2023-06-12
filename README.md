# [JGMP-CEE Q4 2022 - Q1 2023] Spring Boot, Data


1) + Based on the codebase created during the Spring Web module.
Using the Hibernate ORM framework, update existing models. (0.5 point)

2) + Add a new model to the application – UserAccount, it should store the amount of prepaid money the user has in the system, 
which should be used during the booking procedure. 
+ Add methods for refilling the account to the BookingFacade class. 
Add DAO and service objects for the new entities. Add ticketPrice field to Event entity. (0.5 point)

3) + Create a database schema for storing application entities. Provide SQL script with schema creation DDL for DBMS of your choice. (0.5 points)

4) + Update DAO objects so that they inherit from one of the Spring Data interfaces, for example – CrudRepository. 

They would store and retrieve application entities from the database. Use transaction management to perform actions 
in a transaction where it necessary (define the approach to implementing transactions with a mentor). 
Configure Hibernate for work with DBMS that you choose. (1.5 points)

Update ticket booking methods to check and withdraw money from user account according to the ticketPrice for a particular event. (0.5 point)

Cover code with unit tests. Code should contain proper logging (0.5 point)

Add integration tests for newly implemented scenarios. (0.5 point)



Extramile:

2 points

Add Hibernate caching to all getById() methods. Add query caching. 
Show that your caches are really working in tests (cache hits count, cache miss count) Hint: org.hibernate.stat.Statistic. 