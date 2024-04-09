package fr.coulon.recipe.app.gui.panels.ingredients.cards;

import fr.coulon.recipe.app.gui.util.ui.image.ImageUtils;
import fr.coulon.recipe.app.gui.util.ui.image.UiIcons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class IngredientPropertyLabel extends JLabel implements MouseListener {

    private final ImageIcon imageIcon;
    private final ImageIcon grayImageIcon;
    private boolean selected = false;
    public IngredientPropertyLabel(UiIcons icon, IngredientsCardMode cardMode) {

        if (cardMode == IngredientsCardMode.UPDATE || cardMode == IngredientsCardMode.CREATE) {
            this.addMouseListener(this);
        }

        BufferedImage image = ImageUtils.resizeImage(icon.getImage(), 64, 64);
        Image grayImage = ImageUtils.imageToGrayScale(image);
        imageIcon = new ImageIcon(image);
        grayImageIcon = new ImageIcon(grayImage);

        this.setForeground(new Color(0x4E5052));
        this.setOpaque(false);
        this.setIcon(grayImageIcon);
        this.setHorizontalAlignment(JLabel.CENTER);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        setSelected(!selected);

        if (super.getRootPane() != null) {
            super.getRootPane().updateUI();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        if (selected) {
            this.setIcon(imageIcon);
        } else {
            this.setIcon(grayImageIcon);
        }
    }
}
