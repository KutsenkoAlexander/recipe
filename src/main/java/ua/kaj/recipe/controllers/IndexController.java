package ua.kaj.recipe.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.kaj.recipe.services.CrudService;

@Slf4j
@Controller
public class IndexController {

    private final CrudService recipeService;

    public IndexController(CrudService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(final Model model) {
        log.debug("Getting Index page.");
        model.addAttribute("recipes", recipeService.findAll());
        return "index";
    }
}
