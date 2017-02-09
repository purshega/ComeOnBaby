package com.ComeOnBaby.service;


import com.ComeOnBaby.model.AppUser;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AppUserService {
    Long addNewUser(AppUser users);

    AppUser findById(Long id);
    AppUser findByEmail(String email);
    AppUser findBySocialID(String loginType, Long socialID);

    void updateUser(AppUser users);
    void deleteUser(AppUser users);
    List<AppUser> getAllUsers();
}
