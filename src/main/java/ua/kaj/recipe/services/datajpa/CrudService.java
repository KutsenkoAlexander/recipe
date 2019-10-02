package ua.kaj.recipe.services.datajpa;

import ua.kaj.recipe.domain.Recipe;

import java.util.Optional;
import java.util.Set;

public interface CrudService<T> {
    Set<T> findAll();

    Optional<T> findById(Long id);

    T save(T recipe);

    void delete(Recipe recipe);

    void deleteById(Long id);
}
