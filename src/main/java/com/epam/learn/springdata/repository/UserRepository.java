package com.epam.learn.springdata.repository;

import com.epam.learn.springdata.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);

    List<User> findByName(String name);
}
