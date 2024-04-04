package fr.coulon.recipe.app.gui.panels.ingredients.cards.edit;

import fr.coulon.recipe.app.gui.util.ImageUtils;
import fr.coulon.recipe.app.gui.util.RecipeAppConstants;
import fr.coulon.recipe.app.gui.util.TitleSeparator;
import fr.coulon.recipe.app.gui.util.UiIcons;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

public class IngredientEditCardImageInfoPanel extends JPanel {

    private static final BufferedImage INVALID_IMAGE = ImageUtils.resizeImage(UiIcons.INVALID.getImage(), 185, 185);
    private final JTextArea urlTextArea;
    private final JLabel ingredientImageLabel;

    public IngredientEditCardImageInfoPanel() {
        this.setLayout(new MigLayout("fill, ins 15, nogrid"));
        this.setBackground(RecipeAppConstants.DARK_BACKGROUND_COLOR);
        this.setBorder(BorderFactory.createLineBorder(RecipeAppConstants.BORDERS_AND_SEPARATORS_WHITE_COLOR, 1));

        JLabel dragImageLabel = new JLabel();
        dragImageLabel.setText("Drag an image below");
        dragImageLabel.setFont(RecipeAppConstants.DEFAULT_FONT);
        dragImageLabel.setOpaque(true);
        dragImageLabel.setBackground(getBackground());
        this.add(dragImageLabel, "alignx center, wrap");

        ingredientImageLabel = new JLabel();
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
        this.add(copyImageUrl, "alignx center, aligny bottom");

        JButton refreshImageButton = new JButton("OK");
        refreshImageButton.setBackground(RecipeAppConstants.DECORATION_BACKGROUND_COLOR);
        refreshImageButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 10));
        refreshImageButton.setForeground(Color.BLACK);
        refreshImageButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        refreshImageButton.setFocusable(false);
        refreshImageButton.addActionListener(this::handleRefreshImageButton);
        this.add(refreshImageButton, "alignx right, h 23!, w 23!, wrap");

        urlTextArea = new JTextArea();
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

    private void handleRefreshImageButton(ActionEvent actionEvent) {
        new Thread(() -> {
            String url = urlTextArea.getText();
            ingredientImageLabel.setIcon(null);
            ingredientImageLabel.setText("Downloading...");
            ingredientImageLabel.setFont(RecipeAppConstants.TITLE_FONT);
            IngredientEditCardImageInfoPanel.super.getRootPane().updateUI();
            BufferedImage downloadedImage = ImageUtils.downloadImageFromUrl(url);

            BufferedImage resizedImage = INVALID_IMAGE;
            if (downloadedImage != null) {
                resizedImage = ImageUtils.resizeImage(downloadedImage, 250, 250);
            }

            ingredientImageLabel.setIcon(new ImageIcon(resizedImage));
            ingredientImageLabel.setText(null);
            IngredientEditCardImageInfoPanel.super.getRootPane().updateUI();
        }).start();
    }
}
