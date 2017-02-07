package com.ComeOnBaby.dao;


import com.ComeOnBaby.model.AppUser;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("usersDao")
public class AppUserDaoImpl implements AppUserDao {

    private static final Logger logger = LoggerFactory.getLogger(AppUserDaoImpl.class);

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }


    @Override
    public Long create(AppUser appUser) {
        Session session = sessionFactory.getCurrentSession();
        Long id = (Long) session.save(appUser);
        session.getTransaction().commit();
        return id;
    }

    @Override
    public AppUser read(Long id) {
        Session session = sessionFactory.getCurrentSession();
        AppUser appUser = (AppUser) session.get(AppUser.class, id);
        return appUser;
    }

    @Override
    public void update(AppUser appUser) {
        Session session = sessionFactory.getCurrentSession();
        session.update(appUser);
        session.getTransaction().commit();
        logger.error("AppUser update successfully, AppUser=" + appUser);
    }

    @Override
    public void delete(AppUser appUser) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(appUser);
        logger.info("AppUser deleted successfully, AppUser details=" + appUser);
    }

    @Override
    public List<AppUser> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from AppUser");
        return query.list();
    }

}
