package com.ComeOnBaby.dao;


import com.ComeOnBaby.model.Images;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("imagesDao")
public class ImagesDaoImpl implements ImagesDao { private static final Logger logger = LoggerFactory.getLogger(ImagesDaoImpl.class);

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }


    @Override
    public Long create(Images images) {
        Session session = sessionFactory.getCurrentSession();
        Long id = (Long) session.save(images);
        return id;
    }

    @Override
    public Images read(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Images images = (Images) session.get(Images.class, id);
        logger.error("Images findById successfully, Images=" + images);
        return images;
    }

    @Override
    public void update(Images images) {
        Session session = sessionFactory.getCurrentSession();
        session.update(images);
        logger.error("Images update successfully, Images=" + images);
    }

    @Override
    public void delete(Images images) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(images);
        logger.info("Images deleted successfully, Images details=" + images);
    }

    @Override
    public List<Images> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query query =  session.createQuery("from Images");
        return query.list();
    }

}

