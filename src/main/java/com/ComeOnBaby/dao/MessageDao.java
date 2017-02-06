package com.ComeOnBaby.dao;



import java.util.List;

/**
 * Created by adm on 1/30/2017.
 */
public interface MessageDao {
    public Long create(Message message);
    Message read(Long id);
    void update(Message message);
    void delete(Message message);
    List<Message> findAll();
}
