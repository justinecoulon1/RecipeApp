package fr.coulon.recipe.app.gui.panels.ingredients;

import fr.coulon.recipe.app.gui.util.RecipeAppConstants;
import fr.coulon.recipe.app.gui.util.ui.RecipeButtonUtils;
import fr.coulon.recipe.app.gui.util.ui.image.UiIcons;
import fr.coulon.recipe.app.model.recipe.Ingredient;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class IngredientsHeaderPanel extends JPanel {

    private final IngredientsMainPanel ingredientsMainPanel;

    public IngredientsHeaderPanel(IngredientsMainPanel ingredientsMainPanel) {
        this.ingredientsMainPanel = ingredientsMainPanel;
        this.setLayout(new MigLayout("fill, nogrid, ins 0 20 0 15"));
        this.setBackground(RecipeAppConstants.DARK_BACKGROUND_COLOR);

        JLabel stepsTitleLabel = new JLabel();
        stepsTitleLabel.setText("Ingredients");
        stepsTitleLabel.setBackground(getBackground());
        stepsTitleLabel.setFont(RecipeAppConstants.TITLE_FONT);
        stepsTitleLabel.setForeground(Color.white);
        stepsTitleLabel.setOpaque(true);
        this.add(stepsTitleLabel, "grow");

        JButton addIngredientButton = RecipeButtonUtils.createSmallButton(UiIcons.PLUS);
        addIngredientButton.addActionListener(this::handleAddIngredientButton);
        addIngredientButton.setToolTipText("Create an ingredient");
        this.add(addIngredientButton, "h 45!, w 45!, dock east, gapafter 15");

    }

    public void handleAddIngredientButton(ActionEvent e) {
        ingredientsMainPanel.openCreateIngredientPopup(new Ingredient("New Ingredient"));
    }
}
