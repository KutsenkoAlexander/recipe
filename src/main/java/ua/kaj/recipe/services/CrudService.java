package ua.kaj.recipe.services;

import java.util.Set;

public interface CrudService<T> {
    Set<T> findAll();

    T findById(Long id);

    T save(T obj);

    void delete(T obj);

    void deleteById(Long id);
}
