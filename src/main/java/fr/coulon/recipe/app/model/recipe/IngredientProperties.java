package fr.coulon.recipe.app.model.recipe;

public class IngredientProperties {

    private boolean growingInWinter = true;
    private boolean growingInSpring = true;
    private boolean growingInSummer = true;
    private boolean growingInAutumn = true;
    private boolean carbohydrate;
    private boolean condiment;
    private boolean dairy;
    private boolean drinks;
    private boolean fish;
    private boolean greens;
    private boolean gluten;
    private boolean meat;
    public boolean isGrowingInWinter() {
        return growingInWinter;
    }

    public void setGrowingInWinter(boolean growingInWinter) {
        this.growingInWinter = growingInWinter;
    }

    public boolean isGrowingInSpring() {
        return growingInSpring;
    }

    public void setGrowingInSpring(boolean growingInSpring) {
        this.growingInSpring = growingInSpring;
    }

    public boolean isGrowingInSummer() {
        return growingInSummer;
    }

    public void setGrowingInSummer(boolean growingInSummer) {
        this.growingInSummer = growingInSummer;
    }

    public boolean isGrowingInAutumn() {
        return growingInAutumn;
    }

    public void setGrowingInAutumn(boolean growingInAutumn) {
        this.growingInAutumn = growingInAutumn;
    }

    public boolean isCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(boolean carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public boolean isCondiment() {
        return condiment;
    }

    public void setCondiment(boolean condiment) {
        this.condiment = condiment;
    }

    public boolean isDairy() {
        return dairy;
    }

    public void setDairy(boolean dairy) {
        this.dairy = dairy;
    }

    public boolean isDrinks() {
        return drinks;
    }

    public void setDrinks(boolean drinks) {
        this.drinks = drinks;
    }

    public boolean isFish() {
        return fish;
    }

    public void setFish(boolean fish) {
        this.fish = fish;
    }

    public boolean isGreens() {
        return greens;
    }

    public void setGreens(boolean greens) {
        this.greens = greens;
    }

    public boolean isGluten() {
        return gluten;
    }

    public void setGluten(boolean gluten) {
        this.gluten = gluten;
    }

    public boolean isMeat() {
        return meat;
    }

    public void setMeat(boolean meat) {
        this.meat = meat;
    }
}
