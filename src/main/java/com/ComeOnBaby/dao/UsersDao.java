package com.ComeOnBaby.dao;



import com.ComeOnBaby.model.Users;

import java.util.List;

/**
 * Created by adm on 1/30/2017.
 */
public interface UsersDao {
    Long create(Users  users);
    Users read(Long id);
    void update(Users users);
    void delete(Users users);
    List<Users> findAll();
}
