package fr.coulon.recipe.app.gui.panels.ingredients.cards.edit;

import fr.coulon.recipe.app.gui.panels.ingredients.IngredientsMainPanel;
import fr.coulon.recipe.app.gui.panels.ingredients.cards.IngredientCardPanel;
import fr.coulon.recipe.app.gui.panels.ingredients.cards.IngredientsCardMode;
import fr.coulon.recipe.app.gui.util.RecipeAppConstants;
import fr.coulon.recipe.app.model.recipe.Ingredient;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class IngredientEditCardPanel extends JPanel{

    public IngredientEditCardPanel(IngredientsMainPanel ingredientsMainPanel, Ingredient ingredient, IngredientsCardMode cardMode) {
        this.setLayout(new MigLayout("fill, ins 5, nogrid"));
        this.setBackground(RecipeAppConstants.DARK_BACKGROUND_COLOR);
        this.setBorder(BorderFactory.createLineBorder(RecipeAppConstants.BORDERS_AND_SEPARATORS_WHITE_COLOR, 1));

        IngredientEditCardImageInfoPanel ingredientEditCardImageInfoPanel = new IngredientEditCardImageInfoPanel();
        this.add(ingredientEditCardImageInfoPanel, "w 40%!, growy, alignx left");

        IngredientCardPanel ingredientCardPanel = new IngredientCardPanel(ingredientsMainPanel, ingredient, cardMode);
        this.add(ingredientCardPanel, "growx, growy");
    }
}
