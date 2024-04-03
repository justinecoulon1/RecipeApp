package fr.coulon.recipe.app.gui;

import fr.coulon.recipe.app.gui.panels.MainPanel;
import fr.coulon.recipe.app.gui.util.RecipeAppConstants;
import fr.coulon.recipe.app.gui.util.UiIcons;

import javax.swing.*;

public class MainFrame extends JFrame {

    public MainFrame() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1600, 1000);
        this.setTitle("Recipe App");

        this.setResizable(false);
        this.setIconImage(UiIcons.TOQUE.getImage());

        MainPanel mainPanel = new MainPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(
                RecipeAppConstants.DEFAULT_BORDER_SIZE,
                RecipeAppConstants.DEFAULT_BORDER_SIZE,
                RecipeAppConstants.DEFAULT_BORDER_SIZE,
                RecipeAppConstants.DEFAULT_BORDER_SIZE));
        this.setContentPane(mainPanel);

        this.setVisible(true);
    }
}
