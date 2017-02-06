package com.ComeOnBaby.service;


import com.ComeOnBaby.dao.CalendarDao;
import com.ComeOnBaby.model.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("calendarService")
public class CalendarServiceImpl implements CalendarService{

    private CalendarDao calendarDao;

    @Autowired(required = true)
    public void setCaseDao(CalendarDao calendarDao) {
        this.calendarDao = calendarDao;
    }

    @Override
    @Transactional
    public void addNewCalendar(Calendar calendar) {
        calendarDao.create(calendar);
    }

    @Override
    @Transactional
    public void updateCalendar(Calendar calendar) {
        calendarDao.update(calendar);
    }

    @Override
    @Transactional
    public void deleteCalendar(Calendar calendar) {
        calendarDao.delete(calendar);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Calendar> getAllCalendar() {
        return calendarDao.findAll();
    }
}