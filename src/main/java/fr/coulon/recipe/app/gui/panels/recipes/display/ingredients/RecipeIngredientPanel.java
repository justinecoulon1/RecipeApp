package fr.coulon.recipe.app.gui.panels.recipes.display.ingredients;

import fr.coulon.recipe.app.gui.panels.recipes.display.RecipeDisplayMode;
import fr.coulon.recipe.app.gui.util.*;
import fr.coulon.recipe.app.model.recipe.Ingredient;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

public class RecipeIngredientPanel extends JPanel {

    private JButton deleteIngredientButton;
    private final RecipeIngredientsDisplayPanel recipeIngredientsDisplayPanel;
    private final Ingredient ingredient;

    private JTextField ingredientNameTextField;
    private JTextField ingredientAmountTextField;

    public RecipeIngredientPanel(Ingredient ingredient, String amount, RecipeIngredientsDisplayPanel recipeIngredientsDisplayPanel, RecipeDisplayMode displayMode) {
        this.ingredient = ingredient;
        this.recipeIngredientsDisplayPanel = recipeIngredientsDisplayPanel;

        this.setBackground(RecipeAppConstants.DARK_BACKGROUND_COLOR);
        this.setLayout(new MigLayout("fill, nogrid, ins 15"));

        JLabel ingredientImageLabel = new JLabel();
        BufferedImage unknownImage = ImageUtils.resizeImage(UiIcons.UNKNOWN.getImage(), 40, 40);
        ingredientImageLabel.setBackground(RecipeAppConstants.DECORATION_BACKGROUND_COLOR);
        ingredientImageLabel.setForeground(new Color(0x4E5052));
        ingredientImageLabel.setOpaque(true);
        ingredientImageLabel.setIcon(new ImageIcon(unknownImage));
        ingredientImageLabel.setHorizontalAlignment(JLabel.CENTER);
        ingredientImageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        this.add(ingredientImageLabel, "h 50!, w 50!");

        JPanel ingredientInfoContainer = new JPanel();
        ingredientInfoContainer.setBackground(getBackground());
        ingredientInfoContainer.setLayout(new MigLayout("fill, nogrid, ins 5"));
        this.add(ingredientInfoContainer, "growx");

        JLabel nameLabel = new JLabel();
        nameLabel.setText("Name: ");
        nameLabel.setBackground(this.getBackground());
        nameLabel.setFont(RecipeAppConstants.DEFAULT_FONT);
        nameLabel.setForeground(Color.white);
        nameLabel.setOpaque(true);
        ingredientInfoContainer.add(nameLabel, "w 60!, gapafter 10");

        ingredientNameTextField = new JTextField();
        ingredientNameTextField.setText(ingredient.getName());
        ingredientNameTextField.setCaretColor(Color.WHITE);
        ingredientNameTextField.setBackground(this.getBackground());
        ingredientNameTextField.setFont(RecipeAppConstants.DEFAULT_FONT);
        ingredientNameTextField.setForeground(Color.white);
        ingredientNameTextField.setHorizontalAlignment(JTextField.LEFT);
        ingredientNameTextField.setOpaque(true);
        ingredientNameTextField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.gray), BorderFactory.createEmptyBorder(0, 5, 0, 5)));
        ingredientInfoContainer.add(ingredientNameTextField, "growx, wrap");

        JLabel amountLabel = new JLabel();
        amountLabel.setText("Amount: ");
        amountLabel.setBackground(this.getBackground());
        amountLabel.setFont(RecipeAppConstants.DEFAULT_FONT);
        amountLabel.setForeground(Color.white);
        amountLabel.setOpaque(true);
        ingredientInfoContainer.add(amountLabel, "w 60!, gapafter 10");

        ingredientAmountTextField = new JTextField();
        ingredientAmountTextField.setText(amount);
        ingredientAmountTextField.setCaretColor(Color.WHITE);
        ingredientAmountTextField.setBackground(this.getBackground());
        ingredientAmountTextField.setFont(RecipeAppConstants.DEFAULT_FONT);
        ingredientAmountTextField.setForeground(Color.white);
        ingredientAmountTextField.setHorizontalAlignment(JTextField.LEFT);
        ingredientAmountTextField.setOpaque(true);
        ingredientAmountTextField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.gray), BorderFactory.createEmptyBorder(0, 5, 0, 5)));
        ingredientInfoContainer.add(ingredientAmountTextField, "growx");

        deleteIngredientButton = RecipeButtonUtils.createSmallButton(UiIcons.DELETE);
        deleteIngredientButton.addActionListener(this::handleDeleteIngredientButton);
        this.add(deleteIngredientButton, "aligny top, alignx right, h 30!, w 30!, gapbefore 10");

        updateDisplayMode(displayMode);
    }

    private void handleDeleteIngredientButton(ActionEvent e) {
        this.recipeIngredientsDisplayPanel.deleteIngredientPanel(this.getIngredient());
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public String getIngredientAmount() {
        return ingredientAmountTextField.getText();
    }

    public String getIngredientName() {
        return ingredientNameTextField.getText();
    }

    public void updateDisplayMode(RecipeDisplayMode displayMode) {
        if (displayMode == RecipeDisplayMode.UPDATE || displayMode == RecipeDisplayMode.CREATE) {
            ingredientNameTextField.setEditable(true);
            ingredientNameTextField.setFocusable(true);
            ingredientNameTextField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.gray), BorderFactory.createEmptyBorder(0, 5, 0, 5)));
            ingredientNameTextField.setCaretColor(Color.white);

            ingredientAmountTextField.setEditable(true);
            ingredientAmountTextField.setFocusable(true);
            ingredientAmountTextField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.gray), BorderFactory.createEmptyBorder(0, 5, 0, 5)));
            ingredientAmountTextField.setCaretColor(Color.white);

            deleteIngredientButton.setVisible(true);
        } else if (displayMode == RecipeDisplayMode.READ) {
            ingredientNameTextField.setEditable(false);
            ingredientNameTextField.setBorder(null);
            ingredientNameTextField.setFocusable(false);

            ingredientAmountTextField.setEditable(false);
            ingredientAmountTextField.setBorder(null);
            ingredientAmountTextField.setFocusable(false);

            deleteIngredientButton.setVisible(false);
        }
    }
}
