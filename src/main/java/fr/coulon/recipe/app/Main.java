package fr.coulon.recipe.app;

import com.formdev.flatlaf.FlatDarculaLaf;
import fr.coulon.recipe.app.gui.MainFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        FlatDarculaLaf.setup();
        ToolTipManager.sharedInstance().setInitialDelay(500);
        new MainFrame();
    }
}
