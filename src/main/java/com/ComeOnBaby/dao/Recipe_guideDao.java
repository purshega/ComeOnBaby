package com.ComeOnBaby.dao;



import com.ComeOnBaby.model.Recipe_guide;

import java.util.List;

/**
 * Created by adm on 1/30/2017.
 */
public interface Recipe_guideDao {

    //TODO make sure all the names wrote using NOT CAMEL CASE
    Long create(Recipe_guide recipe_guides);
    Recipe_guide read(Long id);
    void update(Recipe_guide recipe_guides);
    void delete(Recipe_guide recipe_guides);
    List<Recipe_guide> findAll();
}
