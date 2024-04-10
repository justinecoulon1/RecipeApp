package fr.coulon.recipe.app.model.managers.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class BufferedImageDeserializer extends JsonDeserializer<BufferedImage> {
    @Override
    public BufferedImage deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(jsonParser.getBinaryValue())) {
            return ImageIO.read(bais);
        }
    }
}
