package com.ComeOnBaby.dao;



import com.ComeOnBaby.model.Status;

import java.util.List;

/**
 * Created by adm on 1/30/2017.
 */
public interface StatusDao {
    public Long create(Status status);
    Status read(Long id);
    void update(Status status);
    void delete(Status status);
    List<Status> findAll();
}
