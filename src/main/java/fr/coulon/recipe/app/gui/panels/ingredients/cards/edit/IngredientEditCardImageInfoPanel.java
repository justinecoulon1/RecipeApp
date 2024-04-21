package fr.coulon.recipe.app.gui.panels.ingredients.cards.edit;

import fr.coulon.recipe.app.gui.util.RecipeAppConstants;
import fr.coulon.recipe.app.gui.util.ui.TitleSeparator;
import fr.coulon.recipe.app.gui.util.ui.image.ImageUtils;
import fr.coulon.recipe.app.gui.util.ui.image.UiIcons;
import fr.coulon.recipe.app.model.managers.util.EditImagePanel;
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
    private final JTextArea urlTextArea;
    private EditImagePanel editImagePanel;

    public IngredientEditCardImageInfoPanel(Ingredient ingredient) {
        this.setLayout(new MigLayout("fill, ins 15, nogrid"));
        this.setBackground(RecipeAppConstants.DARK_BACKGROUND_COLOR);
        this.setBorder(BorderFactory.createLineBorder(RecipeAppConstants.BORDERS_AND_SEPARATORS_WHITE_COLOR, 1));

        JPanel ingredientImagePanel = new JPanel(new MigLayout("fill, nogrid, ins 0"));
        ingredientImagePanel.setBackground(this.getBackground());

        JLabel dragImageLabel = new JLabel();
        dragImageLabel.setText("Drag an image below");
        dragImageLabel.setFont(RecipeAppConstants.DEFAULT_FONT);
        dragImageLabel.setOpaque(true);
        dragImageLabel.setBackground(getBackground());
        this.add(dragImageLabel, "alignx center, wrap");

        BufferedImage ingredientImage = ingredient.getIngredientImage();
        editImagePanel = new EditImagePanel(ingredientImage);
        if (ingredientImage == null) {
            editImagePanel.setPlaceHolderImage(UiIcons.PLUS.getImage());
        }
        editImagePanel.setBackground(RecipeAppConstants.DECORATION_BACKGROUND_COLOR);
        ingredientImagePanel.add(editImagePanel, "h 250!, w 250!, alignx center, wrap");

        editImagePanel.setDropTarget(new DropTarget() {
            public synchronized void drop(DropTargetDropEvent dropTargetDropEvent) {
                try {
                    dropTargetDropEvent.acceptDrop(DnDConstants.ACTION_COPY);
                    List<File> droppedFiles = (List<File>) dropTargetDropEvent.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
                    if (droppedFiles.size() == 1) {
                        BufferedImage droppedImage = ImageIO.read(droppedFiles.get(0));
                        updateIngredientImage(droppedImage);
                    } else {
                        editImagePanel.setText("Please drop a single file");
                        editImagePanel.setPlaceHolderImage(null);
                        editImagePanel.setModifiableImage(null);
                    }
                } catch (UnsupportedFlavorException | IOException e) {
                    updateIngredientImage(null);
                    e.printStackTrace();
                }
            }
        });
        this.add(ingredientImagePanel, "growx, aligny top, wrap");

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
        refreshImageButton.setToolTipText("Display image");
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
            editImagePanel.setText("Downloading...");
            IngredientEditCardImageInfoPanel.this.revalidate();
            IngredientEditCardImageInfoPanel.this.repaint();
            BufferedImage downloadedImage = ImageUtils.downloadImageFromUrl(url);

            updateIngredientImage(downloadedImage);
        }).start();
    }

    private void updateIngredientImage(BufferedImage image) {
        if (image == null) {
            editImagePanel.setPlaceHolderImage(UiIcons.INVALID.getImage());
            editImagePanel.setModifiableImage(null);
        } else {
            editImagePanel.setModifiableImage(image);
            editImagePanel.setPlaceHolderImage(null);
        }
        editImagePanel.setText(null);

        this.revalidate();
        this.repaint();
    }

    public BufferedImage getModifiedIngredientImage() {
        return editImagePanel.getModifiedImage();
    }
}
