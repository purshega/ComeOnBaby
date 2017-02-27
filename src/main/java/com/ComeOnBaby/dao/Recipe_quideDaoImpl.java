package com.ComeOnBaby.dao;


import com.ComeOnBaby.model.Recipe_guide;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("recipe_guideDao")
public class Recipe_quideDaoImpl implements Recipe_guideDao {

    private static final Logger logger = LoggerFactory.getLogger(Recipe_quideDaoImpl.class);

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }


    @Override
    public Long create(Recipe_guide aRecipe_guide) {
        Session session = sessionFactory.getCurrentSession();
        Long id = (Long) session.save(aRecipe_guide);
        return id;
    }

    @Override
    public Recipe_guide read(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Recipe_guide aRecipe_guide = (Recipe_guide) session.get(Recipe_guide.class, id);
        return aRecipe_guide;
    }

    @Override
    public void update(Recipe_guide aRecipe_guide) {
        Session session = sessionFactory.getCurrentSession();
        session.update(aRecipe_guide);
        //session.getTransaction().commit();
        logger.error("Recipe_guide update successfully, Recipe_guide=" + aRecipe_guide);
    }

    @Override
    public void delete(Recipe_guide aRecipe_guide) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(aRecipe_guide);
        logger.info("Recipe_guide deleted successfully, Recipe_guide details=" + aRecipe_guide);
    }

    @Override
    public List<Recipe_guide> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Recipe_guide");
        return query.list();
    }

}
