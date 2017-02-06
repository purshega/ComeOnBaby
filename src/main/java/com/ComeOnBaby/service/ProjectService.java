package com.ComeOnBaby.service;



import java.util.List;

/**
 * Created by adm on 1/30/2017.
 */
public interface ProjectService {
    void addNewProject(Project project);
    void updateProject(Project project);
    void deleteProject(Project project);
    List<Project> getAllProjects();
}
