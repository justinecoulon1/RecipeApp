package fr.coulon.recipe.app.model.managers;

import fr.coulon.recipe.app.model.recipe.Ingredient;

public interface IngredientManagerListener {

    void onIngredientAddition(Ingredient ingredient);
    void onIngredientDeletion(Ingredient ingredient);
    void onIngredientUpdate(Ingredient ingredient);

}
