package fr.coulon.recipe.app;

import com.formdev.flatlaf.FlatDarculaLaf;
import fr.coulon.recipe.app.gui.MainFrame;

public class Main {
    public static void main(String[] args) {
        FlatDarculaLaf.setup();
        new MainFrame();
    }
}
