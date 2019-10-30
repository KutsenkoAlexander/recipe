package ua.kaj.recipe.services.datajpa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.kaj.recipe.domain.Ingredient;
import ua.kaj.recipe.domain.Recipe;
import ua.kaj.recipe.repositories.RecipeRepository;
import ua.kaj.recipe.services.CrudService;

import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

@Slf4j
@Service
public class RecipeServiceImpl implements CrudService<Recipe> {

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
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
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);
        if (!recipeOptional.isPresent()) {
            throw new RuntimeException("Recipe not found");
        }
        return recipeOptional.get();
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

    public Set<Recipe> findByIngredient(Ingredient ingredient) {
        return recipeRepository.findByIngredients(ingredient);
    }
}
