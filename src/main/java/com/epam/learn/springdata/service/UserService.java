package com.epam.learn.springdata.service;

import com.epam.learn.springdata.dao.UserDao;
import com.epam.learn.springdata.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserDao dao;

    public Optional<User> get(long eventId) {
        return dao.get(eventId);
    }

    public User getByEmail(String email) {
        return dao.get(email);
    }

    public List<User> getByNames(String name) {
        return dao.getByName(name);
    }

    public User saveOrUpdate(User user) {
        return dao.saveOrUpdate(user);
    }

    public void deleteUser(long userId) {
        dao.delete(userId);
    }
}
