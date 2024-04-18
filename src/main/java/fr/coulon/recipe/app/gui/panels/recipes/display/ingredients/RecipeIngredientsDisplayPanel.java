package fr.coulon.recipe.app.gui.panels.recipes.display.ingredients;

import fr.coulon.recipe.app.gui.panels.recipes.display.RecipeDisplayMode;
import fr.coulon.recipe.app.gui.util.ui.RecipeAppBorderUtils;
import fr.coulon.recipe.app.gui.util.RecipeAppConstants;
import fr.coulon.recipe.app.model.recipe.Ingredient;
import fr.coulon.recipe.app.model.recipe.Recipe;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecipeIngredientsDisplayPanel extends JPanel {

    private final JPanel recipeIngredientsContainerPanel;
    private final RecipeIngredientsDisplayHeaderPanel recipeIngredientsDisplayHeaderPanel;
    private final List<RecipeIngredientPanel> recipeIngredientsPanels = new ArrayList<>();

    public RecipeIngredientsDisplayPanel(Recipe recipe, RecipeDisplayMode displayMode) {

        this.setBackground(RecipeAppConstants.PANEL_BACKGROUND_COLOR);
        this.setLayout(new MigLayout("fill, ins 0"));

        recipeIngredientsDisplayHeaderPanel = new RecipeIngredientsDisplayHeaderPanel(this, displayMode);
        recipeIngredientsDisplayHeaderPanel.setBorder(RecipeAppBorderUtils.HEADER_DELIMITING_BORDER);
        this.add(recipeIngredientsDisplayHeaderPanel, "dock north, growx, h 60!, wrap");

        this.recipeIngredientsContainerPanel = new JPanel(new MigLayout("fillx, ins 0"));
        this.recipeIngredientsContainerPanel.setBackground(getBackground());

        JScrollPane scrollPane = new JScrollPane(this.recipeIngredientsContainerPanel);
        scrollPane.setHorizontalScrollBar(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        scrollPane.setBorder(null);
        this.add(scrollPane, "grow");

        this.addIngredientPanels(recipe.getAmountByIngredient(), displayMode);
    }

    private void addIngredientPanels(Map<Ingredient, String> amountByIngredient, RecipeDisplayMode displayMode) {
        if (displayMode !=RecipeDisplayMode.READ && amountByIngredient.isEmpty()) {
            this.addIngredientPanel(new Ingredient(""), "0", displayMode);
        } else {
            for (Map.Entry<Ingredient, String> entry : amountByIngredient.entrySet()) {
                Ingredient ingredient = entry.getKey();
                String amount = entry.getValue();
                this.addIngredientPanel(ingredient, amount, displayMode);
            }
        }
    }

    public void addIngredientPanel(Ingredient ingredient, String amount, RecipeDisplayMode displayMode) {
        RecipeIngredientPanel recipeIngredientPanel = new RecipeIngredientPanel(ingredient, amount, this, displayMode);
        recipeIngredientsContainerPanel.add(recipeIngredientPanel, "growx, h 80!, wrap");
        recipeIngredientsPanels.add(recipeIngredientPanel);
        if (super.getRootPane() != null) {
            super.getRootPane().updateUI();
        }
    }

    public void deleteIngredientPanel(Ingredient toDeleteIngredient) {
        RecipeIngredientPanel toDeleteRecipeIngredientPanel = null;
        for (RecipeIngredientPanel recipeIngredientPanel : recipeIngredientsPanels) {
            if (toDeleteIngredient == recipeIngredientPanel.getIngredient()) {
                toDeleteRecipeIngredientPanel = recipeIngredientPanel;
                break;
            }
        }
        if (toDeleteRecipeIngredientPanel != null) {
            recipeIngredientsPanels.remove(toDeleteRecipeIngredientPanel);
            recipeIngredientsContainerPanel.remove(toDeleteRecipeIngredientPanel);
            if (super.getRootPane() != null) {
                super.getRootPane().updateUI();
            }
        }
    }

    public Map<Ingredient, String> getUpdatedAmountByIngredient() {
        Map<Ingredient, String> amountByIngredient = new HashMap<>();
        for (RecipeIngredientPanel recipeIngredientPanel : recipeIngredientsPanels) {
            Ingredient ingredient = recipeIngredientPanel.getIngredient();
            ingredient.setName(recipeIngredientPanel.getIngredientName());
            amountByIngredient.put(ingredient, recipeIngredientPanel.getIngredientAmount());
        }
        return amountByIngredient;
    }

    public void updatePanel(Recipe recipe, RecipeDisplayMode displayMode) {
        recipeIngredientsDisplayHeaderPanel.updateDisplayMode(displayMode);
        recipeIngredientsPanels.clear();
        recipeIngredientsContainerPanel.removeAll();
        this.addIngredientPanels(recipe.getAmountByIngredient(), displayMode);
        if (super.getRootPane() != null) {
            super.getRootPane().updateUI();
        }
    }

    public void updateIngredientsDisplay() {
        for (RecipeIngredientPanel ingredientPanel : recipeIngredientsPanels) {
            ingredientPanel.updateIngredient();
        }
    }
}
