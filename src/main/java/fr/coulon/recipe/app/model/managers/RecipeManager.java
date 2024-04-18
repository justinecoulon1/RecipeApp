package fr.coulon.recipe.app.model.managers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.coulon.recipe.app.model.recipe.Ingredient;
import fr.coulon.recipe.app.model.recipe.Recipe;
import fr.coulon.recipe.app.model.recipe.Step;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RecipeManager {

    public static final RecipeManager INSTANCE = new RecipeManager();
    private final List<Recipe> recipes = new ArrayList<>();
    private final List<RecipeManagerListener> listeners = new ArrayList<>();
    private Recipe selectedRecipe;
    private final File recipeJsonFile;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private RecipeManager() {
        String appFolderPath = System.getProperty("user.home") + "/.RecipeApp";
        File appFolder = new File(appFolderPath);
        if (!appFolder.exists()) {
            appFolder.mkdirs();
        }
        String recipeFilePath = appFolderPath + "/recipe.json";
        recipeJsonFile = new File(recipeFilePath);
        if (recipeJsonFile.exists()) {
            try {
                List<Recipe> initialRecipes = objectMapper.readValue(recipeJsonFile, new TypeReference<>() {});
                recipes.addAll(initialRecipes);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
        saveRecipe();
        for (RecipeManagerListener listener : listeners) {
            listener.onRecipeAddition(recipe);
        }
    }

    public void deleteRecipe(Recipe recipe) {
        recipes.remove(recipe);
        if (recipe == selectedRecipe) {
            if (recipes.size() == 0) {
                selectRecipe(null);
            } else {
                selectRecipe(recipes.get(0));
            }
        }
        for (RecipeManagerListener listener : listeners) {
            listener.onRecipeDeletion(recipe);
        }
    }

    public void selectRecipe(Recipe recipe) {
        selectedRecipe = recipe;
        for (RecipeManagerListener listener : listeners) {
            listener.onRecipeSelection(selectedRecipe);
        }
    }

    public void updateRecipe(Recipe toUpdateRecipe, String newRecipeName, List<Step> newSteps, Map<Ingredient, String> newAmountByIngredient) {
        toUpdateRecipe.setName(newRecipeName);
        toUpdateRecipe.getSteps().clear();
        toUpdateRecipe.getSteps().addAll(newSteps);
        toUpdateRecipe.getAmountByIngredient().clear();
        toUpdateRecipe.getAmountByIngredient().putAll(newAmountByIngredient);

        saveRecipe();
        for (RecipeManagerListener listener : listeners) {
            listener.onRecipeUpdate(toUpdateRecipe);
        }
    }

    public List<Recipe> getAllRecipes() {
        return new ArrayList<>(recipes);
    }

    public void saveRecipe() {
        try {
            objectMapper.writeValue(recipeJsonFile, recipes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addListener(RecipeManagerListener recipeManagerListener) {
        listeners.add(recipeManagerListener);
    }
}
