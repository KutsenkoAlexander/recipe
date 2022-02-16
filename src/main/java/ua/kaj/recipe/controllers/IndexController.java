package ua.kaj.recipe.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.kaj.recipe.services.RecipeService;

@Slf4j
@Controller
@AllArgsConstructor
public class IndexController {

    private final RecipeService recipeService;

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(final Model model) {
        log.debug("Getting Index page.");
        model.addAttribute("recipes", recipeService.findAll());
        return "index";
    }
}
