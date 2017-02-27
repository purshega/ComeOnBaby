package com.ComeOnBaby.dao;

import com.ComeOnBaby.model.Comment;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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
    public Long create(Comment comment) {
        Session session = sessionFactory.getCurrentSession();
        Long id = (Long) session.save(comment);
        return id;
    }

    @Override
    public Comment read(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Comment comment = (Comment) session.get(Comment.class, id);
        logger.error("CommentsService findById successfully, CommentsService=" + comment);
        return comment;
    }

    @Override
    public void update(Comment comment) {
        Session session = sessionFactory.getCurrentSession();
        session.update(comment);
        logger.error("CommentsService update successfully, CommentsService=" + comment);
    }

    @Override
    public void delete(Comment comment) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(comment);
        logger.info("CommentsService deleted successfully, CommentsService details=" + comment);
    }

    @Override
    public List<Comment> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query query =  session.createQuery("from Comments");
        return query.list();
    }

    @Override
    public List<Comment> findByBlogID(Long blogID) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Comment.class).add(Restrictions.like("id_blog", blogID));
        List<Comment> list = criteria.list();
        return list;
    }
}


