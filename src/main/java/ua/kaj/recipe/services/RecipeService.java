package ua.kaj.recipe.services;

import ua.kaj.recipe.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
}
