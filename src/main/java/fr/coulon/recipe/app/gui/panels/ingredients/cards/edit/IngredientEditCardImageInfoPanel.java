package fr.coulon.recipe.app.gui.panels.ingredients.cards.edit;

import fr.coulon.recipe.app.gui.util.ImageUtils;
import fr.coulon.recipe.app.gui.util.RecipeAppConstants;
import fr.coulon.recipe.app.gui.util.TitleSeparator;
import fr.coulon.recipe.app.gui.util.UiIcons;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class IngredientEditCardImageInfoPanel extends JPanel {

    public IngredientEditCardImageInfoPanel() {
        this.setLayout(new MigLayout("fill, ins 15, nogrid"));
        this.setBackground(RecipeAppConstants.DARK_BACKGROUND_COLOR);
        this.setBorder(BorderFactory.createLineBorder(RecipeAppConstants.BORDERS_AND_SEPARATORS_WHITE_COLOR, 1));

        JLabel dragImageLabel = new JLabel();
        dragImageLabel.setText("Drag image below");
        dragImageLabel.setFont(RecipeAppConstants.DEFAULT_FONT);
        dragImageLabel.setOpaque(true);
        dragImageLabel.setBackground(getBackground());
        this.add(dragImageLabel, "alignx center, wrap");

        JLabel ingredientImageLabel = new JLabel();
        BufferedImage plusImage = ImageUtils.resizeImage(UiIcons.PLUS.getImage(), 185, 185);
        ingredientImageLabel.setBackground(RecipeAppConstants.DECORATION_BACKGROUND_COLOR);
        ingredientImageLabel.setForeground(new Color(0x4E5052));
        ingredientImageLabel.setOpaque(true);
        ingredientImageLabel.setIcon(new ImageIcon(plusImage));
        ingredientImageLabel.setHorizontalAlignment(JLabel.CENTER);
        ingredientImageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        this.add(ingredientImageLabel, "h 250!, w 250!, alignx center, wrap");

        this.add(new TitleSeparator("OR", getBackground()), "grow, pushy, gapx 20 20, wrap");

        JLabel copyImageUrl = new JLabel();
        copyImageUrl.setText("Copy an image URL");
        copyImageUrl.setFont(RecipeAppConstants.DEFAULT_FONT);
        copyImageUrl.setOpaque(true);
        copyImageUrl.setBackground(getBackground());
        this.add(copyImageUrl, "alignx center, aligny bottom, wrap");

        JTextArea urlTextArea = new JTextArea();
        urlTextArea.setBackground(getBackground());
        urlTextArea.setForeground(Color.WHITE);
        urlTextArea.setFont(RecipeAppConstants.DEFAULT_FONT);
        urlTextArea.setOpaque(true);
        urlTextArea.setFocusable(true);
        urlTextArea.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.gray), BorderFactory.createEmptyBorder(0, 5, 0, 5)));
        urlTextArea.setCaretColor(Color.white);
        urlTextArea.setLineWrap(true);
        urlTextArea.setWrapStyleWord(true);
        this.add(urlTextArea, "growx, h 100!, alignx center");
    }
}
