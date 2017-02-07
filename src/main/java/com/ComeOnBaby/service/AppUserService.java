package com.ComeOnBaby.service;


import com.ComeOnBaby.model.AppUser;

import java.util.List;

public interface AppUserService {
    void addNewUser(AppUser users);
    void updateUser(AppUser users);
    void deleteUser(AppUser users);
    List<AppUser> getAllUsers();
}