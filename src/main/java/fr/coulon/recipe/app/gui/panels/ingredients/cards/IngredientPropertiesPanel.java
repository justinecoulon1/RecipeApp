package fr.coulon.recipe.app.gui.panels.ingredients.cards;

import fr.coulon.recipe.app.gui.util.RecipeAppConstants;
import fr.coulon.recipe.app.gui.util.ui.image.UiIcons;
import fr.coulon.recipe.app.model.recipe.IngredientProperties;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class IngredientPropertiesPanel extends JPanel {

    private final IngredientPropertyLabel winterImageLabel;
    private final IngredientPropertyLabel springImageLabel;
    private final IngredientPropertyLabel summerImageLabel;
    private final IngredientPropertyLabel autumnImageLabel;
    private final IngredientPropertyLabel glutenImageLabel;
    private final IngredientPropertyLabel fishImageLabel;
    private final IngredientPropertyLabel meatImageLabel;
    private final IngredientPropertyLabel carbohydrateImageLabel;
    private final IngredientPropertyLabel dairyImageLabel;
    private final IngredientPropertyLabel drinksImageLabel;
    private final IngredientPropertyLabel condimentsImageLabel;
    private final IngredientPropertyLabel greensImageLabel;

    public IngredientPropertiesPanel(IngredientProperties ingredientProperties, IngredientsCardMode cardMode) {
        this.setLayout(new MigLayout("fill, nogrid, ins 0"));
        this.setBackground(RecipeAppConstants.DARK_BACKGROUND_COLOR);

        JLabel seasonsHeader = new JLabel();
        seasonsHeader.setText("Seasons");
        seasonsHeader.setFont(RecipeAppConstants.SMALL_TITLE_FONT);
        seasonsHeader.setOpaque(true);
        seasonsHeader.setBackground(getBackground());
        this.add(seasonsHeader, "growx, dock north, gapbefore 15, h 45!, wrap");

        JPanel seasonPanel = new JPanel(new MigLayout("fill, ins 0, wrap 4", "[23%][23%][23%][23%]", "[]"));
        seasonPanel.setBackground(getBackground());
        this.add(seasonPanel, "dock north, wrap");

        winterImageLabel = addIngredientPropertyLabel(UiIcons.WINTER, cardMode, seasonPanel, "Winter");
        springImageLabel = addIngredientPropertyLabel(UiIcons.SPRING, cardMode, seasonPanel, "Spring");
        summerImageLabel = addIngredientPropertyLabel(UiIcons.SUMMER, cardMode, seasonPanel, "Summer");
        autumnImageLabel = addIngredientPropertyLabel(UiIcons.AUTUMN, cardMode, seasonPanel, "Autumn");

        JLabel categoriesHeader = new JLabel();
        categoriesHeader.setText("Categories");
        categoriesHeader.setFont(RecipeAppConstants.SMALL_TITLE_FONT);
        categoriesHeader.setOpaque(true);
        categoriesHeader.setBackground(getBackground());
        this.add(categoriesHeader, "growx, dock north, gapbefore 15, h 45!, wrap");

        JPanel categoriesPanel = new JPanel(new MigLayout("fill, ins 0, wrap 4", "[23%][23%][23%][23%]", "[]"));
        categoriesPanel.setBackground(getBackground());
        this.add(categoriesPanel, "dock north, wrap");

        glutenImageLabel = addIngredientPropertyLabel(UiIcons.GLUTEN, cardMode, categoriesPanel, "Gluten");
        fishImageLabel = addIngredientPropertyLabel(UiIcons.FISH, cardMode, categoriesPanel, "Fish");
        meatImageLabel = addIngredientPropertyLabel(UiIcons.MEAT, cardMode, categoriesPanel, "Meat");
        carbohydrateImageLabel = addIngredientPropertyLabel(UiIcons.CARBOHYDRATE, cardMode, categoriesPanel, "Carbohydrates");
        dairyImageLabel = addIngredientPropertyLabel(UiIcons.DAIRY, cardMode, categoriesPanel, "Dairy");
        drinksImageLabel = addIngredientPropertyLabel(UiIcons.DRINKS, cardMode, categoriesPanel, "Drinks");
        condimentsImageLabel = addIngredientPropertyLabel(UiIcons.CONDIMENT, cardMode, categoriesPanel, "Condiments");
        greensImageLabel = addIngredientPropertyLabel(UiIcons.GREENS, cardMode, categoriesPanel, "Fruits & Veggies");

        updateIngredientProperties(ingredientProperties);
    }

    private IngredientPropertyLabel addIngredientPropertyLabel(UiIcons icon, IngredientsCardMode cardMode, JPanel containerPanel, String tooltip) {
        IngredientPropertyLabel imageLabel = new IngredientPropertyLabel(icon, cardMode);
        imageLabel.setToolTipText(tooltip);
        containerPanel.add(imageLabel, "aligny top, alignx center, gapbefore 5, gapafter 5");
        return imageLabel;
    }

    public IngredientProperties getUpdatedIngredientProperties() {
        IngredientProperties ingredientProperties = new IngredientProperties();
        ingredientProperties.setGrowingInWinter(winterImageLabel.isSelected());
        ingredientProperties.setGrowingInSpring(springImageLabel.isSelected());
        ingredientProperties.setGrowingInSummer(summerImageLabel.isSelected());
        ingredientProperties.setGrowingInAutumn(autumnImageLabel.isSelected());
        ingredientProperties.setGluten(glutenImageLabel.isSelected());
        ingredientProperties.setFish(fishImageLabel.isSelected());
        ingredientProperties.setMeat(meatImageLabel.isSelected());
        ingredientProperties.setCarbohydrate(carbohydrateImageLabel.isSelected());
        ingredientProperties.setDairy(dairyImageLabel.isSelected());
        ingredientProperties.setDrinks(drinksImageLabel.isSelected());
        ingredientProperties.setCondiment(condimentsImageLabel.isSelected());
        ingredientProperties.setGreens(greensImageLabel.isSelected());
        return ingredientProperties;
    }

    public void updateIngredientProperties(IngredientProperties ingredientProperties) {
        winterImageLabel.setSelected(ingredientProperties.isGrowingInWinter());
        springImageLabel.setSelected(ingredientProperties.isGrowingInSpring());
        summerImageLabel.setSelected(ingredientProperties.isGrowingInSummer());
        autumnImageLabel.setSelected(ingredientProperties.isGrowingInAutumn());
        glutenImageLabel.setSelected(ingredientProperties.isGluten());
        fishImageLabel.setSelected(ingredientProperties.isFish());
        meatImageLabel.setSelected(ingredientProperties.isMeat());
        carbohydrateImageLabel.setSelected(ingredientProperties.isCarbohydrate());
        dairyImageLabel.setSelected(ingredientProperties.isDairy());
        drinksImageLabel.setSelected(ingredientProperties.isDrinks());
        condimentsImageLabel.setSelected(ingredientProperties.isCondiment());
        greensImageLabel.setSelected(ingredientProperties.isGreens());

    }
}
