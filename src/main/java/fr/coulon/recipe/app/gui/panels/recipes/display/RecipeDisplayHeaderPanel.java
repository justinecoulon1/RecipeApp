package fr.coulon.recipe.app.gui.panels.recipes.display;

import fr.coulon.recipe.app.gui.panels.recipes.RecipeMainPanel;
import fr.coulon.recipe.app.gui.util.RecipeAppConstants;
import fr.coulon.recipe.app.gui.util.ui.RecipeButtonUtils;
import fr.coulon.recipe.app.gui.util.ui.image.UiIcons;
import fr.coulon.recipe.app.model.managers.RecipeManager;
import fr.coulon.recipe.app.model.recipe.Ingredient;
import fr.coulon.recipe.app.model.recipe.Recipe;
import fr.coulon.recipe.app.model.recipe.Step;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Map;

public class RecipeDisplayHeaderPanel extends JPanel {
    private Recipe recipe;
    private final JTextField recipeNameTextField;
    private final RecipeDisplayPanel recipeDisplayPanel;
    private final RecipeMainPanel recipeMainPanel;
    private final JButton cancelModificationButton;
    private final JButton validateModificationButton;
    private final JButton editRecipeButton;

    public RecipeDisplayHeaderPanel(Recipe recipe, RecipeMainPanel recipeMainPanel, RecipeDisplayPanel recipeDisplayPanel, RecipeDisplayMode displayMode) {
        this.recipe = recipe;
        this.recipeMainPanel = recipeMainPanel;
        this.recipeDisplayPanel = recipeDisplayPanel;

        this.setLayout(new MigLayout("fill, nogrid, ins 15, align center"));
        this.setBackground(RecipeAppConstants.DARK_BACKGROUND_COLOR);

        recipeNameTextField = new JTextField();
        recipeNameTextField.setText(recipe.getName());
        recipeNameTextField.setBackground(this.getBackground());
        recipeNameTextField.setFont(RecipeAppConstants.TITLE_FONT);
        recipeNameTextField.setForeground(Color.white);
        recipeNameTextField.setHorizontalAlignment(JTextField.LEFT);
        recipeNameTextField.setOpaque(true);
        this.add(recipeNameTextField, "growx");

        cancelModificationButton = RecipeButtonUtils.createBigButton(UiIcons.CANCEL);
        cancelModificationButton.addActionListener(this::handleCancelModificationButton);
        cancelModificationButton.setToolTipText("Cancel modifications");

        validateModificationButton = RecipeButtonUtils.createBigButton(UiIcons.VALIDATE);
        validateModificationButton.addActionListener(this::handleValidateModificationButton);
        validateModificationButton.setToolTipText("Save modifications");

        editRecipeButton = RecipeButtonUtils.createBigButton(UiIcons.EDIT);
        editRecipeButton.addActionListener(this::handleEditButton);
        editRecipeButton.setToolTipText("Edit recipe");

        updateDisplayMode(displayMode);
    }

    private void handleValidateModificationButton(ActionEvent e) {
        List<Step> updatedSteps = recipeDisplayPanel.getRecipeStepsDisplayPanel().getUpdatedSteps();
        Map<Ingredient, String> updatedAmountByIngredient = recipeDisplayPanel.getRecipeIngredientsDisplayPanel().getUpdatedAmountByIngredient();
        RecipeManager.INSTANCE.updateRecipe(recipe, recipeNameTextField.getText(), updatedSteps, updatedAmountByIngredient);
    }

    private void handleCancelModificationButton(ActionEvent e) {
        recipeMainPanel.updateDisplayMode(recipe, RecipeDisplayMode.READ);
        recipeMainPanel.createOrUpdateRecipeDisplayPanel(recipe);
    }

    private void handleEditButton(ActionEvent e) {
        recipeMainPanel.updateDisplayMode(recipe, RecipeDisplayMode.UPDATE);
    }

    public void updatePanel(Recipe recipe, RecipeDisplayMode displayMode) {
        this.updateDisplayMode(displayMode);
        recipeNameTextField.setText(recipe.getName());
        this.recipe = recipe;
    }

    public void updateDisplayMode(RecipeDisplayMode displayMode) {
        if (displayMode == RecipeDisplayMode.UPDATE || displayMode == RecipeDisplayMode.CREATE) {
            recipeNameTextField.setEditable(true);
            recipeNameTextField.setCaretColor(Color.WHITE);
            recipeNameTextField.setFocusable(true);
            recipeNameTextField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(RecipeAppConstants.BORDERS_AND_SEPARATORS_WHITE_COLOR), BorderFactory.createEmptyBorder(0, 5, 0, 5)));

            this.remove(editRecipeButton);
            this.add(cancelModificationButton, "h 45!, w 45!, dock east, gapafter 10");
            this.add(validateModificationButton, "h 45!, w 45!, dock east, gapafter 10");

        } else if (displayMode == RecipeDisplayMode.READ) {
            recipeNameTextField.setBorder(null);
            recipeNameTextField.setFocusable(false);
            recipeNameTextField.setEditable(false);

            this.remove(cancelModificationButton);
            this.remove(validateModificationButton);
            this.add(editRecipeButton, "h 45!, w 45!, dock east, gapafter 10");
        }
    }
}

