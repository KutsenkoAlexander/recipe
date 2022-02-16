package ua.kaj.recipe.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Setter
@Getter
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Ingredient implements Comparable<Ingredient> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private BigDecimal amount;

    @ManyToOne
    private Recipe recipe;

    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure uom;

    public Ingredient() {
    }

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom) {
        this.description = description;
        this.amount = amount;
        this.uom = uom;
    }

    public Ingredient(String description, BigDecimal amount, Recipe recipe, UnitOfMeasure uom) {
        this(description, amount, uom);
        this.recipe = recipe;
    }

    @Override
    public int compareTo(Ingredient ingredient) {
        return this.id.compareTo(ingredient.id);
    }
}
