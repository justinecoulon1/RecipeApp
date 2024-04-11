package fr.coulon.recipe.app.gui.panels.ingredients.cards.edit;

import fr.coulon.recipe.app.gui.util.ui.image.ImageUtils;
import fr.coulon.recipe.app.gui.util.RecipeAppConstants;
import fr.coulon.recipe.app.gui.util.ui.TitleSeparator;
import fr.coulon.recipe.app.gui.util.ui.image.UiIcons;
import fr.coulon.recipe.app.model.recipe.Ingredient;
import net.miginfocom.swing.MigLayout;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class IngredientEditCardImageInfoPanel extends JPanel {

    private static final BufferedImage INVALID_IMAGE = ImageUtils.resizeImage(UiIcons.INVALID.getImage(), 185, 185);
    private final JTextArea urlTextArea;
    private final JLabel ingredientImageLabel;
    private BufferedImage ingredientImage;

    public IngredientEditCardImageInfoPanel(Ingredient ingredient) {
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
        ingredientImage = ingredient.getIngredientImage();
        if (ingredientImage == null) {
            ingredientImageLabel.setIcon(new ImageIcon(ImageUtils.resizeImage(UiIcons.PLUS.getImage(), 185, 185)));
        } else {
            ingredientImageLabel.setIcon(new ImageIcon(ImageUtils.resizeImage(ingredientImage, 250, 250)));
        }

        ingredientImageLabel.setBackground(RecipeAppConstants.DECORATION_BACKGROUND_COLOR);
        ingredientImageLabel.setForeground(new Color(0x4E5052));
        ingredientImageLabel.setOpaque(true);
        ingredientImageLabel.setFont(RecipeAppConstants.TITLE_FONT);
        ingredientImageLabel.setHorizontalAlignment(JLabel.CENTER);
        ingredientImageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        this.add(ingredientImageLabel, "h 250!, w 250!, alignx center, wrap");

        ingredientImageLabel.setDropTarget(new DropTarget() {
            public synchronized void drop(DropTargetDropEvent dropTargetDropEvent) {
                try {
                    dropTargetDropEvent.acceptDrop(DnDConstants.ACTION_COPY);
                    List<File> droppedFiles = (List<File>) dropTargetDropEvent.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
                    if (droppedFiles.size() == 1) {
                        BufferedImage droppedImage = ImageIO.read(droppedFiles.get(0));
                        updateIngredientImage(droppedImage);
                    } else {
                        ingredientImageLabel.setIcon(null);
                        ingredientImageLabel.setText("Please drop a single file");
                    }
                } catch (UnsupportedFlavorException | IOException e) {
                    ingredientImageLabel.setIcon(null);
                    ingredientImageLabel.setText("Please drop a single file");
                    e.printStackTrace();
                }
            }
        });

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
            IngredientEditCardImageInfoPanel.this.revalidate();
            IngredientEditCardImageInfoPanel.this.repaint();
            BufferedImage downloadedImage = ImageUtils.downloadImageFromUrl(url);

            updateIngredientImage(downloadedImage);
        }).start();
    }

    private void updateIngredientImage(BufferedImage image) {
        BufferedImage resizedImage = INVALID_IMAGE;
        if (image != null) {
            resizedImage = ImageUtils.resizeImage(image, 250, 250);
        }
        ingredientImage = resizedImage;
        ingredientImageLabel.setIcon(new ImageIcon(resizedImage));
        ingredientImageLabel.setText(null);

        this.revalidate();
        this.repaint();
    }

    public BufferedImage getIngredientImage() {
        return ingredientImage;
    }
}
