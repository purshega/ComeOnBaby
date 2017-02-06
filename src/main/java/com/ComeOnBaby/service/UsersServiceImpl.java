package com.ComeOnBaby.service;


import com.ComeOnBaby.dao.UsersDao;
import com.ComeOnBaby.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class UsersServiceImpl implements UsersService{
    private UsersDao usersDao;

    @Autowired(required = true)
    public void setCaseDao(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    @Override
    @Transactional
    public void addNewUsers(Users users) {
        usersDao.create(users);
    }

    @Override
    @Transactional
    public void updateUsers(Users users) {
        usersDao.update(users);
    }

    @Override
    @Transactional
    public void deleteUsers(Users users) {
        usersDao.delete(users);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Users> getAllUsers() {
        return usersDao.findAll();
    }
}