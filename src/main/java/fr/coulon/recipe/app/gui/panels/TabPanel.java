package fr.coulon.recipe.app.gui.panels;

import fr.coulon.recipe.app.gui.util.RecipeAppConstants;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class TabPanel extends JPanel implements MouseListener {

    private final NavigationBarPanel navigationBarPanel;
    private boolean isSelected = false;
    private final JPanel displayedPanel;
    public TabPanel(BufferedImage image, NavigationBarPanel navigationBarPanel, JPanel displayedPanel) {
        this.displayedPanel = displayedPanel;
        this.navigationBarPanel = navigationBarPanel;
        this.setLayout(new MigLayout("fill, ins 0"));
        this.addMouseListener(this);
        this.setBackground(RecipeAppConstants.DARK_BACKGROUND_COLOR);

        JLabel label = new JLabel();
        ImageIcon icon = new ImageIcon(image);
        label.setBackground(getBackground());
        label.setIcon(icon);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVisible(true);
        this.add(label, "al center");
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        navigationBarPanel.selectTab(this);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.setBackground(RecipeAppConstants.SELECTED_COLOR);
        super.getRootPane().updateUI();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (!this.isSelected) {
            this.setBackground(RecipeAppConstants.DARK_BACKGROUND_COLOR);
            super.getRootPane().updateUI();
        }
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public JPanel getDisplayedPanel() {
        return displayedPanel;
    }
}
