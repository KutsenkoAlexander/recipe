package ua.kaj.recipe.services.datajpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.kaj.recipe.domain.Ingredient;
import ua.kaj.recipe.domain.Recipe;
import ua.kaj.recipe.repositories.RecipeRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

    private RecipeServiceImpl recipeService;
    private final Long recipeId = 1L;

    @Mock
    RecipeRepository recipeRepository;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    void findAll() {
        Set<Recipe> recipeData = new TreeSet<>(Recipe::compareTo);
        Recipe recipe = new Recipe();
        recipe.setId(recipeId);
        recipeData.add(recipe);
        when(recipeRepository.findAll()).thenReturn(recipeData);
        Set<Recipe> recipes = recipeService.findAll();
        assertEquals(recipes.size(), 1);
        assertNotNull(recipes, "Recipes not found");
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        Recipe recipe = new Recipe();
        recipe.setId(recipeId);
        when(recipeRepository.findById(recipeId)).thenReturn(Optional.of(recipe));
        Recipe foundRecipe = recipeService.findById(recipeId);
        assertEquals(recipeId, foundRecipe.getId());
        assertNotNull(foundRecipe, "Recipes not found");
        verify(recipeRepository, times(1)).findById(recipeId);
        verify(recipeRepository, never()).findAll();
    }

    @Test
    void save() {
        Recipe recipe = new Recipe();
        recipe.setId(recipeId);
        when(recipeRepository.save(recipe)).thenReturn(recipe);
        Recipe savedRecipe = recipeService.save(recipe);
        assertEquals(recipeId, savedRecipe.getId());
        verify(recipeRepository, times(1)).save(recipe);
    }

    @Test
    void delete() {
        Recipe recipe = new Recipe();
        recipe.setId(recipeId);
        when(recipeRepository.findById(recipeId)).thenReturn(Optional.of(recipe));
        assertEquals(Optional.of(recipe).get(), recipeService.findById(recipeId));
        doNothing().when(recipeRepository).delete(recipe);
        recipeService.delete(recipe);
        assertEquals(0, recipeService.findAll().size());
    }

    @Test
    void deleteById() {
        Recipe recipe = new Recipe();
        recipe.setId(recipeId);
        when(recipeRepository.findById(recipeId)).thenReturn(Optional.of(recipe));
        assertEquals(Optional.of(recipe).get(), recipeService.findById(recipeId));
        doNothing().when(recipeRepository).deleteById(recipeId);
        recipeService.deleteById(recipeId);
        assertEquals(0, recipeService.findAll().size());
    }

    @Test
    void findByIngredient() {
        Ingredient ingredient = new Ingredient();
        ingredient.setDescription("Teaspoon");

        Set<Ingredient> ingredients = new HashSet<>();
        ingredients.add(ingredient);

        Recipe recipe = new Recipe();
        recipe.setId(recipeId);
        recipe.setIngredients(ingredients);

        Set<Recipe> recipes = new HashSet<>();
        recipes.add(recipe);

        when(recipeRepository.findByIngredients(ingredient)).thenReturn(recipes);
        Set<Recipe> foundRecipes = recipeService.findByIngredient(ingredient);
        assertEquals(1, foundRecipes.size());
        verify(recipeRepository, times(1)).findByIngredients(ingredient);
    }
}