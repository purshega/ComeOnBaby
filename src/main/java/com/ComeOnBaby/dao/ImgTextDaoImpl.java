package com.ComeOnBaby.dao;

import com.ComeOnBaby.model.ImgText;
import com.ComeOnBaby.model.Likes;
import com.ComeOnBaby.model.Notice;
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

/**
 * Created by Макс on 24.02.2017.
 */

@Repository("imgTextDao")
public class ImgTextDaoImpl implements ImgTextDao {
    private static final Logger logger = LoggerFactory.getLogger(LikesDaoImpl.class);

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Long create(ImgText obj) {
        Session session = sessionFactory.getCurrentSession();
        Long id = (Long) session.save(obj);
        return id;
    }

    @Override
    public ImgText read(Long id) {
        Session session = sessionFactory.getCurrentSession();
        ImgText obj = (ImgText) session.get(ImgText.class, id);
        return obj;
    }

    @Override
    public void update(ImgText obj) {
        Session session = sessionFactory.getCurrentSession();
        session.update(obj);
    }

    @Override
    public void delete(ImgText obj) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(obj);
    }

    @Override
    public List<ImgText> findAllByNotice(Notice notice) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(ImgText.class).
                add(Restrictions.eq("notice_id",notice.getId()));
        return criteria.list();
    }
}
