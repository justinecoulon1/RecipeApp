package fr.coulon.recipe.app.model.managers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import fr.coulon.recipe.app.gui.util.StringUtils;
import fr.coulon.recipe.app.model.managers.util.BufferedImageDeserializer;
import fr.coulon.recipe.app.model.managers.util.BufferedImageSerializer;
import fr.coulon.recipe.app.model.recipe.Ingredient;
import fr.coulon.recipe.app.model.recipe.IngredientProperties;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IngredientManager {

    public static final IngredientManager INSTANCE = new IngredientManager();
    private final Map<String, Ingredient> ingredientByName = new HashMap<>();
    private final List<IngredientManagerListener> ingredientListeners = new ArrayList<>();
    private final File ingredientsJsonFile;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private IngredientManager() {
        SimpleModule module = new SimpleModule();
        module.addSerializer(BufferedImage.class, new BufferedImageSerializer());
        module.addDeserializer(BufferedImage.class, new BufferedImageDeserializer());
        objectMapper.registerModule(module);

        String appFolderPath = System.getProperty("user.home") + "/.RecipeApp";
        File appFolder = new File(appFolderPath);
        if (!appFolder.exists()) {
            appFolder.mkdirs();
        }
        String ingredientsFilePath = appFolderPath + "/ingredients.json";
        ingredientsJsonFile = new File(ingredientsFilePath);
        if (ingredientsJsonFile.exists()) {
            try {
                HashMap<String, Ingredient> initialIngredientByName = objectMapper.readValue(ingredientsJsonFile, new TypeReference<>() {});
                ingredientByName.putAll(initialIngredientByName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void addOrUpdateIngredient(Ingredient ingredient, boolean isUpdate, String newIngredientName, IngredientProperties ingredientProperties, BufferedImage newIngredientImage) {
        if (isUpdate && getIngredientByName(ingredient.getName()) == null ) {
            throw new RuntimeException("Ingredient does not exist: " + ingredient.getName());
        }
        if ((!isUpdate || !newIngredientName.equals(ingredient.getName())) && getIngredientByName(newIngredientName) != null) {
            throw new RuntimeException("Ingredient already exists: " + newIngredientName);
        }
        if (isUpdate) {
            ingredientByName.remove(StringUtils.removeAccentsAndLowerCase(ingredient.getName()));
        }
        ingredient.setName(newIngredientName);
        ingredient.setIngredientProperties(ingredientProperties);
        ingredient.setIngredientImage(newIngredientImage);
        ingredientByName.put(StringUtils.removeAccentsAndLowerCase(ingredient.getName()), ingredient);

        saveIngredients();

        for (IngredientManagerListener ingredientListener : ingredientListeners) {
            if (isUpdate) {
                ingredientListener.onIngredientUpdate(ingredient);
            } else {
                ingredientListener.onIngredientAddition(ingredient);
            }
        }
    }

    public void deleteIngredient(Ingredient ingredient) {
        if (getIngredientByName(ingredient.getName()) == null) {
            throw new RuntimeException("Ingredient does not exists : " + ingredient.getName());
        }
        ingredientByName.remove(StringUtils.removeAccentsAndLowerCase(ingredient.getName()));

        saveIngredients();

        for (IngredientManagerListener ingredientListener : ingredientListeners) {
            ingredientListener.onIngredientDeletion(ingredient);
        }
    }

    public List<Ingredient> getAllIngredients() {
        return this.ingredientByName.values().stream().toList();
    }

    public List<String> getIngredientsNames() {
        return this.ingredientByName.values().stream().map(Ingredient::getName).toList();
    }

    public Ingredient getIngredientByName(String ingredientName) {
        return ingredientByName.get(StringUtils.removeAccentsAndLowerCase(ingredientName));
    }

    private void saveIngredients() {
        try {
            objectMapper.writeValue(ingredientsJsonFile, ingredientByName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addListener(IngredientManagerListener ingredientManagerListener) {
        ingredientListeners.add(ingredientManagerListener);
    }
}
