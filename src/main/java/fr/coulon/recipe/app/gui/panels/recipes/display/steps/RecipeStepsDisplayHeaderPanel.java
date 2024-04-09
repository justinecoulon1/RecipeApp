package fr.coulon.recipe.app.gui.panels.recipes.display.steps;

import fr.coulon.recipe.app.gui.panels.recipes.display.RecipeDisplayMode;
import fr.coulon.recipe.app.gui.util.RecipeAppConstants;
import fr.coulon.recipe.app.gui.util.ui.RecipeButtonUtils;
import fr.coulon.recipe.app.gui.util.ui.image.UiIcons;
import fr.coulon.recipe.app.model.recipe.Step;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RecipeStepsDisplayHeaderPanel extends JPanel {

    private final RecipeStepsDisplayPanel recipeStepsDisplayPanel;
    private final JButton addStepButton;

    public RecipeStepsDisplayHeaderPanel(RecipeStepsDisplayPanel recipeStepsDisplayPanel, RecipeDisplayMode displayMode) {
        this.recipeStepsDisplayPanel = recipeStepsDisplayPanel;

        this.setBackground(RecipeAppConstants.DARK_BACKGROUND_COLOR);
        this.setLayout(new MigLayout("fillx, ins 0 20 0 15, nogrid, align center"));

        JLabel stepsTitleLabel = new JLabel();
        stepsTitleLabel.setText("Steps");
        stepsTitleLabel.setBackground(this.getBackground());
        stepsTitleLabel.setFont(RecipeAppConstants.SMALL_TITLE_FONT);
        stepsTitleLabel.setForeground(Color.white);
        stepsTitleLabel.setOpaque(true);
        this.add(stepsTitleLabel, "growx");

        addStepButton = RecipeButtonUtils.createSmallButton(UiIcons.PLUS);
        addStepButton.addActionListener(this::handleStepAdditionButton);
        this.add(addStepButton, "h 30!, w 30!");

        updateDisplayMode(displayMode);
    }

    private void handleStepAdditionButton(ActionEvent e) {
        this.recipeStepsDisplayPanel.addStepPanel(new Step(""), RecipeDisplayMode.UPDATE);
    }

    public void updateDisplayMode(RecipeDisplayMode displayMode) {
        addStepButton.setVisible(displayMode == RecipeDisplayMode.CREATE || displayMode == RecipeDisplayMode.UPDATE);
    }
}
