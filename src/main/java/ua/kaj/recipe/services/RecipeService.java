package ua.kaj.recipe.services;

import ua.kaj.recipe.commands.RecipeCommand;
import ua.kaj.recipe.domain.Ingredient;
import ua.kaj.recipe.domain.Recipe;

import java.util.Set;

public interface RecipeService extends CrudService<Recipe> {

    RecipeCommand saveRecipeCommand(RecipeCommand command);

    Set<Recipe> findByIngredient(Ingredient ingredient);

    RecipeCommand findCommandById(Long id);
}
