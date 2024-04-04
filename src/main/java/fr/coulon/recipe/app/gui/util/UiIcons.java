package fr.coulon.recipe.app.gui.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public enum UiIcons {

    TOQUE("/icons/toque.png"), // TOQUE = new UiIcons("/icons/toque.png")
    PLUS("/icons/plus.png"),
    VALIDATE("/icons/validate.png"),
    DELETE("/icons/delete.png"),
    UNKNOWN("/icons/unknown.png"),
    RECIPE("/icons/recipe.png"),
    REFRESH("/icons/refresh.png"),
    INGREDIENTS("/icons/ingredients.png"),
    INVALID("/icons/invalid.png"),
    EDIT("/icons/edit.png"),
    WINTER("/icons/winter.png"),
    SPRING("/icons/spring.png"),
    SUMMER("/icons/summer.png"),
    AUTUMN("/icons/autumn.png"),
    CARBOHYDRATE("/icons/carbohydrate.png"),
    CONDIMENT("/icons/condiment.png"),
    DAIRY("/icons/dairy.png"),
    DRINKS("/icons/drinks.png"),
    FISH("/icons/fish.png"),
    GREENS("/icons/greens.png"),
    GLUTEN("/icons/gluten.png"),
    MEAT("/icons/meat.png"),
    CANCEL("/icons/cancel.png");

    private BufferedImage image;

    UiIcons(String path) {
        try (InputStream inputStream = getClass().getResourceAsStream(path)) {
            this.image = ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getImage() {
        return image;
    }
}
