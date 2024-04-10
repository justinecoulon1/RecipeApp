package fr.coulon.recipe.app.model.managers.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class BufferedImageSerializer extends JsonSerializer<BufferedImage> {

    @Override
    public void serialize(BufferedImage image, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(image, "png", baos);
            byte[] bytes = baos.toByteArray();
            jsonGenerator.writeBinary(bytes);
        }
    }

}
