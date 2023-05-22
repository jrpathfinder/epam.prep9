package com.epam.learn.springdata.service;

import com.epam.learn.springdata.dao.UserAccountDao;
import com.epam.learn.springdata.model.UserAccount;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserAccountService {

    private UserAccountDao userAccountDao;

    public Optional<UserAccount> getById(Long id) {
        return userAccountDao.getById(id);
    }

    public void save(UserAccount userAccount) {
        userAccountDao.saveOrUpdate(userAccount);
    }

    public void delete(UserAccount userAccount) {
        userAccountDao.delete(userAccount);
    }
}