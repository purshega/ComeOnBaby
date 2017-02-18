package com.ComeOnBaby.service;


import com.ComeOnBaby.dao.AppUserDao;
import com.ComeOnBaby.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("appUserService")
@Transactional
public class AppUserServiceImpl implements AppUserService {
    private AppUserDao appUserDao;

    @Autowired(required = true)
    public void setCaseDao(AppUserDao appUserDao) {
        this.appUserDao = appUserDao;
    }

    @Override
    @Transactional()
    public Long addNewUser(AppUser appUser) {
        return appUserDao.create(appUser);
    }

    @Override
    @Transactional()
    public AppUser findById(Long id) {return appUserDao.read(id);}

    @Override
    @Transactional()
    public AppUser findByEmail(String email) {
        return appUserDao.findByEmail(email);
    }

    @Override
    @Transactional()
    public AppUser findBySocialID(String loginType, Long socialID) {
        return appUserDao.findBySocialID(loginType, socialID);
    }

    @Override
    @Transactional()
    public void updateUser(AppUser appUser) {
        appUserDao.update(appUser);
    }

    @Override
    @Transactional()
    public void deleteUser(AppUser appUser) {
        appUserDao.delete(appUser);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AppUser> getAllUsers() {
        return appUserDao.findAll();
    }
}