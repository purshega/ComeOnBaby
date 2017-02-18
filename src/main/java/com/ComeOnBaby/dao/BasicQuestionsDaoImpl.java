package com.ComeOnBaby.dao;


import com.ComeOnBaby.model.BasicQuestions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("basicQuestionDao")
public class BasicQuestionsDaoImpl implements BasicQuestionsDao {

    private static final Logger logger = LoggerFactory.getLogger(BasicQuestionsDaoImpl.class);

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }


    @Override
    public Long create(BasicQuestions basicQuestions) {
        Session session = sessionFactory.getCurrentSession();
        Long id = (Long) session.save(basicQuestions);
        return id;
    }

    @Override
    public BasicQuestions read(Long id) {
        Session session = sessionFactory.getCurrentSession();
        BasicQuestions basicQuestions = (BasicQuestions) session.get(BasicQuestions.class, id);
        logger.error("BasicQuestions findById successfully, BasicQuestions=" + basicQuestions);
        return basicQuestions;
    }

    @Override
    public void update(BasicQuestions basicQuestions) {
        Session session = sessionFactory.getCurrentSession();
        session.update(basicQuestions);
        logger.error("BasicQuestions update successfully, BasicQuestions=" + basicQuestions);
    }

    @Override
    public void delete(BasicQuestions basicQuestions) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(basicQuestions);
        logger.info("BasicQuestions deleted successfully, BasicQuestions details=" + basicQuestions);
    }

    @Override
    public List<BasicQuestions> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query query =  session.createQuery("from BasicQuestions");
        return query.list();
    }

}
