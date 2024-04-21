package fr.coulon.recipe.app.gui.panels.recipes.list;

import fr.coulon.recipe.app.gui.util.RecipeAppConstants;
import fr.coulon.recipe.app.gui.util.ui.RecipeButtonUtils;
import fr.coulon.recipe.app.gui.util.ui.image.UiIcons;
import fr.coulon.recipe.app.model.managers.RecipeManager;
import fr.coulon.recipe.app.model.recipe.Recipe;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RecipeListHeaderPanel extends JPanel {

    public RecipeListHeaderPanel() {
        this.setLayout(new MigLayout("fill, nogrid, ins 5"));
        this.setBackground(RecipeAppConstants.DARK_BACKGROUND_COLOR);

        JLabel recipeListTitleLabel = new JLabel();
        recipeListTitleLabel.setText("List of recipes");
        recipeListTitleLabel.setBackground(this.getBackground());
        recipeListTitleLabel.setFont(RecipeAppConstants.SMALL_TITLE_FONT);
        recipeListTitleLabel.setForeground(Color.white);
        recipeListTitleLabel.setHorizontalAlignment(JLabel.CENTER);
        recipeListTitleLabel.setOpaque(true);
        this.add(recipeListTitleLabel, "growx");

        JButton createRecipeButton = RecipeButtonUtils.createSmallButton(UiIcons.PLUS);
        createRecipeButton.addActionListener(this::handleCreateRecipeButton);
        createRecipeButton.setToolTipText("Create a recipe");
        this.add(createRecipeButton, "h 30!, w 30!, gapafter 10");
    }

    private void handleCreateRecipeButton(ActionEvent e) {
        Recipe recipe = new Recipe("New Recipe");
        RecipeManager.INSTANCE.addRecipe(recipe);
        RecipeManager.INSTANCE.selectRecipe(recipe);

    }
}
