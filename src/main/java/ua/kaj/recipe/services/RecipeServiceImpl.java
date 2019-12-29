package ua.kaj.recipe.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kaj.recipe.commands.RecipeCommand;
import ua.kaj.recipe.converters.RecipeCommandToRecipe;
import ua.kaj.recipe.converters.RecipeToRecipeCommand;
import ua.kaj.recipe.domain.Ingredient;
import ua.kaj.recipe.domain.Recipe;
import ua.kaj.recipe.repositories.RecipeRepository;

import java.util.Set;
import java.util.TreeSet;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeRepository recipeRepository,
                             RecipeCommandToRecipe recipeCommandToRecipe,
                             RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    public Set<Recipe> findAll() {
        log.debug("Service in an action.");
        Set<Recipe> recipes = new TreeSet<>(Recipe::compareTo);
        recipeRepository.findAll().forEach(recipes :: add);
        return recipes;
    }

    @Override
    public Recipe findById(Long id) {
        return recipeRepository.findById(id).orElseThrow(() -> new RuntimeException("Recipe not found by id="+id));
    }

    @Override
    public Recipe save(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @Override
    public void delete(Recipe recipe) {
        recipeRepository.delete(recipe);
    }

    @Override
    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
        Recipe detachedRecipe = recipeCommandToRecipe.convert(command);
        assert detachedRecipe != null;
        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        log.debug("Saved RecipeId:" + savedRecipe.getId());
        return recipeToRecipeCommand.convert(savedRecipe);
    }

    @Override
    @Transactional
    public Set<Recipe> findByIngredient(Ingredient ingredient) {
        return recipeRepository.findByIngredients(ingredient);
    }

    @Override
    @Transactional
    public RecipeCommand findCommandById(Long id) {
        return recipeToRecipeCommand.convert(findById(id));
    }
}
