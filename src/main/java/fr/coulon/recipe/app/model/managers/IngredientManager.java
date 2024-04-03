package fr.coulon.recipe.app.model.managers;

import fr.coulon.recipe.app.model.recipe.Ingredient;
import fr.coulon.recipe.app.model.recipe.IngredientProperties;

import java.util.ArrayList;
import java.util.List;

public class IngredientManager {

    public static final IngredientManager INSTANCE = new IngredientManager();
    private final List<Ingredient> ingredients = new ArrayList<>();
    private final List<IngredientManagerListener> ingredientListeners = new ArrayList<>();

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
        for (IngredientManagerListener ingredientListener : ingredientListeners) {
            ingredientListener.onIngredientAddition(ingredient);
        }
    }
    public void deleteIngredient(Ingredient ingredient) {
        ingredients.remove(ingredient);
        for (IngredientManagerListener ingredientListener : ingredientListeners) {
            ingredientListener.onIngredientDeletion(ingredient);
        }
    }
    public void updateIngredient(Ingredient ingredient, String newIngredientName, IngredientProperties ingredientProperties) {
        ingredient.setName(newIngredientName);
        ingredient.setIngredientProperties(ingredientProperties);
        for (IngredientManagerListener ingredientListener : ingredientListeners) {
            ingredientListener.onIngredientUpdate(ingredient);
        }
    }

    public void addListener(IngredientManagerListener ingredientManagerListener) {
        ingredientListeners.add(ingredientManagerListener);
    }
}
