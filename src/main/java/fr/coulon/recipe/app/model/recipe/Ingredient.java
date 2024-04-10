package fr.coulon.recipe.app.model.recipe;

import java.awt.image.BufferedImage;

public class Ingredient {

    private String name;
    private IngredientProperties ingredientProperties = new IngredientProperties();
    private BufferedImage ingredientImage;

    public Ingredient(String name) {
        this.name = name;
    }

    @Deprecated
    public Ingredient() {
        // For jackson only
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BufferedImage getIngredientImage() {
        return ingredientImage;
    }

    public void setIngredientImage(BufferedImage ingredientImage) {
        this.ingredientImage = ingredientImage;
    }

    public IngredientProperties getIngredientProperties() {
        return ingredientProperties;
    }

    public void setIngredientProperties(IngredientProperties ingredientProperties) {
        this.ingredientProperties = ingredientProperties;
    }
}