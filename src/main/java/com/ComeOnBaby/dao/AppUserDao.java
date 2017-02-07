package com.ComeOnBaby.dao;



import com.ComeOnBaby.model.AppUser;

import java.util.List;

/**
 * Created by adm on 1/30/2017.
 */
public interface AppUserDao {
    Long create(AppUser appUser);
    AppUser read(Long id);
    void update(AppUser appUser);
    void delete(AppUser appUser);
    List<AppUser> findAll();
}
