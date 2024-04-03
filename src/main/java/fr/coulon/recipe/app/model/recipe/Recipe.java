package fr.coulon.recipe.app.model.recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Recipe {

        private String name;
        private Map<Ingredient, String> amountByIngredient;
        private List<Step> steps;

        public Recipe(String name, Map<Ingredient, String> amountByIngredient, List<Step> steps) {
                this.name = name;
                this.amountByIngredient = amountByIngredient;
                this.steps = steps;
        }

        public Recipe(String name) {
                this.name = name;
                this.amountByIngredient = new HashMap<>();
                this.steps = new ArrayList<>();
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public Map<Ingredient, String> getAmountByIngredient() {
                return amountByIngredient;
        }

        public List<Step> getSteps() {
                return steps;
        }
}
