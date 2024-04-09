package fr.coulon.recipe.app.gui.panels.ingredients.cards.headers;

import fr.coulon.recipe.app.gui.panels.ingredients.IngredientsMainPanel;
import fr.coulon.recipe.app.gui.panels.ingredients.cards.IngredientsCardMode;
import fr.coulon.recipe.app.gui.panels.ingredients.cards.edit.IngredientEditCardPanel;
import fr.coulon.recipe.app.gui.util.RecipeAppConstants;
import fr.coulon.recipe.app.gui.util.ui.RecipeButtonUtils;
import fr.coulon.recipe.app.gui.util.ui.image.UiIcons;
import fr.coulon.recipe.app.model.managers.IngredientManager;
import fr.coulon.recipe.app.model.recipe.Ingredient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class IngredientEditCardHeaderPanel extends IngredientCardHeader {
    private final IngredientsMainPanel ingredientsMainPanel;
    private final JTextField ingredientNameTextField;
    private final Ingredient ingredient;
    private final IngredientEditCardPanel ingredientEditCardPanel;

    public IngredientEditCardHeaderPanel(Ingredient ingredient, IngredientsMainPanel ingredientsMainPanel, IngredientEditCardPanel ingredientEditCardPanel, IngredientsCardMode cardMode) {
        this.ingredient = ingredient;
        this.ingredientsMainPanel = ingredientsMainPanel;
        this.ingredientEditCardPanel = ingredientEditCardPanel;

        ingredientNameTextField = new JTextField();
        ingredientNameTextField.setText(ingredient.getName());
        ingredientNameTextField.setBackground(RecipeAppConstants.DARK_BACKGROUND_COLOR);
        ingredientNameTextField.setFont(RecipeAppConstants.SMALL_TITLE_FONT);
        ingredientNameTextField.setForeground(Color.white);
        ingredientNameTextField.setOpaque(true);
        this.add(ingredientNameTextField, "aligny center, gapbefore 10");

        if (cardMode == IngredientsCardMode.UPDATE || cardMode == IngredientsCardMode.CREATE) {
            JButton cancelEditButton = RecipeButtonUtils.createSmallButton(UiIcons.CANCEL);
            cancelEditButton.addActionListener(this::handleCancelEditIngredientCardButton);
            this.add(cancelEditButton, "aligny top, dock east, h 30!, w 30!, gaptop 10, gapafter 10");
        }

        if (cardMode == IngredientsCardMode.UPDATE) {
            JButton validateUpdateButton = RecipeButtonUtils.createSmallButton(UiIcons.VALIDATE);
            validateUpdateButton.addActionListener(this::handleValidateEditIngredientCardButton);
            this.add(validateUpdateButton, "aligny top, dock east, h 30!, w 30!, gaptop 10, gapafter 10");
        }

        if (cardMode == IngredientsCardMode.CREATE) {
            JButton validateCreateButton = RecipeButtonUtils.createSmallButton(UiIcons.VALIDATE);
            validateCreateButton.addActionListener(this::handleValidateCreateIngredientCardButton);
            this.add(validateCreateButton, "aligny top, dock east, h 30!, w 30!, gaptop 10, gapafter 10");
        }
    }
    public void handleCancelEditIngredientCardButton(ActionEvent e) {
        ingredientsMainPanel.closeIngredientPopup();
    }

    public void handleValidateEditIngredientCardButton(ActionEvent e) {
        IngredientManager.INSTANCE.updateIngredient(ingredient,
                ingredientNameTextField.getText(),
                ingredientEditCardPanel.getIngredientPropertiesPanel().getUpdatedIngredientProperties(),
                ingredientEditCardPanel.getIngredientEditCardImageInfoPanel().getIngredientImage());
        ingredientsMainPanel.closeIngredientPopup();
    }

    public void handleValidateCreateIngredientCardButton(ActionEvent e) {
        IngredientManager.INSTANCE.addIngredient(ingredient);
        handleValidateEditIngredientCardButton(e);
    }

    public void requestFocusTextField() {
        this.ingredientNameTextField.requestFocus();
    }
}
