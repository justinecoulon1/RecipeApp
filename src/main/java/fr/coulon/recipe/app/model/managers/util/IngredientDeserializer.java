package fr.coulon.recipe.app.model.managers.util;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import fr.coulon.recipe.app.model.managers.IngredientManager;
import fr.coulon.recipe.app.model.recipe.Ingredient;

import java.io.IOException;

public class IngredientDeserializer extends KeyDeserializer {

    @Override
    public Object deserializeKey(String key, DeserializationContext ctxt) throws IOException {
        Ingredient ingredient = IngredientManager.INSTANCE.getIngredientByName(key);
        if (ingredient != null) {
            return ingredient;
        }
        return new Ingredient(key);
    }
}
