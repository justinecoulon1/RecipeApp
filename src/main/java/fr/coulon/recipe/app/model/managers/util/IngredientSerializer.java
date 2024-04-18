package fr.coulon.recipe.app.model.managers.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import fr.coulon.recipe.app.model.recipe.Ingredient;

import java.io.IOException;

public class IngredientSerializer extends JsonSerializer<Ingredient> {

    @Override
    public void serialize(Ingredient ingredient, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(ingredient.getName());
    }
}
