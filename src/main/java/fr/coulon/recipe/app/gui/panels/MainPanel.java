package fr.coulon.recipe.app.gui.panels;

import fr.coulon.recipe.app.gui.util.RecipeAppConstants;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class MainPanel extends JPanel {
    private final JPanel mainContentPanel = new JPanel(new MigLayout("fill, nogrid, ins 0"));
    public MainPanel() {
        this.setLayout(new MigLayout("fill, nogrid, ins 0"));
        this.setBackground(RecipeAppConstants.BACKGROUND_COLOR);

        NavigationBarPanel navigationBarPanel = new NavigationBarPanel(this);
        navigationBarPanel.setBorder(BorderFactory.createLineBorder(RecipeAppConstants.BORDERS_AND_SEPARATORS_WHITE_COLOR, 2));
        this.add(navigationBarPanel, "align left, growy, gapright 10");

        this.add(mainContentPanel, "grow");
    }

    public void setDisplayedPanel(JPanel displayedPanel) {
        mainContentPanel.removeAll();
        mainContentPanel.add(displayedPanel, "grow");
    }
}
