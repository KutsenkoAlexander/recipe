package ua.kaj.recipe.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.kaj.recipe.domain.Ingredient;
import ua.kaj.recipe.repositories.IngredientRepository;

import java.util.Set;
import java.util.TreeSet;

@AllArgsConstructor
@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    @Override
    public Set<Ingredient> findAll() {
        log.debug("Ingredient Service :: find all");
        Set<Ingredient> recipes = new TreeSet<>(Ingredient::compareTo);
        ingredientRepository.findAll().forEach(recipes :: add);
        return recipes;
    }

    @Override
    public Ingredient findById(Long id) {
        return ingredientRepository.findById(id).orElseThrow(() -> new RuntimeException("Ingredient not found by id="+id));
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    @Override
    public void delete(Ingredient ingredient) {
        ingredientRepository.delete(ingredient);
    }

    @Override
    public void deleteById(Long id) {
        ingredientRepository.deleteById(id);
    }
}
