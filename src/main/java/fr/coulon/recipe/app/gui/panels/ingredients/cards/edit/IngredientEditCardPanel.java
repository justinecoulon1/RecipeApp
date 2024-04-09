package fr.coulon.recipe.app.gui.panels.ingredients.cards.edit;

import fr.coulon.recipe.app.gui.panels.ingredients.IngredientsMainPanel;
import fr.coulon.recipe.app.gui.panels.ingredients.cards.IngredientPropertiesPanel;
import fr.coulon.recipe.app.gui.panels.ingredients.cards.IngredientsCardMode;
import fr.coulon.recipe.app.gui.panels.ingredients.cards.headers.IngredientEditCardHeaderPanel;
import fr.coulon.recipe.app.gui.util.RecipeAppConstants;
import fr.coulon.recipe.app.model.recipe.Ingredient;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class IngredientEditCardPanel extends JPanel{

    private final IngredientEditCardImageInfoPanel ingredientEditCardImageInfoPanel;
    private final IngredientEditCardHeaderPanel ingredientEditCardHeaderPanel;
    private final IngredientPropertiesPanel ingredientPropertiesPanel;

    public IngredientEditCardPanel(Ingredient ingredient, IngredientsMainPanel ingredientsMainPanel, IngredientsCardMode cardMode) {
        this.setLayout(new MigLayout("fill, ins 5, nogrid"));
        this.setBackground(RecipeAppConstants.DARK_BACKGROUND_COLOR);
        this.setBorder(BorderFactory.createLineBorder(RecipeAppConstants.BORDERS_AND_SEPARATORS_WHITE_COLOR, 1));

        ingredientEditCardImageInfoPanel = new IngredientEditCardImageInfoPanel(ingredient);
        this.add(ingredientEditCardImageInfoPanel, "w 40%!, growy, alignx left");


        JPanel ingredientPropertiesAndHeaderContainerPanel = new JPanel(new MigLayout("fill, nogrid, ins 0"));
        ingredientPropertiesAndHeaderContainerPanel.setBackground(RecipeAppConstants.DARK_BACKGROUND_COLOR);
        ingredientPropertiesAndHeaderContainerPanel.setBorder(BorderFactory.createLineBorder(RecipeAppConstants.BORDERS_AND_SEPARATORS_WHITE_COLOR, 1));

        ingredientEditCardHeaderPanel = new IngredientEditCardHeaderPanel(ingredient, ingredientsMainPanel, this, cardMode);
        ingredientPropertiesAndHeaderContainerPanel.add(ingredientEditCardHeaderPanel, "growx, h 120!, wrap");

        JSeparator separator = new JSeparator();
        separator.setForeground(RecipeAppConstants.BORDERS_AND_SEPARATORS_WHITE_COLOR);
        separator.setBackground(RecipeAppConstants.BORDERS_AND_SEPARATORS_WHITE_COLOR);
        ingredientPropertiesAndHeaderContainerPanel.add(separator, "w 75%!, alignx center, wrap");

        ingredientPropertiesPanel = new IngredientPropertiesPanel(ingredient.getIngredientProperties(), cardMode);
        ingredientPropertiesAndHeaderContainerPanel.add(ingredientPropertiesPanel, "grow, pushy");

        this.add(ingredientPropertiesAndHeaderContainerPanel, "growx, growy");
    }

    public IngredientEditCardImageInfoPanel getIngredientEditCardImageInfoPanel() {
        return ingredientEditCardImageInfoPanel;
    }

    public IngredientPropertiesPanel getIngredientPropertiesPanel() {
        return ingredientPropertiesPanel;
    }

    public IngredientEditCardHeaderPanel getIngredientEditCardHeaderPanel() {
        return ingredientEditCardHeaderPanel;
    }
}
