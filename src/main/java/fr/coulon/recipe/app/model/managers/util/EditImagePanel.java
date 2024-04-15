package fr.coulon.recipe.app.model.managers.util;

import fr.coulon.recipe.app.gui.util.RecipeAppConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class EditImagePanel extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener {

    private static final double ZOOM_FACTOR = 1.1;
    private static final Font FONT = RecipeAppConstants.TITLE_FONT;

    private BufferedImage modifiableImage;
    private BufferedImage placeHolderImage;
    private String text;
    private double currentZoom = 1.0;
    private boolean dragging = false;
    private int dragStartX = 0;
    private int dragStartY = 0;
    private int offsetX = 0;
    private int offsetY = 0;

    public EditImagePanel(BufferedImage modifiableImage) {
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addMouseWheelListener(this);
        this.modifiableImage = modifiableImage;
    }

    public void setModifiableImage(BufferedImage modifiableImage) {
        this.modifiableImage = modifiableImage;
        if (modifiableImage != null) {
            double imageHeight = modifiableImage.getHeight();
            if (imageHeight != 0) {
                this.currentZoom = (double) this.getHeight() / imageHeight;
            } else {
                this.currentZoom = 1;
            }
            this.offsetX = (int) (this.getWidth() - modifiableImage.getWidth() * currentZoom) / 2;
            this.offsetY = 0;
        }
        repaint();
    }

    public void setPlaceHolderImage(BufferedImage placeHolderImage) {
        this.placeHolderImage = placeHolderImage;
        repaint();
    }

    public BufferedImage getModifiedImage() {
        if (text != null || placeHolderImage != null) {
            return null;
        }
        BufferedImage image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
        drawImage(image.createGraphics());
        return image;
    }

    public void setText(String text) {
        this.text = text;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        drawImage(g);
    }

    private void drawImage(Graphics g) {
        g.setColor(this.getBackground());
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.BLACK);

        if (placeHolderImage != null) {
            g.drawImage(placeHolderImage, 15, 15, this.getWidth() - 30, this.getHeight() - 30, null);
        }
        if (text != null && !text.isEmpty()) {
            FontMetrics metrics = g.getFontMetrics(FONT);
            int x = (getWidth() - metrics.stringWidth(text)) / 2;
            int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
            g.setFont(FONT);
            g.drawString(text, x, y);
        } else if (modifiableImage != null && placeHolderImage == null) {
            g.drawImage(modifiableImage, offsetX, offsetY, (int) (modifiableImage.getWidth() * currentZoom), (int) (modifiableImage.getHeight() * currentZoom), null);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            dragging = true;
            dragStartX = e.getX();
            dragStartY = e.getY();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            dragging = false;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (dragging) {
            int newX = e.getX();
            int newY = e.getY();
            offsetX += newX - dragStartX;
            offsetY += newY - dragStartY;
            dragStartX = newX;
            dragStartY = newY;
        }
        repaint();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        currentZoom /= Math.pow(ZOOM_FACTOR, e.getPreciseWheelRotation());
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
