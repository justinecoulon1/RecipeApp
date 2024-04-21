package fr.coulon.recipe.app.gui.panels.recipes.display.ingredients;

import fr.coulon.recipe.app.gui.panels.recipes.display.RecipeDisplayMode;
import fr.coulon.recipe.app.gui.util.RecipeAppConstants;
import fr.coulon.recipe.app.gui.util.ui.RecipeButtonUtils;
import fr.coulon.recipe.app.gui.util.ui.image.UiIcons;
import fr.coulon.recipe.app.model.recipe.Ingredient;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RecipeIngredientsDisplayHeaderPanel extends JPanel {
    private final RecipeIngredientsDisplayPanel recipeIngredientsDisplayPanel;
    private final JButton addIngredientButton;

    public RecipeIngredientsDisplayHeaderPanel(RecipeIngredientsDisplayPanel recipeIngredientsDisplayPanel, RecipeDisplayMode displayMode) {
        this.recipeIngredientsDisplayPanel = recipeIngredientsDisplayPanel;

        this.setBackground(RecipeAppConstants.DARK_BACKGROUND_COLOR);
        this.setLayout(new MigLayout("fillx, ins 0 20 0 15, nogrid, align center"));

        JLabel ingredientsTitleLabel = new JLabel();
        ingredientsTitleLabel.setText("Ingredients");
        ingredientsTitleLabel.setBackground(this.getBackground());
        ingredientsTitleLabel.setFont(RecipeAppConstants.SMALL_TITLE_FONT);
        ingredientsTitleLabel.setForeground(Color.white);
        ingredientsTitleLabel.setOpaque(true);
        this.add(ingredientsTitleLabel, "growx");

        addIngredientButton = RecipeButtonUtils.createSmallButton(UiIcons.PLUS);
        addIngredientButton.addActionListener(this::handleIngredientAdditionButton);
        addIngredientButton.setToolTipText("Add an ingredient");
        this.add(addIngredientButton, "h 30!, w 30!");

        updateDisplayMode(displayMode);
    }

    private void handleIngredientAdditionButton(ActionEvent e) {
        this.recipeIngredientsDisplayPanel.addIngredientPanel(new Ingredient(""), "0", RecipeDisplayMode.UPDATE);
    }

    public void updateDisplayMode(RecipeDisplayMode displayMode) {
        addIngredientButton.setVisible(displayMode == RecipeDisplayMode.CREATE || displayMode == RecipeDisplayMode.UPDATE);
    }
}
