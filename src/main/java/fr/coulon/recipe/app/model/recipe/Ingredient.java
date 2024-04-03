package fr.coulon.recipe.app.model.recipe;

public class Ingredient {

    private String name;
    private IngredientProperties ingredientProperties = new IngredientProperties();

    public Ingredient(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IngredientProperties getIngredientProperties() {
        return ingredientProperties;
    }

    public void setIngredientProperties(IngredientProperties ingredientProperties) {
        this.ingredientProperties = ingredientProperties;
    }
}