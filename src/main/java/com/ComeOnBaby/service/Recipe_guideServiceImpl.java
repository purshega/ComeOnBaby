package com.ComeOnBaby.service;

import com.ComeOnBaby.dao.Recipe_guideDao;
import com.ComeOnBaby.model.Recipe_guide;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("recipe_guideService")
public class Recipe_guideServiceImpl implements Recipe_guideService {
    private Recipe_guideDao recipe_guideDao;

    @Autowired(required = true)
    public void setCaseDao(Recipe_guideDao recipe_guideDao) {
        this.recipe_guideDao = recipe_guideDao;
    }

    @Override
    @Transactional
    public void addNewRecipe_guide(Recipe_guide recipe_guide) {
        recipe_guideDao.create(recipe_guide);
    }

    @Override
    @Transactional
    public void updateRecipe_guide(Recipe_guide recipe_guide) {
        recipe_guideDao.update(recipe_guide);
    }

    @Override
    @Transactional
    public void deleteRecipe_guide(Recipe_guide recipe_guide) {
        recipe_guideDao.delete(recipe_guide);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Recipe_guide> getAllRecipe_guide() {
        return recipe_guideDao.findAll();
    }
}