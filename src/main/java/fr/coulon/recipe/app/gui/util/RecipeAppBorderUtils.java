package fr.coulon.recipe.app.gui.util;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class RecipeAppBorderUtils {

    public static final Border HEADER_DELIMITING_BORDER =  BorderFactory.createMatteBorder(0,0,1,0, Color.lightGray);
    public static final Border SMALL_TITLE_EMPTY_BORDER = createEmptyBorder(RecipeAppConstants.SMALL_TITLE_BORDER_SIZE);
    public static final Border TITLE_EMPTY_BORDER = createEmptyBorder(RecipeAppConstants.TITLE_BORDER_SIZE);
    public static final Border SELECTED_NAME_PANEL_BORDER = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2);

    public static Border createEmptyBorder(int borderSize){
        return BorderFactory.createEmptyBorder(borderSize, borderSize,borderSize,borderSize);
    }
}
