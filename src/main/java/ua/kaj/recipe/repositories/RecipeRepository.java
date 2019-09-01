package ua.kaj.recipe.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.kaj.recipe.domain.Recipe;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
