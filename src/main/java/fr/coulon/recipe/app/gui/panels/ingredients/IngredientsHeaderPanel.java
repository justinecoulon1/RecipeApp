package fr.coulon.recipe.app.gui.panels.ingredients;

import fr.coulon.recipe.app.gui.util.ImageUtils;
import fr.coulon.recipe.app.gui.util.RecipeAppConstants;
import fr.coulon.recipe.app.gui.util.UiIcons;
import fr.coulon.recipe.app.model.recipe.Ingredient;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

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

        JButton addIngredientButton = new JButton();
        addIngredientButton.addActionListener(this::handleAddIngredientButton);
        BufferedImage plusImage = ImageUtils.resizeImage(UiIcons.PLUS.getImage(), 15, 15);
        addIngredientButton.setBackground(RecipeAppConstants.DECORATION_BACKGROUND_COLOR);
        addIngredientButton.setIcon(new ImageIcon(plusImage));
        addIngredientButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        addIngredientButton.setFocusable(false);
        this.add(addIngredientButton, "h 45!, w 45!, dock east, gapafter 15");
    }

    public void handleAddIngredientButton(ActionEvent e) {
        ingredientsMainPanel.openCreateIngredientPopup(new Ingredient("New Ingredient"));
    }
}
