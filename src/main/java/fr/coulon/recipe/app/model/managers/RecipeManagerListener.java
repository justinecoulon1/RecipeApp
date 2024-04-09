package fr.coulon.recipe.app.model.managers;

import fr.coulon.recipe.app.model.recipe.Recipe;

public interface RecipeManagerListener {

    void onRecipeAddition(Recipe addedRecipe);
    void onRecipeDeletion(Recipe deletedRecipe);
    void onRecipeSelection(Recipe selectedRecipe);
    void onRecipeUpdate(Recipe updatedRecipe);


}
