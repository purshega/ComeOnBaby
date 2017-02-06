package com.ComeOnBaby.dao;


import com.ComeOnBaby.model.Estimate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("estimateDao")
public class EstimateDaoImpl implements EstimateDao {

    static final Logger logger = LoggerFactory.getLogger(EstimateDaoImpl.class);

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    @Override
    public Long create(Estimate estimate) {
        Session session = sessionFactory.getCurrentSession();
        Long id = (Long) session.save(estimate);
        return id;
    }

    @Override
    public Estimate read(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Estimate estimate = (Estimate) session.get(Estimate.class, id);
        return estimate;
    }

    @Override
    public void update(Estimate estimate) {
        Session session = sessionFactory.getCurrentSession();
        session.update(estimate);
        logger.error("Case update successfully, Case=" + estimate);
    }

    @Override
    public void delete(Estimate estimate) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(estimate);
        logger.info("Case deleted successfully, Case details=" + estimate);
    }

    @Override
    public List<Estimate> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Estimate");
        return query.list();
    }
}
