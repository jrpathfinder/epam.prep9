package com.epam.learn.springdata.dao;

import com.epam.learn.springdata.model.UserAccount;
import com.epam.learn.springdata.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The above code represents a DAO (Data Access Object) class for the UserAccount entity.
 * The getById() method returns an Optional object containing the UserAccount entity with the specified ID.
 */
@Repository
@RequiredArgsConstructor
public class UserAccountDao {

    private final UserAccountRepository repository;

    public Optional<UserAccount> getById(Long id) {
        return repository.findById(id);
    }

    public void saveOrUpdate(UserAccount userAccount) {
        repository.save(userAccount);
    }

    public void delete(UserAccount userAccount) {
        repository.delete(userAccount);
    }
}