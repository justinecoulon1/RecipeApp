package fr.coulon.recipe.app.gui.util.ui.customtextfield;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;

public class SearchableTextFieldBasicComboBoxUI extends BasicComboBoxUI {
    @Override
    protected JButton createArrowButton() {
        return new JButton() {
            @Override
            public int getWidth() {
                return 0;
            }
        };
    }
}
