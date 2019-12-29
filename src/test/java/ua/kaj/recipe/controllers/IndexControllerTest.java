package ua.kaj.recipe.controllers;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import ua.kaj.recipe.domain.Recipe;
import ua.kaj.recipe.services.RecipeServiceImpl;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class IndexControllerTest {

    @Mock
    Model model;
    @Mock
    RecipeServiceImpl recipeService;
    @InjectMocks
    IndexController controller;

    private Set<Recipe> recipes;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() throws Exception {
        recipes = new HashSet<>();
        recipes.add(Recipe.builder().id(1L).build());
        recipes.add(Recipe.builder().id(2L).build());
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testRecipe() throws Exception {
        when(recipeService.findAll()).thenReturn(recipes);
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("recipes", Matchers.hasSize(2)));
    }

    @Test
    void testRecipeByIndex() throws Exception {
        when(recipeService.findAll()).thenReturn(recipes);
        mockMvc.perform(get("/index/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("recipes", Matchers.hasSize(2)));
    }

    @Test
    void getIndexPage() {
        when(recipeService.findAll()).thenReturn(recipes);
        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);
        assertEquals("index", controller.getIndexPage(model));
        verify(recipeService, times(1)).findAll();
        verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
        assertEquals(2, argumentCaptor.getValue().size());
    }
}
