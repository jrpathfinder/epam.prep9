package com.epam.learn.springdata.dao;

import com.epam.learn.springdata.model.User;
import com.epam.learn.springdata.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * The above code is a DAO (Data Access Object) class for the User model.
 * The class has methods to get a User by their ID or email, get a list of Users by name, save or update a User, and delete a User by their ID.
 */
@Component
@RequiredArgsConstructor
public class UserDao {
    private final UserRepository repository;

    public Optional<User> get(long userId) {
        return repository.findById(userId);
    }

    public User get(String email) {
        return repository.findByEmail(email);
    }

    public List<User> getByName(String name) {
        return repository.findByName(name);
    }

    public User saveOrUpdate(User user) {
        return repository.save(user);
    }

    public void delete(long userId) {
        repository.deleteById(userId);
    }
}
