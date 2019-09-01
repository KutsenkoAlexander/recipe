package ua.kaj.recipe.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import ua.kaj.recipe.domain.Category;
import ua.kaj.recipe.domain.Recipe;
import ua.kaj.recipe.domain.UnitOfMeasure;
import ua.kaj.recipe.repositories.CategoryRepository;
import ua.kaj.recipe.repositories.RecipeRepository;
import ua.kaj.recipe.repositories.UnitOfMeasureRepository;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
    }

    private List<Recipe> getRecipes() {
        List<Recipe> recipes = new ArrayList<>(2);

        //get UOMs
        Optional<UnitOfMeasure> eachUOMOptional = unitOfMeasureRepository.findByDescription("Each");
        if (!eachUOMOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }
        UnitOfMeasure eachUOM = eachUOMOptional.get();

        Optional<UnitOfMeasure> tableSpoonUOMOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
        if (!tableSpoonUOMOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }
        UnitOfMeasure tableSpoonUOM = tableSpoonUOMOptional.get();

        Optional<UnitOfMeasure> teaSpoonUOMOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
        if (!teaSpoonUOMOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }
        UnitOfMeasure teaSpoonUOM = teaSpoonUOMOptional.get();

        Optional<UnitOfMeasure> dashUOMOptional = unitOfMeasureRepository.findByDescription("Dash");
        if (!dashUOMOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }
        UnitOfMeasure dashUOM = dashUOMOptional.get();

        Optional<UnitOfMeasure> pintUOMOptional = unitOfMeasureRepository.findByDescription("Pint");
        if (!pintUOMOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }
        UnitOfMeasure pintUOM = pintUOMOptional.get();

        Optional<UnitOfMeasure> cupUOMOptional = unitOfMeasureRepository.findByDescription("Cup");
        if (!cupUOMOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }
        UnitOfMeasure cupUOM = cupUOMOptional.get();

        //get Categories
        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");
        if (!americanCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected Category Not Found");
        }


        return recipes;
    }
}
