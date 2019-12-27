package ua.kaj.recipe.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.kaj.recipe.commands.IngredientCommand;
import ua.kaj.recipe.domain.Ingredient;
import ua.kaj.recipe.domain.Recipe;
import ua.kaj.recipe.domain.UnitOfMeasure;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class IngredientToIngredientCommandTest {

    public static final Recipe RECIPE = new Recipe();
    public static final BigDecimal AMOUNT = BigDecimal.ONE;
    public static final String DESCRIPTION = "Description";
    public static final Long UOM_ID = 2L;
    public static final Long ID_VALUE = 1L;

    IngredientToIngredientCommand ingredientConverter;
    UnitOfMeasureToUnitOfMeasureCommand uomConverter;

    @BeforeEach
    void setUp() {
        uomConverter = new UnitOfMeasureToUnitOfMeasureCommand();
        ingredientConverter = new IngredientToIngredientCommand(uomConverter);
    }

    @Test
    public void testNullConvert() {
        assertNull(ingredientConverter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(ingredientConverter.convert(new Ingredient()));
    }

    @Test
    void convert() {
        //given
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(UOM_ID);

        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID_VALUE);
        ingredient.setRecipe(RECIPE);
        ingredient.setAmount(AMOUNT);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setUom(uom);

        //when
        IngredientCommand ingredientCommand = ingredientConverter.convert(ingredient);

        //then
        assertEquals(ID_VALUE, ingredientCommand.getId());
        assertNotNull(ingredientCommand.getUnitOfMeasure());
        assertEquals(UOM_ID, ingredientCommand.getUnitOfMeasure().getId());
        assertEquals(AMOUNT, ingredientCommand.getAmount());
        assertEquals(DESCRIPTION, ingredientCommand.getDescription());
    }

    @Test
    public void testConvertNullUOM() {
        //given
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID_VALUE);
        ingredient.setRecipe(RECIPE);
        ingredient.setAmount(AMOUNT);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setUom(null);

        //when
        IngredientCommand ingredientCommand = ingredientConverter.convert(ingredient);

        //then
        assertNull(ingredientCommand.getUnitOfMeasure());
        assertEquals(ID_VALUE, ingredientCommand.getId());
        assertEquals(AMOUNT, ingredientCommand.getAmount());
        assertEquals(DESCRIPTION, ingredientCommand.getDescription());
    }
}
