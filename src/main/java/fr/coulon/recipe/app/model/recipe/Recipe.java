package fr.coulon.recipe.app.model.recipe;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fr.coulon.recipe.app.model.managers.util.IngredientDeserializer;
import fr.coulon.recipe.app.model.managers.util.IngredientSerializer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Recipe {

    private String name;
    @JsonSerialize(keyUsing = IngredientSerializer.class)
    @JsonDeserialize(keyUsing = IngredientDeserializer.class)
    private Map<Ingredient, String> amountByIngredient;
    private List<Step> steps;

    public Recipe(String name, Map<Ingredient, String> amountByIngredient, List<Step> steps) {
        this.name = name;
        this.amountByIngredient = amountByIngredient;
        this.steps = steps;
    }

    public Recipe(String name) {
        this.name = name;
        this.amountByIngredient = new HashMap<>();
        this.steps = new ArrayList<>();
    }

    @Deprecated
    public Recipe() {
        // For jackson only
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Ingredient, String> getAmountByIngredient() {
        return amountByIngredient;
    }

    public List<Step> getSteps() {
        return steps;
    }
}
