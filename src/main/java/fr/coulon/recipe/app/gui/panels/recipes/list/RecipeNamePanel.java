package fr.coulon.recipe.app.gui.panels.recipes.list;

import fr.coulon.recipe.app.gui.util.RecipeAppConstants;
import fr.coulon.recipe.app.gui.util.ui.RecipeButtonUtils;
import fr.coulon.recipe.app.gui.util.ui.image.UiIcons;
import fr.coulon.recipe.app.model.managers.RecipeManager;
import fr.coulon.recipe.app.model.recipe.Recipe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RecipeNamePanel extends JPanel implements MouseListener {

    private final Recipe recipe;
    private final JLabel recipeNameLabel;
    private final JButton deleteRecipeButton;
    private final Component deleteRecipeButtonFiller;
    public RecipeNamePanel(Recipe recipe) {
        this.recipe = recipe;
        this.setBackground(RecipeAppConstants.DARK_BACKGROUND_COLOR);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setMinimumSize(new Dimension(0, 50));
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE,50));
        this.addMouseListener(this);

        recipeNameLabel = new JLabel();
        recipeNameLabel.setText(recipe.getName());
        recipeNameLabel.setBackground(this.getBackground());
        recipeNameLabel.setFont(RecipeAppConstants.DEFAULT_FONT);
        recipeNameLabel.setForeground(Color.white);
        recipeNameLabel.setOpaque(true);
        recipeNameLabel.setHorizontalAlignment(JLabel.CENTER);
        recipeNameLabel.setVerticalAlignment(JLabel.CENTER);
        recipeNameLabel.setMinimumSize(new Dimension(0, 50));
        recipeNameLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE,50));
        this.add(recipeNameLabel);

        this.add(Box.createHorizontalGlue());

        deleteRecipeButton = RecipeButtonUtils.createSmallButton(UiIcons.DELETE);
        deleteRecipeButton.setVisible(false);
        int plusButtonSize = 30;
        deleteRecipeButton.setMaximumSize(new Dimension(plusButtonSize, plusButtonSize));
        deleteRecipeButton.setMinimumSize(new Dimension(plusButtonSize, plusButtonSize));
        deleteRecipeButton.setPreferredSize(new Dimension(plusButtonSize, plusButtonSize));
        deleteRecipeButton.setToolTipText("Delete recipe");

        deleteRecipeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                RecipeNamePanel.this.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                RecipeNamePanel.this.mouseExited(e);
            }
        });

        deleteRecipeButton.addActionListener(this::handleDeleteRecipeButton);
        this.add(deleteRecipeButton, "h 30!, w 30!");

        deleteRecipeButtonFiller = Box.createRigidArea(new Dimension(30, 0));
        deleteRecipeButtonFiller.setVisible(true);
        this.add(deleteRecipeButtonFiller);

        this.add(Box.createRigidArea(new Dimension(10, 0)));
    }

    public JLabel getRecipeNameLabel() {
        return recipeNameLabel;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        RecipeManager.INSTANCE.selectRecipe(recipe);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        deleteRecipeButton.setVisible(true);
        deleteRecipeButtonFiller.setVisible(false);
        super.getRootPane().updateUI();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        deleteRecipeButton.setVisible(false);
        deleteRecipeButtonFiller.setVisible(true);
        super.getRootPane().updateUI();
    }

    private void handleDeleteRecipeButton(ActionEvent e) {
        RecipeManager.INSTANCE.deleteRecipe(recipe);
    }
}
