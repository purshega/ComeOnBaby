package com.ComeOnBaby.service;


import com.ComeOnBaby.model.Users;

import java.util.List;

public interface UsersService {
    void addNewUsers(Users users);
    void updateUsers(Users users);
    void deleteUsers(Users users);
    List<Users> getAllUsers();
}
