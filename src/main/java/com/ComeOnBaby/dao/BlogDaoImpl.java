package com.ComeOnBaby.dao;


import com.ComeOnBaby.model.Blog;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("blogDao")
public class BlogDaoImpl implements BlogDao{

    private static final Logger logger = LoggerFactory.getLogger(BlogDaoImpl.class);

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    @Override
    public Long create(Blog blog) {
        Session session = sessionFactory.getCurrentSession();
        Long id = (Long) session.save(blog);
        return id;
    }

    @Override
    public Blog read(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Blog blog = (Blog) session.get(Blog.class, id);
        logger.error("Blog read successfully, Blog=" + blog);
        return blog;
    }

    @Override
    public void update(Blog blog) {
        Session session = sessionFactory.getCurrentSession();
        session.update(blog);
        logger.error("Blog update successfully, Blog=" + blog);
    }

    @Override
    public void delete(Blog blog) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(blog);
        logger.info("Blog deleted successfully, Blog details=" + blog);
    }

    @Override
    public List<Blog> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query query =  session.createQuery("from Blog");
        return query.list();
    }
}
