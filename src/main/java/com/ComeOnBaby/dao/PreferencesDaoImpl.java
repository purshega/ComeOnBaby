package com.ComeOnBaby.dao;


import com.ComeOnBaby.model.Preferences;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("preferencesDao")
public class PreferencesDaoImpl implements PreferencesDao {

    private static final Logger logger = LoggerFactory.getLogger(PreferencesDaoImpl.class);

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }


    @Override
    public Long create(Preferences aPreferences) {
        Session session = sessionFactory.getCurrentSession();
        Long id = (Long) session.save(aPreferences);
        return id;
    }

    @Override
    public Preferences read(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Preferences aPreferences = (Preferences) session.get(Preferences.class, id);
        return aPreferences;
    }

    @Override
    public void update(Preferences aPreferences) {
        Session session = sessionFactory.getCurrentSession();
        session.update(aPreferences);
        logger.error("Preferences update successfully, Preferences=" + aPreferences);
    }

    @Override
    public void delete(Preferences aPreferences) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(aPreferences);
        logger.info("Preferences deleted successfully, Preferences details=" + aPreferences);
    }

    @Override
    public List<Preferences> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Preferences");
        return query.list();
    }

    @Override
    public Preferences findFirstByUserId(Long id) {
        Preferences pref = null;
        Session session = sessionFactory.getCurrentSession();

        List<Preferences> list =  session.createCriteria(Preferences.class)
                .add(Restrictions.eq("id", id))
                .list();
        if(list !=null && list.size()>0) return list.get(0);
        return pref;
    }
}
