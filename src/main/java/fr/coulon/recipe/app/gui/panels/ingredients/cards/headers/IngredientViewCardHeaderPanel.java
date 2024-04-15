package fr.coulon.recipe.app.gui.panels.ingredients.cards.headers;

import fr.coulon.recipe.app.gui.panels.ingredients.IngredientsMainPanel;
import fr.coulon.recipe.app.gui.panels.ingredients.cards.IngredientCardPanel;
import fr.coulon.recipe.app.gui.util.RecipeAppConstants;
import fr.coulon.recipe.app.gui.util.ui.RecipeButtonUtils;
import fr.coulon.recipe.app.gui.util.ui.image.ImageUtils;
import fr.coulon.recipe.app.gui.util.ui.image.UiIcons;
import fr.coulon.recipe.app.model.managers.IngredientManager;
import fr.coulon.recipe.app.model.recipe.Ingredient;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

public class IngredientViewCardHeaderPanel extends IngredientCardHeaderPanel {

    private static final BufferedImage UNKNOWN_IMAGE = ImageUtils.resizeImage(UiIcons.UNKNOWN.getImage(), 85, 85);

    private final IngredientsMainPanel ingredientsMainPanel;
    private final JTextArea ingredientNameTextArea;
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

        ingredientNameTextArea = new JTextArea();
        ingredientNameTextArea.setEditable(false);
        ingredientNameTextArea.setLineWrap(true);
        ingredientNameTextArea.setWrapStyleWord(true);
        ingredientNameTextArea.setText(ingredient.getName());
        ingredientNameTextArea.setBackground(getBackground());
        ingredientNameTextArea.setFont(RecipeAppConstants.SMALL_TITLE_FONT);
        ingredientNameTextArea.setForeground(Color.white);
        ingredientNameTextArea.setOpaque(true);
        this.add(ingredientNameTextArea, "gapbefore 10, growx, aligny center");

        JPanel buttonsPanel = new JPanel(new MigLayout("fill, ins 0, nogrid"));
        buttonsPanel.setBackground(this.getBackground());

        JButton deleteIngredientCardButton = RecipeButtonUtils.createSmallButton(UiIcons.DELETE);
        deleteIngredientCardButton.addActionListener(this::handleDeleteIngredientCardButton);
        buttonsPanel.add(deleteIngredientCardButton, "aligny top, alignx right, h 30!, w 30!, wrap");

        JButton editIngredientCardButton = RecipeButtonUtils.createSmallButton(UiIcons.EDIT);
        editIngredientCardButton.addActionListener(this::handleEditIngredientCardButton);
        buttonsPanel.add(editIngredientCardButton, "aligny top, alignx right, h 30!, w 30!");

        this.add(buttonsPanel, "alignx right, aligny top");

        updateIngredientImageLabel();
        updateIngredientNameTextArea();
    }

    public void handleDeleteIngredientCardButton(ActionEvent e) {
        IngredientManager.INSTANCE.deleteIngredient(ingredientCardPanel.getIngredient());
    }

    public void handleEditIngredientCardButton(ActionEvent e) {
        ingredientsMainPanel.openUpdateIngredientPopup(ingredient);
    }

    public void updateIngredientNameTextArea() {
        ingredientNameTextArea.setText(ingredient.getName());
    }

    public void updateIngredientImageLabel() {
        BufferedImage ingredientImage = UNKNOWN_IMAGE;
        if (ingredient.getIngredientImage() != null) {
            ingredientImage = ImageUtils.resizeImage(ingredient.getIngredientImage(), 100, 100);
        }
        ingredientImageLabel.setIcon(new ImageIcon(ingredientImage));
    }
}
