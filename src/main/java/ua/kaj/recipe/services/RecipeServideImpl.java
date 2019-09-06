package ua.kaj.recipe.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.kaj.recipe.domain.Recipe;
import ua.kaj.recipe.repositories.RecipeRepository;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class RecipeServideImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeServideImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getRecipes() {
        log.debug("Service in an action.");
        Set<Recipe> recipes = new HashSet<>();
        recipeRepository.findAll().forEach(recipes :: add);
        return recipes;
    }
}
