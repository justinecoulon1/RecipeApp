package fr.coulon.recipe.app.gui.panels.ingredients.cards.headers;

import fr.coulon.recipe.app.gui.panels.ingredients.IngredientsMainPanel;
import fr.coulon.recipe.app.gui.panels.ingredients.cards.IngredientCardPanel;
import fr.coulon.recipe.app.gui.util.RecipeAppConstants;
import fr.coulon.recipe.app.gui.util.ui.RecipeButtonUtils;
import fr.coulon.recipe.app.gui.util.ui.image.ImageUtils;
import fr.coulon.recipe.app.gui.util.ui.image.UiIcons;
import fr.coulon.recipe.app.model.managers.IngredientManager;
import fr.coulon.recipe.app.model.recipe.Ingredient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

public class IngredientViewCardHeaderPanel extends IngredientCardHeaderPanel {

    private final IngredientsMainPanel ingredientsMainPanel;
    private final JLabel ingredientNameLabel;
    private final Ingredient ingredient;
    private final IngredientCardPanel ingredientCardPanel;
    private final JLabel ingredientImageLabel;

    public IngredientViewCardHeaderPanel(Ingredient ingredient, IngredientsMainPanel ingredientsMainPanel, IngredientCardPanel ingredientCardPanel) {
        this.ingredientsMainPanel = ingredientsMainPanel;
        this.ingredientCardPanel = ingredientCardPanel;
        this.ingredient = ingredient;

        ingredientImageLabel = new JLabel();
        BufferedImage unknownImage = ImageUtils.resizeImage(UiIcons.UNKNOWN.getImage(), 40, 40);
        ingredientImageLabel.setBackground(RecipeAppConstants.DECORATION_BACKGROUND_COLOR);
        ingredientImageLabel.setForeground(new Color(0x4E5052));
        ingredientImageLabel.setOpaque(true);
        ingredientImageLabel.setIcon(new ImageIcon(unknownImage));
        ingredientImageLabel.setHorizontalAlignment(JLabel.CENTER);
        ingredientImageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        this.add(ingredientImageLabel, "h 100!, w 100!, aligny center, alignx left");

        ingredientNameLabel = new JLabel();
        ingredientNameLabel.setText(ingredient.getName());
        ingredientNameLabel.setBackground(RecipeAppConstants.DARK_BACKGROUND_COLOR);
        ingredientNameLabel.setFont(RecipeAppConstants.SMALL_TITLE_FONT);
        ingredientNameLabel.setForeground(Color.white);
        ingredientNameLabel.setOpaque(true);
        this.add(ingredientNameLabel, "gaptop 25, aligny top, gapbefore 10, gaptop 47");

        JButton deleteIngredientCardButton = RecipeButtonUtils.createSmallButton(UiIcons.DELETE);
        deleteIngredientCardButton.addActionListener(this::handleDeleteIngredientCardButton);
        this.add(deleteIngredientCardButton, "aligny top, dock east, h 30!, w 30!, gaptop 10, gapafter 10");

        JButton editIngredientCardButton = RecipeButtonUtils.createSmallButton(UiIcons.EDIT);
        editIngredientCardButton.addActionListener(this::handleEditIngredientCardButton);
        this.add(editIngredientCardButton, "aligny top, dock east, h 30!, w 30!, gaptop 10, gapafter 10");

        updateIngredientImageLabel();
        updateIngredientNameLabel();
    }

    public void handleDeleteIngredientCardButton(ActionEvent e) {
        IngredientManager.INSTANCE.deleteIngredient(ingredientCardPanel.getIngredient());
    }

    public void handleEditIngredientCardButton(ActionEvent e) {
        ingredientsMainPanel.openUpdateIngredientPopup(ingredient);
    }

    public void updateIngredientNameLabel() {
        ingredientNameLabel.setText(ingredient.getName());
    }

    public void updateIngredientImageLabel() {
        if (ingredient.getIngredientImage() == null) {
            ingredientImageLabel.setIcon(new ImageIcon(ImageUtils.resizeImage(UiIcons.UNKNOWN.getImage(), 100, 100)));
        } else {
            ingredientImageLabel.setIcon(new ImageIcon(ImageUtils.resizeImage(ingredient.getIngredientImage(), 100, 100)));
        }
    }
}
