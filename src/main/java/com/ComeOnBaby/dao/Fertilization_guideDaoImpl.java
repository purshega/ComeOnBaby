package com.ComeOnBaby.dao;



import com.ComeOnBaby.model.Fertilization_guide;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("fertilization_guideDao")
public class Fertilization_guideDaoImpl implements Fertilization_guideDao {

    private static final Logger logger = LoggerFactory.getLogger(Fertilization_guideDaoImpl.class);

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }


    @Override
    public Long create(Fertilization_guide fertilization_guide) {
        Session session = sessionFactory.getCurrentSession();
        Long id = (Long) session.save(fertilization_guide);
        return id;
    }

    @Override
    public Fertilization_guide read(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Fertilization_guide fertilization_guide = (Fertilization_guide) session.get(Fertilization_guide.class, id);
        logger.error("CommentsService findById successfully, CommentsService=" + fertilization_guide);
        return fertilization_guide;
    }

    @Override
    public void update(Fertilization_guide fertilization_guide) {
        Session session = sessionFactory.getCurrentSession();
        session.update(fertilization_guide);
        logger.error("Fertilization_guide update successfully, Fertilization_guide=" + fertilization_guide);
    }

    @Override
    public void delete(Fertilization_guide fertilization_guide) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(fertilization_guide);
        logger.info("Fertilization_guide deleted successfully, Fertilization_guide details=" + fertilization_guide);
    }

    @Override
    public List<Fertilization_guide> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query query =  session.createQuery("from Fertilization_guide");
        return query.list();
    }
}
