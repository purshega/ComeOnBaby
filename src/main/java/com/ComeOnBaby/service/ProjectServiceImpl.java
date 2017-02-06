package com.ComeOnBaby.service;


import com.ComeOnBaby.dao.ProjectDao;
import com.ComeOnBaby.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by adm on 1/27/2017.
 */
@Service("projectService")
public class ProjectServiceImpl implements ProjectService {

    private ProjectDao projectDao;

    @Autowired(required=true)
    public ProjectServiceImpl(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    @Override
    @Transactional
    public void addNewProject(Project project) {
        projectDao.create(project);
    }

    @Override
    @Transactional
    public void updateProject(Project project) {
        projectDao.update(project);
    }

    @Override
    @Transactional
    public void deleteProject(Project project) {
        projectDao.delete(project);
    }

    @Override
    @Transactional(readOnly=true)
    public List<Project> getAllProjects() {
        return projectDao.findAll();
    }
}
