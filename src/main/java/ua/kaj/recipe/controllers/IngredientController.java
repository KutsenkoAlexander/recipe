package ua.kaj.recipe.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.kaj.recipe.commands.RecipeCommand;
import ua.kaj.recipe.services.RecipeService;

@Controller
@Slf4j
@AllArgsConstructor
public class IngredientController {

    private final RecipeService recipeService;

    @GetMapping
    @RequestMapping({"/recipe/{id}/ingredients"})
    public String listIngredients(@PathVariable Long id, Model model) {
        log.debug("Ingredient Controller :: getting ingredient list for recipe ID:{}", id);
        RecipeCommand recipeCommand = recipeService.findCommandById(id);
        model.addAttribute("recipe", recipeCommand);
        return "recipe/ingredients/list";
    }

}
