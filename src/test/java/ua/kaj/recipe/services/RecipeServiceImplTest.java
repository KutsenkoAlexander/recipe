package ua.kaj.recipe.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.kaj.recipe.commands.RecipeCommand;
import ua.kaj.recipe.converters.RecipeToRecipeCommand;
import ua.kaj.recipe.domain.Ingredient;
import ua.kaj.recipe.domain.Recipe;
import ua.kaj.recipe.repositories.RecipeRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RecipeServiceImplTest {

    private final Long recipeId = 1L;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @InjectMocks
    private RecipeServiceImpl recipeService;

    @Test
    void findAll() {
        //given
        Set<Recipe> recipeData = new TreeSet<>(Recipe::compareTo);
        Recipe recipe = new Recipe();
        recipe.setId(recipeId);
        recipeData.add(recipe);

        //when
        when(recipeRepository.findAll()).thenReturn(recipeData);

        Set<Recipe> recipes = recipeService.findAll();

        //then
        assertEquals(1, recipes.size());
        assertNotNull(recipes, "Recipes not found");
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        //given
        Recipe recipe = new Recipe();
        recipe.setId(recipeId);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        //when
        when(recipeRepository.findById(recipeId)).thenReturn(recipeOptional);

        Recipe foundRecipe = recipeService.findById(recipeId);

        //then
        assertEquals(recipeId, foundRecipe.getId());
        assertNotNull(foundRecipe, "Recipes not found");
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }

    @Test
    void save() {
        //given
        Recipe recipe = new Recipe();
        recipe.setId(recipeId);

        //when
        when(recipeRepository.save(recipe)).thenReturn(recipe);

        Recipe savedRecipe = recipeService.save(recipe);

        //then
        assertEquals(recipeId, savedRecipe.getId());
        verify(recipeRepository, times(1)).save(any());
    }

    @Test
    void delete() {
        //given
        Recipe recipe = new Recipe();
        recipe.setId(recipeId);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        //when
        when(recipeRepository.findById(recipeId)).thenReturn(recipeOptional);
        doNothing().when(recipeRepository).delete(recipe);

        recipeService.delete(recipe);

        //then
        assertEquals(recipeOptional.get(), recipeService.findById(recipeId));
        assertEquals(0, recipeService.findAll().size());
        verify(recipeRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        //given
        Recipe recipe = new Recipe();
        recipe.setId(recipeId);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        //when
        when(recipeRepository.findById(recipeId)).thenReturn(recipeOptional);
        doNothing().when(recipeRepository).deleteById(recipeId);

        recipeService.deleteById(recipeId);

        //then
        assertEquals(recipeOptional.get(), recipeService.findById(recipeId));
        assertEquals(0, recipeService.findAll().size());
        verify(recipeRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void findByIngredient() {
        //given
        Ingredient ingredient = new Ingredient();
        ingredient.setDescription("Teaspoon");

        Set<Ingredient> ingredients = new HashSet<>();
        ingredients.add(ingredient);

        Recipe recipe = new Recipe();
        recipe.setId(recipeId);
        recipe.setIngredients(ingredients);

        Set<Recipe> recipes = new HashSet<>();
        recipes.add(recipe);

        //when
        when(recipeRepository.findByIngredients(ingredient)).thenReturn(recipes);

        Set<Recipe> foundRecipes = recipeService.findByIngredient(ingredient);

        //then
        assertEquals(1, foundRecipes.size());
        verify(recipeRepository, times(1)).findByIngredients(any());
    }

    @Test
    public void getRecipeCommandByIdTest() {
        //given
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);

        //when
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        when(recipeToRecipeCommand.convert(any())).thenReturn(recipeCommand);

        RecipeCommand commandById = recipeService.findCommandById(1L);

        //then
        assertNotNull(String.valueOf(commandById));
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }
}
