package fr.coulon.recipe.app.gui.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;

public class ImageUtils {
    public static BufferedImage resizeImage(BufferedImage image, int width, int height) {
        Image tmp = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return resizedImage;
    }

    public static Image imageToGrayScale(BufferedImage originalImage) {
        ImageFilter filter = new GrayFilter(true, 5);
        ImageProducer producer = new FilteredImageSource(originalImage.getSource(), filter);
        return Toolkit.getDefaultToolkit().createImage(producer);
    }

    public static BufferedImage downloadImageFromUrl(String url) { // String fileName, String directory
        try (BufferedInputStream input = new BufferedInputStream(new URL(url).openStream())) {
            return ImageIO.read(input);
        } catch (IOException e) {
            return null;
        }
    }
}
