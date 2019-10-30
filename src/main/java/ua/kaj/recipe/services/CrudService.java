package ua.kaj.recipe.services;

import ua.kaj.recipe.domain.Recipe;

import java.util.Set;

public interface CrudService<T> {
    Set<T> findAll();

    T findById(Long id);

    T save(T recipe);

    void delete(Recipe recipe);

    void deleteById(Long id);
}
