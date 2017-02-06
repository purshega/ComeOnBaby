package com.ComeOnBaby.dao;


import com.ComeOnBaby.model.Message;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("messageDao")
public class MessageDaoImpl implements MessageDao {

    private static final Logger logger = LoggerFactory.getLogger(MessageDaoImpl.class);

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }


    @Override
    public Long create(Message message) {
        Session session = sessionFactory.getCurrentSession();
        Long id = (Long) session.save(message);
        return id;
    }


    @Override
    public Message read(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Message message = (Message) session.get(Message.class, id);
        return message;
    }

    @Override
    public void update(Message message) {
        Session session = sessionFactory.getCurrentSession();
        session.update(message);
        logger.error("Case update successfully, Case=" + message);
    }

    @Override
    public void delete(Message message) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(message);
        logger.info("Case deleted successfully, Case details=" + message);
    }

    @Override
    public List<Message> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Message");
        return query.list();
    }
}
