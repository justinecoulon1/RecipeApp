package fr.coulon.recipe.app.gui.panels.recipes.display.ingredients;

import fr.coulon.recipe.app.gui.panels.AppPanels;
import fr.coulon.recipe.app.gui.panels.recipes.display.RecipeDisplayMode;
import fr.coulon.recipe.app.gui.util.RecipeAppConstants;
import fr.coulon.recipe.app.gui.util.ui.RecipeButtonUtils;
import fr.coulon.recipe.app.gui.util.ui.customtextfield.SearchableTextField;
import fr.coulon.recipe.app.gui.util.ui.customtextfield.SearchableTextFieldItemsGetter;
import fr.coulon.recipe.app.gui.util.ui.image.ImageUtils;
import fr.coulon.recipe.app.gui.util.ui.image.UiIcons;
import fr.coulon.recipe.app.model.managers.IngredientManager;
import fr.coulon.recipe.app.model.recipe.Ingredient;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class RecipeIngredientPanel extends JPanel {

    private static final BufferedImage UNKNOWN_IMAGE = ImageUtils.resizeImage(UiIcons.UNKNOWN.getImage(), 40, 40);
    private final JButton deleteIngredientButton;
    private final JButton addIngredientButton;
    private final RecipeIngredientsDisplayPanel recipeIngredientsDisplayPanel;
    private Ingredient ingredient;
    private final SearchableTextField ingredientNameTextField;
    private final JTextField ingredientAmountTextField;
    private final JLabel ingredientImageLabel;
    private boolean isIngredientAlreadyStored;

    public RecipeIngredientPanel(Ingredient ingredient, String amount, RecipeIngredientsDisplayPanel recipeIngredientsDisplayPanel, RecipeDisplayMode displayMode) {
        this.ingredient = ingredient;
        this.recipeIngredientsDisplayPanel = recipeIngredientsDisplayPanel;

        this.setBackground(RecipeAppConstants.DARK_BACKGROUND_COLOR);
        this.setLayout(new MigLayout("fill, nogrid, ins 0"));

        ingredientImageLabel = new JLabel();
        ingredientImageLabel.setBackground(RecipeAppConstants.DECORATION_BACKGROUND_COLOR);
        ingredientImageLabel.setForeground(new Color(0x4E5052));
        ingredientImageLabel.setOpaque(true);
        ingredientImageLabel.setHorizontalAlignment(JLabel.CENTER);
        ingredientImageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        this.add(ingredientImageLabel, "h 50!, w 50!, gapbefore 15");

        JPanel ingredientInfoContainer = new JPanel(new MigLayout("fill, nogrid, ins 5"));
        ingredientInfoContainer.setBackground(getBackground());

        JLabel nameLabel = new JLabel();
        nameLabel.setText("Name: ");
        nameLabel.setBackground(this.getBackground());
        nameLabel.setFont(RecipeAppConstants.DEFAULT_FONT);
        nameLabel.setForeground(Color.white);
        nameLabel.setOpaque(true);
        ingredientInfoContainer.add(nameLabel, "w 60!, gapafter 10");

        SearchableTextFieldItemsGetter itemsGetter = () -> IngredientManager.INSTANCE.getIngredientsNames().toArray(String[]::new);
        ingredientNameTextField = new SearchableTextField(itemsGetter, ingredient.getName());
        ingredientNameTextField.getTextField().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                onIngredientNameChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                onIngredientNameChange();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                onIngredientNameChange();
            }
        });
        ingredientNameTextField.setBackground(RecipeAppConstants.DARK_BACKGROUND_COLOR);
        ingredientNameTextField.setFont(RecipeAppConstants.DEFAULT_FONT);
        ingredientNameTextField.getTextField().setFont(RecipeAppConstants.DEFAULT_FONT);
        ingredientNameTextField.getTextField().setForeground(Color.white);
        ingredientInfoContainer.add(ingredientNameTextField, "growx, h 25!, wrap");

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
        ingredientInfoContainer.add(ingredientAmountTextField, "growx, h 25!");

        this.add(ingredientInfoContainer, "growx");

        JPanel buttonPanel = new JPanel(new MigLayout("fill, nogrid, ins 5"));
        buttonPanel.setBackground(getBackground());

        deleteIngredientButton = RecipeButtonUtils.createSmallButton(UiIcons.DELETE);
        deleteIngredientButton.addActionListener(this::handleDeleteIngredientButton);
        buttonPanel.add(deleteIngredientButton, "aligny top, alignx right, h 30!, w 30!, gapbefore 10, gapafter 15, wrap");

        addIngredientButton = RecipeButtonUtils.createSmallButton(UiIcons.PLUS);
        addIngredientButton.addActionListener(this::handleAddIngredientButton);
        buttonPanel.add(addIngredientButton, "aligny bottom, alignx right, h 30!, w 30!, gapbefore 10, gapafter 15");

        this.add(buttonPanel, "alignx right");

        onIngredientNameChange();
        updateDisplayMode(displayMode);
    }

    public void updateIngredient() {
        this.ingredientNameTextField.setText(ingredient.getName());
        onIngredientNameChange();
    }

    private void onIngredientNameChange() {
        String ingredientName = ingredientNameTextField.getText();
        Ingredient newIngredient = IngredientManager.INSTANCE.getIngredientByName(ingredientName);
        isIngredientAlreadyStored = newIngredient != null;
        this.ingredient = Objects.requireNonNullElseGet(newIngredient, () -> new Ingredient(ingredientName));
        updateIngredientDisplay();
    }

    private void handleDeleteIngredientButton(ActionEvent e) {
        this.recipeIngredientsDisplayPanel.deleteIngredientPanel(this.getIngredient());
    }

    private void handleAddIngredientButton(ActionEvent e) {
        AppPanels.INGREDIENTS_MAIN_PANEL.openCreateIngredientPopup(ingredient);
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
            ingredientNameTextField.getTextField().setEditable(true);
            ingredientNameTextField.setFocusable(true);
            ingredientNameTextField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.gray), BorderFactory.createEmptyBorder(0, 5, 0, 5)));

            ingredientAmountTextField.setEditable(true);
            ingredientAmountTextField.setFocusable(true);
            ingredientAmountTextField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.gray), BorderFactory.createEmptyBorder(0, 5, 0, 5)));

            deleteIngredientButton.setVisible(true);
            addIngredientButton.setVisible(!isIngredientAlreadyStored);
        } else if (displayMode == RecipeDisplayMode.READ) {
            ingredientNameTextField.getTextField().setEditable(false);
            ingredientNameTextField.setFocusable(false);
            ingredientNameTextField.setBorder(null);

            ingredientAmountTextField.setEditable(false);
            ingredientAmountTextField.setFocusable(false);
            ingredientAmountTextField.setBorder(null);

            deleteIngredientButton.setVisible(false);
            addIngredientButton.setVisible(false);
        }
    }

    private void updateIngredientDisplay() {
        this.addIngredientButton.setVisible(!this.isIngredientAlreadyStored);

        BufferedImage ingredientImage = UNKNOWN_IMAGE;
        if (ingredient.getIngredientImage() != null) {
            ingredientImage = ImageUtils.resizeImage(ingredient.getIngredientImage(), 50, 50);
        }
        this.ingredientImageLabel.setIcon(new ImageIcon(ingredientImage));
    }
}
