package fr.coulon.recipe.app.model.managers;

import fr.coulon.recipe.app.model.recipe.Ingredient;
import fr.coulon.recipe.app.model.recipe.Recipe;
import fr.coulon.recipe.app.model.recipe.Step;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RecipeManager {

    public static final RecipeManager INSTANCE = new RecipeManager();
    private final List<Recipe> recipes = new ArrayList<>();
    private final List<RecipeManagerListener> listeners = new ArrayList<>();
    private Recipe selectedRecipe;

    private RecipeManager() {
    }
    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
        for (RecipeManagerListener listener : listeners) {
            listener.onRecipeAddition(recipe);
        }
    }
    public void deleteRecipe(Recipe recipe) {
        recipes.remove(recipe);
        if (recipe == selectedRecipe) {
            if (recipes.size() == 0) {
                selectRecipe(null);
            } else {
                selectRecipe(recipes.get(0));
            }
        }
        for (RecipeManagerListener listener : listeners) {
            listener.onRecipeDeletion(recipe);
        }
    }
    public void selectRecipe(Recipe recipe) {
        selectedRecipe = recipe;
        for (RecipeManagerListener listener : listeners) {
            listener.onRecipeSelection(selectedRecipe);
        }
    }
    public void updateRecipe(Recipe toUpdateRecipe, String newRecipeName, List<Step> newSteps, Map<Ingredient, String> newAmountByIngredient) {
        toUpdateRecipe.setName(newRecipeName);
        toUpdateRecipe.getSteps().clear();
        toUpdateRecipe.getSteps().addAll(newSteps);
        toUpdateRecipe.getAmountByIngredient().clear();
        toUpdateRecipe.getAmountByIngredient().putAll(newAmountByIngredient);
        for (RecipeManagerListener listener : listeners) {
            listener.onRecipeUpdate(toUpdateRecipe);
        }
    }

    public void addListener(RecipeManagerListener recipeManagerListener) {
        listeners.add(recipeManagerListener);
    }
}
