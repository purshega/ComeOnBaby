package com.ComeOnBaby.service;


import com.ComeOnBaby.model.Recipe_guide;

import java.util.List;

public interface Recipe_guideService {
    void addNewRecipe_guide(Recipe_guide recipe_guide);
    void updateRecipe_guide(Recipe_guide recipe_guide);
    void deleteRecipe_guide(Recipe_guide recipe_guide);
    List<Recipe_guide> getAllRecipe_guide();
}
