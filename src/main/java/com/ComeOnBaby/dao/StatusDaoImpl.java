package com.ComeOnBaby.dao;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("statusDao")
public class StatusDaoImpl implements StatusDao {

    private static final Logger logger = LoggerFactory.getLogger(StatusDaoImpl.class);
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }


    @Override
    public Long create(Status status) {
        Session session = sessionFactory.getCurrentSession();
        Long id = (Long) session.save(status);
        return id;
    }


    @Override
    public Status read(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Status status = (Status) session.get(Status.class, id);
        logger.error("Case read successfully, Case=" + status);
        return status;
    }

    @Override
    public void update(Status status) {
        Session session = sessionFactory.getCurrentSession();
        session.update(status);
        logger.error("Case update successfully, Case=" + status);
    }

    @Override
    public void delete(Status status) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(status);
        logger.info("Case deleted successfully, Case details=" + status);
    }

    @Override
    public List<Status> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Status");
        return query.list();
    }
}
