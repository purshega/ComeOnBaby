package com.ComeOnBaby.dao;


import com.ComeOnBaby.model.Likes;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("likesDao")
public class LikesDaoImpl implements LikesDao {

    private static final Logger logger = LoggerFactory.getLogger(LikesDaoImpl.class);

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    @Override
    public Long create(Likes aLikes) {
        Session session = sessionFactory.getCurrentSession();
        Long id = (Long) session.save(aLikes);
        return id;
    }

    @Override
    public Likes read(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Likes aLikes = (Likes) session.get(Likes.class, id);
        return aLikes;
    }

    @Override
    public void update(Likes aLikes) {
        Session session = sessionFactory.getCurrentSession();
        session.update(aLikes);
        logger.error("Likes update successfully, Likes=" + aLikes);
    }

    @Override
    public void delete(Likes aLikes) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(aLikes);
        logger.info("Likes deleted successfully, Likes details=" + aLikes);
    }

    @Override
    public List<Likes> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Likes");
        return query.list();
    }
}
