package fr.coulon.recipe.app.gui.panels;

import fr.coulon.recipe.app.gui.panels.ingredients.IngredientsMainPanel;
import fr.coulon.recipe.app.gui.panels.recipes.RecipeMainPanel;
import fr.coulon.recipe.app.gui.util.ui.image.ImageUtils;
import fr.coulon.recipe.app.gui.util.RecipeAppConstants;
import fr.coulon.recipe.app.gui.util.ui.image.UiIcons;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class NavigationBarPanel extends JPanel {
    private final List<TabPanel> tabPanels = new ArrayList<>();
    private final MainPanel mainPanel;

    public NavigationBarPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
        this.setBackground(RecipeAppConstants.DARK_BACKGROUND_COLOR);
        this.setLayout(new MigLayout("fill, nogrid, ins 0"));

        JPanel tabContainerPanel = new JPanel(new MigLayout("ins 0, gap 0"));
        tabContainerPanel.setBackground(getBackground());
        this.add(tabContainerPanel, "dock north");

        BufferedImage recipeImage = ImageUtils.resizeImage(UiIcons.RECIPE.getImage(), 64, 64);
        TabPanel recipeTab = new TabPanel(recipeImage, this, new RecipeMainPanel());
        tabContainerPanel.add(recipeTab, "aligny top, h 80!, w 80!, wrap");
        recipeTab.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, RecipeAppConstants.PANEL_BACKGROUND_COLOR));
        tabPanels.add(recipeTab);

        BufferedImage ingredientsImage = ImageUtils.resizeImage(UiIcons.INGREDIENTS.getImage(), 64, 64);
        TabPanel ingredientsTab = new TabPanel(ingredientsImage, this, new IngredientsMainPanel());
        tabContainerPanel.add(ingredientsTab, "aligny top, h 80!, w 80!, wrap");
        ingredientsTab.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, RecipeAppConstants.PANEL_BACKGROUND_COLOR));
        tabPanels.add(ingredientsTab);

        selectTab(recipeTab);
    }

    public void selectTab(TabPanel toSelectTabPanel) {

        for (TabPanel tabPanel : tabPanels) {
            if (toSelectTabPanel == tabPanel) {
                tabPanel.setBackground(RecipeAppConstants.SELECTED_COLOR);
                tabPanel.setSelected(true);
            } else {
                tabPanel.setBackground(RecipeAppConstants.DARK_BACKGROUND_COLOR);
                tabPanel.setSelected(false);
            }
        }
        mainPanel.setDisplayedPanel(toSelectTabPanel.getDisplayedPanel());
        if (super.getRootPane() != null) {
            super.getRootPane().updateUI();
        }
    }
}
