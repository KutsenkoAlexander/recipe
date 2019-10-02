package ua.kaj.recipe.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.kaj.recipe.domain.Ingredient;
import ua.kaj.recipe.domain.Recipe;

import java.util.Set;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    Set<Recipe> findByIngredients(Ingredient ingredient);
}
