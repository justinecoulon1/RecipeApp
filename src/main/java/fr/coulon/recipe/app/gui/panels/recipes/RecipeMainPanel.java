package fr.coulon.recipe.app.gui.panels.recipes;

import fr.coulon.recipe.app.gui.panels.recipes.display.RecipeDisplayMode;
import fr.coulon.recipe.app.gui.panels.recipes.display.RecipeDisplayPanel;
import fr.coulon.recipe.app.gui.panels.recipes.list.RecipeListPanel;
import fr.coulon.recipe.app.gui.util.RecipeAppConstants;
import fr.coulon.recipe.app.model.managers.RecipeManager;
import fr.coulon.recipe.app.model.managers.RecipeManagerListener;
import fr.coulon.recipe.app.model.recipe.Recipe;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class RecipeMainPanel extends JPanel implements RecipeManagerListener {

    private RecipeDisplayPanel recipeDisplayPanel;
    private RecipeListPanel recipeListPanel;
    private final Map<Recipe, RecipeDisplayMode> modeByRecipe = new HashMap<>();

    public RecipeMainPanel() {
        RecipeManager.INSTANCE.addListener(this);
        this.setLayout(new MigLayout("fill, nogrid, ins 0"));
        this.setBackground(RecipeAppConstants.BACKGROUND_COLOR); //

        recipeListPanel = new RecipeListPanel();
        recipeListPanel.setBorder(BorderFactory.createLineBorder(RecipeAppConstants.BORDERS_AND_SEPARATORS_WHITE_COLOR, 2));
        this.add(recipeListPanel, "growy, w 250!");

        for (Recipe recipe : RecipeManager.INSTANCE.getAllRecipes()) {
            this.modeByRecipe.put(recipe, RecipeDisplayMode.READ);
            recipeListPanel.onRecipeAddition(recipe);
        }
    }

    @Override
    public void onRecipeAddition(Recipe addedRecipe) {
        modeByRecipe.put(addedRecipe, RecipeDisplayMode.CREATE);
    }

    @Override
    public void onRecipeDeletion(Recipe deletedRecipe) {
    }

    @Override
    public void onRecipeSelection(Recipe selectedRecipe) {
        if (selectedRecipe == null) {
            this.removeRecipeDisplayPanel();
        } else {
            this.createOrUpdateRecipeDisplayPanel(selectedRecipe);
        }
        if (super.getRootPane() != null) {
            super.getRootPane().updateUI();
        }
    }

    private void removeRecipeDisplayPanel() {
        if (recipeDisplayPanel != null) {
            this.remove(recipeDisplayPanel);
        }
        recipeDisplayPanel = null;
    }

    public void createOrUpdateRecipeDisplayPanel(Recipe selectedRecipe) {
        if (recipeDisplayPanel == null) {
            recipeDisplayPanel = new RecipeDisplayPanel(selectedRecipe, this.modeByRecipe.get(selectedRecipe));
            this.add(recipeDisplayPanel, "growx, growy");
            recipeDisplayPanel.setBorder(BorderFactory.createLineBorder(RecipeAppConstants.BORDERS_AND_SEPARATORS_WHITE_COLOR, 2));
        } else {
            recipeDisplayPanel.updatePanel(selectedRecipe, this.modeByRecipe.get(selectedRecipe));
        }
    }

    @Override
    public void onRecipeUpdate(Recipe updatedRecipe) {
        updateDisplayMode(updatedRecipe, RecipeDisplayMode.READ);
    }

    public void updateDisplayMode(Recipe recipe, RecipeDisplayMode displayMode) {
        this.modeByRecipe.put(recipe, displayMode);
        recipeDisplayPanel.updatePanel(recipe, displayMode);
    }
}
