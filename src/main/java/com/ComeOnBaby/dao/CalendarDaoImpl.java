package com.ComeOnBaby.dao;



import com.ComeOnBaby.model.Calendar;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("calendarDao")
public class CalendarDaoImpl implements CalendarDao {

    private static final Logger logger = LoggerFactory.getLogger(CalendarDaoImpl.class);

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }


    @Override
    public Long create(Calendar calendar) {
        Session session = sessionFactory.getCurrentSession();
        Long id = (Long) session.save(calendar);
        return id;
    }

    @Override
    public Calendar read(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Calendar calendar = (Calendar) session.get(Calendar.class, id);
        logger.error("Calendar findById successfully, Calendar=" + calendar);
        return calendar;
    }

    @Override
    public void update(Calendar calendar) {
        Session session = sessionFactory.getCurrentSession();
        session.update(calendar);
        logger.error("Calendar update successfully, Calendar=" + calendar);
    }

    @Override
    public void delete(Calendar calendar) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(calendar);
        logger.info("Calendar deleted successfully, Calendar details=" + calendar);
    }

    @Override
    public List<Calendar> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query query =  session.createQuery("from Calendar");
        return query.list();
    }

}


