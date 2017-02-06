package com.ComeOnBaby.service;

import com.ComeOnBaby.model.Calendar;

import java.util.List;


public interface CalendarService {
    void addNewCalendar(Calendar calendar);
    void updateCalendar(Calendar calendar);
    void deleteCalendar(Calendar calendar);
    List<Calendar> getAllCalendar();
}
