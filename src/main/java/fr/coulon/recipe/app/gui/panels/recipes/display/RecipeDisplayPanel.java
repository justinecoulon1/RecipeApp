package fr.coulon.recipe.app.gui.panels.recipes.display;

import fr.coulon.recipe.app.gui.panels.recipes.RecipeMainPanel;
import fr.coulon.recipe.app.gui.panels.recipes.display.ingredients.RecipeIngredientsDisplayPanel;
import fr.coulon.recipe.app.gui.panels.recipes.display.steps.RecipeStepsDisplayPanel;
import fr.coulon.recipe.app.gui.util.RecipeAppConstants;
import fr.coulon.recipe.app.model.recipe.Recipe;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class RecipeDisplayPanel extends JPanel {

    private final RecipeStepsDisplayPanel recipeStepsDisplayPanel;
    private final RecipeIngredientsDisplayPanel recipeIngredientsDisplayPanel;
    private final RecipeDisplayHeaderPanel recipeDisplayHeaderPanel;
    private RecipeMainPanel recipeMainPanel;

    public RecipeDisplayPanel(Recipe recipe, RecipeMainPanel recipeMainPanel, RecipeDisplayMode displayMode) {
        this.recipeMainPanel = recipeMainPanel;

        this.setBackground(RecipeAppConstants.BACKGROUND_COLOR);
        this.setLayout(new MigLayout("fill, nogrid, ins 0"));

        recipeDisplayHeaderPanel = new RecipeDisplayHeaderPanel(recipe, recipeMainPanel, this, displayMode);
        this.add(recipeDisplayHeaderPanel, "growx, h 70!, dock north, gapbottom 4, wrap");

        recipeIngredientsDisplayPanel = new RecipeIngredientsDisplayPanel(recipe, displayMode);
        this.add(recipeIngredientsDisplayPanel, "growy, w 500!");

        recipeStepsDisplayPanel = new RecipeStepsDisplayPanel(recipe, displayMode);
        this.add(recipeStepsDisplayPanel, "growy, growx");
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
}
