package fr.coulon.recipe.app.gui.panels.ingredients.cards;

import fr.coulon.recipe.app.gui.panels.ingredients.IngredientsMainPanel;
import fr.coulon.recipe.app.gui.util.ImageUtils;
import fr.coulon.recipe.app.gui.util.RecipeAppConstants;
import fr.coulon.recipe.app.gui.util.RecipeButtonUtils;
import fr.coulon.recipe.app.gui.util.UiIcons;
import fr.coulon.recipe.app.model.managers.IngredientManager;
import fr.coulon.recipe.app.model.recipe.Ingredient;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

public class IngredientCardHeaderPanel extends JPanel {

    private final IngredientsMainPanel ingredientsMainPanel;
    private JTextField ingredientNameTextField;
    private JLabel ingredientNameLabel;
    private Ingredient ingredient;
    private final IngredientCardPanel ingredientCardPanel;
    private JLabel ingredientImageLabel;

    public IngredientCardHeaderPanel(Ingredient ingredient, IngredientsMainPanel ingredientsMainPanel, IngredientCardPanel ingredientCardPanel, IngredientsCardMode cardMode) {
        this.ingredientsMainPanel = ingredientsMainPanel;
        this.ingredientCardPanel = ingredientCardPanel;
        this.ingredient = ingredient;

        this.setLayout(new MigLayout("fill, nogrid, ins 5 10 5 5"));
        this.setBackground(getBackground());

        if (cardMode == IngredientsCardMode.READ) {
            ingredientImageLabel = new JLabel();
            BufferedImage unknownImage = ImageUtils.resizeImage(UiIcons.UNKNOWN.getImage(), 40, 40);
            ingredientImageLabel.setBackground(RecipeAppConstants.DECORATION_BACKGROUND_COLOR);
            ingredientImageLabel.setForeground(new Color(0x4E5052));
            ingredientImageLabel.setOpaque(true);
            ingredientImageLabel.setIcon(new ImageIcon(unknownImage));
            ingredientImageLabel.setHorizontalAlignment(JLabel.CENTER);
            ingredientImageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            this.add(ingredientImageLabel, "h 100!, w 100!, aligny center, alignx left");
        }

        if (cardMode == IngredientsCardMode.UPDATE || cardMode == IngredientsCardMode.CREATE) {
            ingredientNameTextField = new JTextField();
            ingredientNameTextField.setText(ingredient.getName());
            ingredientNameTextField.setBackground(RecipeAppConstants.DARK_BACKGROUND_COLOR);
            ingredientNameTextField.setFont(RecipeAppConstants.SMALL_TITLE_FONT);
            ingredientNameTextField.setForeground(Color.white);
            ingredientNameTextField.setOpaque(true);
            this.add(ingredientNameTextField, "gaptop 25, aligny top, gapbefore 10, gaptop 47");
        } else if (cardMode == IngredientsCardMode.READ) {
            ingredientNameLabel = new JLabel();
            ingredientNameLabel.setText(ingredient.getName());
            ingredientNameLabel.setBackground(RecipeAppConstants.DARK_BACKGROUND_COLOR);
            ingredientNameLabel.setFont(RecipeAppConstants.SMALL_TITLE_FONT);
            ingredientNameLabel.setForeground(Color.white);
            ingredientNameLabel.setOpaque(true);
            this.add(ingredientNameLabel, "gaptop 25, aligny top, gapbefore 10, gaptop 47");
        }

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

        if (cardMode == IngredientsCardMode.READ) {
            JButton deleteIngredientCardButton = RecipeButtonUtils.createSmallButton(UiIcons.DELETE);
            deleteIngredientCardButton.addActionListener(this::handleDeleteIngredientCardButton);
            this.add(deleteIngredientCardButton, "aligny top, dock east, h 30!, w 30!, gaptop 10, gapafter 10");

            JButton editIngredientCardButton = RecipeButtonUtils.createSmallButton(UiIcons.EDIT);
            editIngredientCardButton.addActionListener(this::handleEditIngredientCardButton);
            this.add(editIngredientCardButton, "aligny top, dock east, h 30!, w 30!, gaptop 10, gapafter 10");
        }
    }

    public void handleDeleteIngredientCardButton(ActionEvent e) {
        IngredientManager.INSTANCE.deleteIngredient(ingredientCardPanel.getIngredient());
    }

    public void handleEditIngredientCardButton(ActionEvent e) {
        ingredientsMainPanel.openUpdateIngredientPopup(ingredient);
    }

    public void handleCancelEditIngredientCardButton(ActionEvent e) {
        ingredientsMainPanel.closeIngredientPopup();
    }

    public void handleValidateEditIngredientCardButton(ActionEvent e) {
        IngredientManager.INSTANCE.updateIngredient(ingredient, ingredientNameTextField.getText(), ingredientCardPanel.getIngredientPropertiesPanel().getUpdatedIngredientProperties());
        ingredientsMainPanel.closeIngredientPopup();
    }

    public void handleValidateCreateIngredientCardButton(ActionEvent e) {
        IngredientManager.INSTANCE.addIngredient(ingredient);
        handleValidateEditIngredientCardButton(e);
    }

    public void updateIngredientNameLabel() {
        ingredientNameLabel.setText(ingredient.getName());
    }

}
