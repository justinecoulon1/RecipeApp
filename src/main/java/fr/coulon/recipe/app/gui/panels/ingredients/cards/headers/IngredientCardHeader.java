package fr.coulon.recipe.app.gui.panels.ingredients.cards.headers;

import fr.coulon.recipe.app.gui.util.RecipeAppConstants;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public abstract class IngredientCardHeader extends JPanel {

    public IngredientCardHeader() {
        this.setLayout(new MigLayout("fill, nogrid, ins 5 10 5 5"));
        this.setBackground(RecipeAppConstants.DARK_BACKGROUND_COLOR);
    }
}
