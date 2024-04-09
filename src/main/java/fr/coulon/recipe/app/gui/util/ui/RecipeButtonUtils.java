package fr.coulon.recipe.app.gui.util.ui;

import fr.coulon.recipe.app.gui.util.ui.image.ImageUtils;
import fr.coulon.recipe.app.gui.util.RecipeAppConstants;
import fr.coulon.recipe.app.gui.util.ui.image.UiIcons;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class RecipeButtonUtils {

    public static JButton createBigButton(UiIcons icon) {
        JButton button = new JButton();
        BufferedImage image = ImageUtils.resizeImage(icon.getImage(), 20, 20);
        button.setBackground(RecipeAppConstants.DECORATION_BACKGROUND_COLOR);
        button.setIcon(new ImageIcon(image));
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        button.setFocusable(false);
        return button;
    }

    public static JButton createSmallButton(UiIcons icon) {
        JButton button = new JButton();
        BufferedImage image = ImageUtils.resizeImage(icon.getImage(), 15, 15);
        button.setBackground(RecipeAppConstants.DECORATION_BACKGROUND_COLOR);
        button.setIcon(new ImageIcon(image));
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        button.setFocusable(false);
        return button;
    }

}
