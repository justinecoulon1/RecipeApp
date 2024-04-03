package fr.coulon.recipe.app.gui.panels.recipes.display.steps;

import fr.coulon.recipe.app.gui.panels.recipes.display.RecipeDisplayMode;
import fr.coulon.recipe.app.gui.util.RecipeAppBorderUtils;
import fr.coulon.recipe.app.gui.util.RecipeAppConstants;
import fr.coulon.recipe.app.model.recipe.Recipe;
import fr.coulon.recipe.app.model.recipe.Step;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class RecipeStepsDisplayPanel extends JPanel {

    private final JPanel recipeStepsContainerPanel;
    private final RecipeStepsDisplayHeaderPanel recipeStepsDisplayHeaderPanel;
    private List<RecipeStepPanel> recipeStepPanels = new ArrayList<>();

    public RecipeStepsDisplayPanel(Recipe recipe, RecipeDisplayMode displayMode) {

        this.setBackground(RecipeAppConstants.PANEL_BACKGROUND_COLOR);
        this.setLayout(new MigLayout("fill, ins 0"));

        recipeStepsDisplayHeaderPanel = new RecipeStepsDisplayHeaderPanel(this, displayMode);
        recipeStepsDisplayHeaderPanel.setBorder(RecipeAppBorderUtils.HEADER_DELIMITING_BORDER);
        this.add(recipeStepsDisplayHeaderPanel, "dock north, growx, h 60!, wrap");

        this.recipeStepsContainerPanel = new JPanel(new MigLayout("fillx, ins 0"));
        this.recipeStepsContainerPanel.setBackground(getBackground());
        JScrollPane scrollPane = new JScrollPane(this.recipeStepsContainerPanel);
        scrollPane.setHorizontalScrollBar(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        scrollPane.setBorder(null);
        this.add(scrollPane, "grow");

        this.addStepPanels(recipe.getSteps(), displayMode);
    }

    private void addStepPanels(List<Step> steps, RecipeDisplayMode displayMode) {
        if (steps.isEmpty()) {
            this.addStepPanel(new Step(""), displayMode);
        } else {
            for (Step step : steps) {
                this.addStepPanel(step, displayMode);
            }
        }
    }

    public void addStepPanel(Step step, RecipeDisplayMode displayMode) {
        RecipeStepPanel recipeStepPanel = new RecipeStepPanel(step, recipeStepPanels.size() + 1, this, displayMode);
        recipeStepPanels.add(recipeStepPanel);
        recipeStepsContainerPanel.add(recipeStepPanel, "growx, hmin 80, wrap");
        if (super.getRootPane() != null) {
            super.getRootPane().updateUI();
        }
    }

    public void deleteStepPanel(Step toDeleteStep) {
        RecipeStepPanel toDeleteRecipeStepPanel = null;
        for (RecipeStepPanel recipeStepPanel : recipeStepPanels) {
            if (toDeleteStep == recipeStepPanel.getStep()) {
                toDeleteRecipeStepPanel = recipeStepPanel;
                break;
            }
        }
        if (toDeleteRecipeStepPanel != null) {
            recipeStepPanels.remove(toDeleteRecipeStepPanel);
            recipeStepsContainerPanel.remove(toDeleteRecipeStepPanel);
            int i = 1;
            for (RecipeStepPanel recipeStepPanel : recipeStepPanels) {
                recipeStepPanel.setStepIndex(i);
                i++;
            }
            if (super.getRootPane() != null) {
                super.getRootPane().updateUI();
            }
        }
    }

    public List<Step> getUpdatedSteps() {
        List<Step> steps = new ArrayList<>();
        for (RecipeStepPanel recipeStepPanel : recipeStepPanels) {
            Step step = recipeStepPanel.getStep();
            step.setDescription(recipeStepPanel.getStepDescription());
            steps.add(step);
        }
        return steps;
    }

    public void updatePanel(Recipe recipe, RecipeDisplayMode displayMode) {
        recipeStepsDisplayHeaderPanel.updateDisplayMode(displayMode);
        recipeStepPanels.clear();
        recipeStepsContainerPanel.removeAll();
        this.addStepPanels(recipe.getSteps(), displayMode);
        if (super.getRootPane() != null) {
            super.getRootPane().updateUI();
        }
    }
}
