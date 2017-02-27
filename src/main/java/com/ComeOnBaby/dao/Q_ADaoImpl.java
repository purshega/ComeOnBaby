package com.ComeOnBaby.dao;


import com.ComeOnBaby.model.Q_A;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

//TODO Make sure the name is correct "Q_ADao"
@Repository("Q_ADao")
public class Q_ADaoImpl implements Q_ADao {

    private static final Logger logger = LoggerFactory.getLogger(Q_ADaoImpl.class);

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }


    @Override
    public Long create(Q_A aQ_A) {
        Session session = sessionFactory.getCurrentSession();
        Long id = (Long) session.save(aQ_A);
        return id;
    }

    @Override
    public Q_A read(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Q_A aQ_A = (Q_A) session.get(Q_A.class, id);
        return aQ_A;
    }

    @Override
    public void update(Q_A aQ_A) {
        Session session = sessionFactory.getCurrentSession();
        session.update(aQ_A);
        //session.getTransaction().commit();
        logger.error("Q_AService update successfully, Q_AService=" + aQ_A);
    }

    @Override
    public void delete(Q_A aQ_A) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(aQ_A);
        logger.info("Q_AService deleted successfully, Q_AService details=" + aQ_A);
    }

    @Override
    public List<Q_A> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Q_A");
        return query.list();
    }

}
