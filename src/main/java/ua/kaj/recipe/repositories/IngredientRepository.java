package ua.kaj.recipe.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.kaj.recipe.domain.Ingredient;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
}
