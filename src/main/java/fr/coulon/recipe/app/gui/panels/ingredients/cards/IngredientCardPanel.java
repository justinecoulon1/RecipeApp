package fr.coulon.recipe.app.gui.panels.ingredients.cards;

import fr.coulon.recipe.app.gui.panels.ingredients.IngredientsMainPanel;
import fr.coulon.recipe.app.gui.panels.ingredients.cards.headers.IngredientViewCardHeaderPanel;
import fr.coulon.recipe.app.gui.util.RecipeAppConstants;
import fr.coulon.recipe.app.model.recipe.Ingredient;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class IngredientCardPanel extends JPanel {

    private final IngredientsMainPanel ingredientsMainPanel;
    private Ingredient ingredient;
    private IngredientPropertiesPanel ingredientPropertiesPanel;
    private final IngredientViewCardHeaderPanel ingredientViewCardHeaderPanel;

    public IngredientCardPanel(IngredientsMainPanel ingredientsMainPanel, Ingredient ingredient, IngredientsCardMode cardMode) {
        this.ingredientsMainPanel = ingredientsMainPanel;
        this.ingredient = ingredient;

        this.setLayout(new MigLayout("fill, nogrid, ins 0"));
        this.setBackground(RecipeAppConstants.DARK_BACKGROUND_COLOR);
        this.setBorder(BorderFactory.createLineBorder(RecipeAppConstants.BORDERS_AND_SEPARATORS_WHITE_COLOR, 1));

        ingredientViewCardHeaderPanel = new IngredientViewCardHeaderPanel(ingredient, ingredientsMainPanel, this);
        ingredientViewCardHeaderPanel.setBackground(getBackground());
        this.add(ingredientViewCardHeaderPanel, "growx, h 120!,dock north, wrap");

        JSeparator separator = new JSeparator();
        separator.setForeground(RecipeAppConstants.BORDERS_AND_SEPARATORS_WHITE_COLOR);
        separator.setBackground(RecipeAppConstants.BORDERS_AND_SEPARATORS_WHITE_COLOR);
        this.add(separator, "w 75%!, dock north, alignx center");

        ingredientPropertiesPanel = new IngredientPropertiesPanel(ingredient.getIngredientProperties(), cardMode);
        this.add(ingredientPropertiesPanel,"grow");
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void updatePropertiesPanel() {
        ingredientPropertiesPanel.updateIngredientProperties(ingredient.getIngredientProperties());
    }

    public IngredientViewCardHeaderPanel getIngredientCardHeaderPanel() {
        return ingredientViewCardHeaderPanel;
    }
}
