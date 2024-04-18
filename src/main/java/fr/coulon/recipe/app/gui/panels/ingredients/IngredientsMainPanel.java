package fr.coulon.recipe.app.gui.panels.ingredients;

import fr.coulon.recipe.app.gui.panels.ingredients.cards.IngredientsCardMode;
import fr.coulon.recipe.app.gui.panels.ingredients.cards.edit.IngredientEditCardPanel;
import fr.coulon.recipe.app.gui.panels.ingredients.cards.IngredientCardPanel;
import fr.coulon.recipe.app.gui.util.RecipeAppConstants;
import fr.coulon.recipe.app.model.managers.IngredientManager;
import fr.coulon.recipe.app.model.managers.IngredientManagerListener;
import fr.coulon.recipe.app.model.recipe.Ingredient;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class IngredientsMainPanel extends JPanel implements IngredientManagerListener {

    private final JPanel cardContainerPanel;
    private final List<IngredientCardPanel> ingredientCardPanels = new ArrayList<>();
    private JDialog dialog;

    public IngredientsMainPanel() {
        IngredientManager.INSTANCE.addListener(this);
        this.setLayout(new MigLayout("fill, ins 0"));
        this.setBackground(RecipeAppConstants.PANEL_BACKGROUND_COLOR);
        this.setBorder(BorderFactory.createLineBorder(RecipeAppConstants.BORDERS_AND_SEPARATORS_WHITE_COLOR, 2));

        IngredientsHeaderPanel ingredientsHeaderPanel = new IngredientsHeaderPanel(this);
        this.add(ingredientsHeaderPanel, "grow, h 70!, dock north, wrap");

        cardContainerPanel = new JPanel(new MigLayout("fill, ins 10, gapx 5, gapy 5, wrap 4", "[grow][grow][grow][grow]", "[]"));
        cardContainerPanel.setBackground(RecipeAppConstants.PANEL_BACKGROUND_COLOR);
        JScrollPane scrollPane = new JScrollPane(cardContainerPanel);
        scrollPane.setHorizontalScrollBar(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        scrollPane.setBorder(null);
        this.add(scrollPane, "growx, aligny top");

        for (Ingredient ingredient : IngredientManager.INSTANCE.getAllIngredients()) {
            onIngredientAddition(ingredient);
        }
    }

    @Override
    public void onIngredientAddition(Ingredient ingredient) {
        IngredientCardPanel ingredientCardPanel = new IngredientCardPanel(this, ingredient, IngredientsCardMode.READ);
        cardContainerPanel.add(ingredientCardPanel, "h 440!, w 24%!, aligny top, alignx left");
        ingredientCardPanels.add(ingredientCardPanel);
        if (super.getRootPane() != null) {
            super.getRootPane().updateUI();
        }
    }

    @Override
    public void onIngredientDeletion(Ingredient ingredient) {
        IngredientCardPanel toDeleteIngredientCardPanel = null;
        for (IngredientCardPanel ingredientCardPanel : ingredientCardPanels) {
            if (ingredient == ingredientCardPanel.getIngredient()) {
                toDeleteIngredientCardPanel = ingredientCardPanel;
                break;
            }
        }
        if (toDeleteIngredientCardPanel != null) {
            ingredientCardPanels.remove(toDeleteIngredientCardPanel);
            cardContainerPanel.remove(toDeleteIngredientCardPanel);
            if (super.getRootPane() != null) {
                super.getRootPane().updateUI();
            }
        }
    }

    @Override
    public void onIngredientUpdate(Ingredient ingredient) {
        IngredientCardPanel toUpdateIngredientCardPanel = null;
        for (IngredientCardPanel ingredientCardPanel : ingredientCardPanels) {
            if (ingredient == ingredientCardPanel.getIngredient()) {
                toUpdateIngredientCardPanel = ingredientCardPanel;
                break;
            }
        }
        if (toUpdateIngredientCardPanel != null) {
            toUpdateIngredientCardPanel.getIngredientCardHeaderPanel().updateIngredientNameTextArea();
            toUpdateIngredientCardPanel.updatePropertiesPanel();
            toUpdateIngredientCardPanel.getIngredientCardHeaderPanel().updateIngredientImageLabel();
            if (super.getRootPane() != null) {
                super.getRootPane().updateUI();
            }
        }
    }

    public void openUpdateIngredientPopup(Ingredient ingredient) {
        openIngredientPopup(ingredient, IngredientsCardMode.UPDATE);
    }

    public void openCreateIngredientPopup(Ingredient ingredient) {
        openIngredientPopup(ingredient, IngredientsCardMode.CREATE);
    }

    private void openIngredientPopup(Ingredient ingredient, IngredientsCardMode cardMode) {
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);

        dialog = new JDialog(topFrame);
        if (cardMode == IngredientsCardMode.UPDATE) {
            dialog.setTitle("Updating ingredient: " + ingredient.getName());
        } else {
            dialog.setTitle("New ingredient");
        }

        dialog.setResizable(false);
        dialog.setSize(730, 550);
        dialog.setLocationRelativeTo(this);
        dialog.setModalityType(Dialog.ModalityType.DOCUMENT_MODAL);

        IngredientEditCardPanel ingredientEditCardPanel = new IngredientEditCardPanel(ingredient, this, cardMode);
        dialog.setContentPane(ingredientEditCardPanel);

        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                ingredientEditCardPanel.getIngredientEditCardHeaderPanel().requestFocusTextField();
            }
        });

        dialog.setVisible(true);
    }

    public void closeIngredientPopup() {
        dialog.dispose();
    }
}
