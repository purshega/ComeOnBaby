package com.ComeOnBaby.dao;


import com.ComeOnBaby.model.Users;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("usersDao")
public class UsersDaoImpl implements UsersDao {

    private static final Logger logger = LoggerFactory.getLogger(UsersDaoImpl.class);

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }


    @Override
    public Long create(Users aUsers) {
        Session session = sessionFactory.getCurrentSession();
        Long id = (Long) session.save(aUsers);
        session.getTransaction().commit();
        return id;
    }

    @Override
    public Users read(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Users aUsers = (Users) session.get(Users.class, id);
        return aUsers;
    }

    @Override
    public void update(Users aUsers) {
        Session session = sessionFactory.getCurrentSession();
        session.update(aUsers);
        session.getTransaction().commit();
        logger.error("Users update successfully, Users=" + aUsers);
    }

    @Override
    public void delete(Users aUsers) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(aUsers);
        logger.info("Users deleted successfully, Users details=" + aUsers);
    }

    @Override
    public List<Users> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Users");
        return query.list();
    }

}
