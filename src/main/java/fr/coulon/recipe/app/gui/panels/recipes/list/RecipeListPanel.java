package fr.coulon.recipe.app.gui.panels.recipes.list;

import fr.coulon.recipe.app.gui.util.RecipeAppBorderUtils;
import fr.coulon.recipe.app.gui.util.RecipeAppConstants;
import fr.coulon.recipe.app.model.managers.RecipeManager;
import fr.coulon.recipe.app.model.managers.RecipeManagerListener;
import fr.coulon.recipe.app.model.recipe.Recipe;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class RecipeListPanel extends JPanel implements RecipeManagerListener {

    private final JPanel recipeNamesContainerPanel;
    private final List<RecipeNamePanel> recipeNamePanels = new ArrayList<>();

    public RecipeListPanel() {
        RecipeManager.INSTANCE.addListener(this);

        this.setBackground(RecipeAppConstants.PANEL_BACKGROUND_COLOR);
        this.setLayout(new MigLayout("fill, nogrid, ins 0"));

        RecipeListHeaderPanel recipeListHeaderPanel = new RecipeListHeaderPanel();
        recipeListHeaderPanel.setBorder(RecipeAppBorderUtils.HEADER_DELIMITING_BORDER);
        this.add(recipeListHeaderPanel, "dock north, wrap, h 55!");

        recipeNamesContainerPanel = new JPanel(new MigLayout("fillx, ins 0"));
        recipeNamesContainerPanel.setBackground(getBackground());
        JScrollPane scrollPane = new JScrollPane(this.recipeNamesContainerPanel);
        scrollPane.setHorizontalScrollBar(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        scrollPane.setBorder(null);
        this.add(scrollPane,"grow");
    }

    @Override
    public void onRecipeAddition(Recipe addedRecipe) {
        RecipeNamePanel recipeNamePanel = new RecipeNamePanel(addedRecipe);
        recipeNamesContainerPanel.add(recipeNamePanel, "growx, wrap, aligny top");
        recipeNamePanels.add(recipeNamePanel);
        if (super.getRootPane() != null) {
            super.getRootPane().updateUI();
        }
    }

    @Override
    public void onRecipeDeletion(Recipe deletedRecipe) {
        RecipeNamePanel recipeNamePanelToDelete = null;
        for (RecipeNamePanel recipeNamePanel : recipeNamePanels) {
            if (deletedRecipe == recipeNamePanel.getRecipe()) {
                recipeNamesContainerPanel.remove(recipeNamePanel);
                recipeNamePanelToDelete = recipeNamePanel;
            }
        }
        recipeNamePanels.remove(recipeNamePanelToDelete);
        super.getRootPane().updateUI();
    }

    @Override
    public void onRecipeSelection(Recipe selectedRecipe) {
        for (RecipeNamePanel recipeNamePanel : recipeNamePanels) {
            if (selectedRecipe == recipeNamePanel.getRecipe()) {
                recipeNamePanel.setBackground(RecipeAppConstants.SELECTED_COLOR);
                recipeNamePanel.setBorder(RecipeAppBorderUtils.SELECTED_NAME_PANEL_BORDER);
                recipeNamePanel.getRecipeNameLabel().setBackground(RecipeAppConstants.SELECTED_COLOR);
                recipeNamePanel.getRecipeNameLabel().setFont(RecipeAppConstants.SELECTED_DEFAULT_FONT);
            } else {
                recipeNamePanel.setBackground(RecipeAppConstants.DARK_BACKGROUND_COLOR);
                recipeNamePanel.setBorder(null);
                recipeNamePanel.getRecipeNameLabel().setBackground(RecipeAppConstants.DARK_BACKGROUND_COLOR);
                recipeNamePanel.getRecipeNameLabel().setFont(RecipeAppConstants.DEFAULT_FONT);
            }
        }
        if (super.getRootPane() != null) {
            super.getRootPane().updateUI();
        }
    }

    @Override
    public void onRecipeUpdate(Recipe updatedRecipe) {
        for (RecipeNamePanel recipeNamePanel : recipeNamePanels) {
            if (updatedRecipe == recipeNamePanel.getRecipe()) {
                recipeNamePanel.getRecipeNameLabel().setText(updatedRecipe.getName());
            }
        }
        super.getRootPane().updateUI();
    }
}
