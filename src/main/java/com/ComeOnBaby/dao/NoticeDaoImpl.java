package com.ComeOnBaby.dao;


import com.ComeOnBaby.model.Notice;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("noticeDao")
public class NoticeDaoImpl implements NoticeDao {

    private static final Logger logger = LoggerFactory.getLogger(NoticeDaoImpl.class);

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }


    @Override
    public Long create(Notice aNotice) {
        Session session = sessionFactory.getCurrentSession();
        Long id = (Long) session.save(aNotice);
        session.getTransaction().commit();
        return id;
    }

    @Override
    public Notice read(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Notice aNotice = (Notice) session.get(Notice.class, id);
        return aNotice;
    }

    @Override
    public void update(Notice aNotice) {
        Session session = sessionFactory.getCurrentSession();
        session.update(aNotice);
        session.getTransaction().commit();
        logger.error("Notice update successfully, Notice=" + aNotice);
    }

    @Override
    public void delete(Notice aNotice) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(aNotice);
        logger.info("Notice deleted successfully, Notice details=" + aNotice);
    }

    @Override
    public List<Notice> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Case");
        return query.list();
    }

}
