package com.ComeOnBaby.dao;



import com.ComeOnBaby.model.Project;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("projectDao")
public class ProjectDaoImpl implements ProjectDao {

    private static final Logger logger = LoggerFactory.getLogger(ProjectDaoImpl.class);

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }


    @Override
    public Long create(Project project) {
        Session session = sessionFactory.getCurrentSession();
        Long id = (Long) session.save(project);
        return id;
    }

    @Override
    public Project read(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Project project = (Project) session.get(Project.class, id);
        return project;
    }

    @Override
    public void update(Project project) {
        Session session = sessionFactory.getCurrentSession();
        session.update(project);
        logger.error("Case update successfully, Case=" + project);
    }

    @Override
    public void delete(Project project) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(project);
        logger.info("Case deleted successfully, Case details=" + project);
    }

    @Override
    public List<Project> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Project");
        return query.list();
    }
}
