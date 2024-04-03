package fr.coulon.recipe.app.gui.util;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class TitleSeparator extends JPanel {
    public TitleSeparator(String text, Color backgroundColor) {
        super(new MigLayout("ins 0, fill, nogrid"));
        this.setBackground(backgroundColor);

        JSeparator separator1 = new JSeparator();
        separator1.setForeground(RecipeAppConstants.BORDERS_AND_SEPARATORS_WHITE_COLOR);
        separator1.setBackground(RecipeAppConstants.BORDERS_AND_SEPARATORS_WHITE_COLOR);
        this.add(separator1, "growx, aligny center");

        JLabel textLabel = new JLabel();
        textLabel.setText(text);
        textLabel.setFont(RecipeAppConstants.SMALL_TITLE_FONT);
        textLabel.setOpaque(true);
        textLabel.setBackground(getBackground());
        this.add(textLabel, "aligny center, alignx center, gapbefore 10, gapafter 10");

        JSeparator separator2 = new JSeparator();
        separator2.setForeground(RecipeAppConstants.BORDERS_AND_SEPARATORS_WHITE_COLOR);
        separator2.setBackground(RecipeAppConstants.BORDERS_AND_SEPARATORS_WHITE_COLOR);
        this.add(separator2, "growx, aligny center");
    }
}
