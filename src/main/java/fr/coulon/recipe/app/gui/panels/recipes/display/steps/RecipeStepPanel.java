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

public class RecipeStepPanel extends JPanel {

    private final RecipeStepsDisplayPanel recipeStepsDisplayPanel;
    private final JButton deleteStepButton;
    private final Step step;
    private final JLabel stepNumberLabel;
    private final JTextArea stepDescriptionTextArea;

    public RecipeStepPanel(Step step, int stepIndex, RecipeStepsDisplayPanel recipeStepsDisplayPanel, RecipeDisplayMode displayMode) {
        this.recipeStepsDisplayPanel = recipeStepsDisplayPanel;
        this.step = step;

        this.setBackground(RecipeAppConstants.DARK_BACKGROUND_COLOR);
        this.setLayout(new MigLayout("fill, nogrid, ins 15"));

        stepNumberLabel = new JLabel();
        stepNumberLabel.setBackground(this.getBackground());
        setStepIndex(stepIndex);
        stepNumberLabel.setFont(RecipeAppConstants.SMALL_TITLE_FONT);
        stepNumberLabel.setVerticalAlignment(JLabel.TOP);
        stepNumberLabel.setForeground(Color.white);
        stepNumberLabel.setOpaque(true);
        this.add(stepNumberLabel, "gapafter 10");

        stepDescriptionTextArea = new JTextArea();
        stepDescriptionTextArea.setText(step.getDescription());
        stepDescriptionTextArea.setBackground(this.getBackground());
        stepDescriptionTextArea.setForeground(Color.WHITE);
        stepDescriptionTextArea.setFont(RecipeAppConstants.DEFAULT_FONT);
        stepDescriptionTextArea.setOpaque(true);
        stepDescriptionTextArea.setLineWrap(true); // Enable line wrapping
        stepDescriptionTextArea.setWrapStyleWord(true);
        this.add(stepDescriptionTextArea, "grow");

        deleteStepButton = RecipeButtonUtils.createSmallButton(UiIcons.DELETE);
        deleteStepButton.addActionListener(this::handleDeleteStepButton);
        this.add(deleteStepButton, "h 30, w 30, gapbefore 10");

        updateDisplayMode(displayMode);
    }

    private void handleDeleteStepButton(ActionEvent e) {
        this.recipeStepsDisplayPanel.deleteStepPanel(this.getStep());
    }

    public void setStepIndex(int stepIndex) {
        stepNumberLabel.setText(stepIndex + ".");
    }

    public Step getStep() {
        return step;
    }

    public String getStepDescription() {
        return stepDescriptionTextArea.getText();
    }

    public void updateDisplayMode(RecipeDisplayMode displayMode) {
        if (displayMode == RecipeDisplayMode.UPDATE || displayMode == RecipeDisplayMode.CREATE) {
            stepDescriptionTextArea.setEditable(true);
            stepDescriptionTextArea.setFocusable(true);
            stepDescriptionTextArea.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.gray), BorderFactory.createEmptyBorder(0, 5, 0, 5)));
            stepDescriptionTextArea.setCaretColor(Color.white);
            stepDescriptionTextArea.setAlignmentX(JTextArea.CENTER_ALIGNMENT);

            deleteStepButton.setVisible(true);
        } else if (displayMode == RecipeDisplayMode.READ) {
            stepDescriptionTextArea.setEditable(false);
            stepDescriptionTextArea.setBorder(null);
            stepDescriptionTextArea.setFocusable(false);

            deleteStepButton.setVisible(false);
        }
    }
}
