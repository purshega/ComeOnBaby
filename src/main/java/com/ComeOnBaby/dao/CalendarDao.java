package com.ComeOnBaby.dao;


import com.ComeOnBaby.model.Calendar;

import java.util.List;

public interface CalendarDao {
    Long create(Calendar calendar);
    Calendar read(Long id);
    void update(Calendar calendar);
    void delete(Calendar calendar);
    List<Calendar> findAll();
}
