package com.ComeOnBaby.dao;



import com.ComeOnBaby.model.Comments;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("commentsDao")
public class CommentsDaoImpl implements CommentsDao {

    private static final Logger logger = LoggerFactory.getLogger(CommentsDaoImpl.class);

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    @Override
    public Long create(Comments comments) {
        Session session = sessionFactory.getCurrentSession();
        Long id = (Long) session.save(comments);
        return id;
    }

    @Override
    public Comments read(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Comments comments = (Comments) session.get(Comments.class, id);
        logger.error("Comments read successfully, Comments=" + comments);
        return comments;
    }

    @Override
    public void update(Comments comments) {
        Session session = sessionFactory.getCurrentSession();
        session.update(comments);
        logger.error("Comments update successfully, Comments=" + comments);
    }

    @Override
    public void delete(Comments comments) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(comments);
        logger.info("Comments deleted successfully, Comments details=" + comments);
    }

    @Override
    public List<Comments> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query query =  session.createQuery("from Comments");
        return query.list();
    }

}


