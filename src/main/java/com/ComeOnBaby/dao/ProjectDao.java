package com.ComeOnBaby.dao;



import java.util.List;

/**
 * Created by adm on 1/30/2017.
 */
public interface ProjectDao {
    public Long create(Project project);
    Project read(Long id);
    void update(Project project);
    void delete(Project project);
    List<Project> findAll();
}
