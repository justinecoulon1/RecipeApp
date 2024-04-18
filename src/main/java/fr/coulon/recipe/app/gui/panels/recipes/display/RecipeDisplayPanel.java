package fr.coulon.recipe.app.gui.panels.recipes.display;

import fr.coulon.recipe.app.gui.panels.AppPanels;
import fr.coulon.recipe.app.gui.panels.recipes.display.ingredients.RecipeIngredientsDisplayPanel;
import fr.coulon.recipe.app.gui.panels.recipes.display.steps.RecipeStepsDisplayPanel;
import fr.coulon.recipe.app.gui.util.RecipeAppConstants;
import fr.coulon.recipe.app.model.managers.IngredientManager;
import fr.coulon.recipe.app.model.managers.IngredientManagerListener;
import fr.coulon.recipe.app.model.recipe.Ingredient;
import fr.coulon.recipe.app.model.recipe.Recipe;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class RecipeDisplayPanel extends JPanel implements IngredientManagerListener {

    private final RecipeStepsDisplayPanel recipeStepsDisplayPanel;
    private final RecipeIngredientsDisplayPanel recipeIngredientsDisplayPanel;
    private final RecipeDisplayHeaderPanel recipeDisplayHeaderPanel;

    public RecipeDisplayPanel(Recipe recipe, RecipeDisplayMode displayMode) {
        this.setBackground(RecipeAppConstants.BACKGROUND_COLOR);
        this.setLayout(new MigLayout("fill, nogrid, ins 0"));

        recipeDisplayHeaderPanel = new RecipeDisplayHeaderPanel(recipe, AppPanels.RECIPE_MAIN_PANEL, this, displayMode);
        this.add(recipeDisplayHeaderPanel, "growx, h 70!, dock north, gapbottom 4, wrap");

        recipeIngredientsDisplayPanel = new RecipeIngredientsDisplayPanel(recipe, displayMode);
        this.add(recipeIngredientsDisplayPanel, "growy, w 500!");

        recipeStepsDisplayPanel = new RecipeStepsDisplayPanel(recipe, displayMode);
        this.add(recipeStepsDisplayPanel, "growy, growx");

        IngredientManager.INSTANCE.addListener(this);
    }

    public RecipeStepsDisplayPanel getRecipeStepsDisplayPanel() {
        return recipeStepsDisplayPanel;
    }

    public RecipeIngredientsDisplayPanel getRecipeIngredientsDisplayPanel() {
        return recipeIngredientsDisplayPanel;
    }

    public void updatePanel(Recipe recipe, RecipeDisplayMode displayMode) {
        recipeIngredientsDisplayPanel.updatePanel(recipe, displayMode);
        recipeStepsDisplayPanel.updatePanel(recipe, displayMode);
        recipeDisplayHeaderPanel.updatePanel(recipe, displayMode);
        if (super.getRootPane() != null) {
            super.getRootPane().updateUI();
        }
    }

    @Override
    public void onIngredientAddition(Ingredient ingredient) {
        recipeIngredientsDisplayPanel.updateIngredientsDisplay();
    }

    @Override
    public void onIngredientDeletion(Ingredient ingredient) {
        recipeIngredientsDisplayPanel.updateIngredientsDisplay();
    }

    @Override
    public void onIngredientUpdate(Ingredient ingredient) {
        recipeIngredientsDisplayPanel.updateIngredientsDisplay();
    }
}
