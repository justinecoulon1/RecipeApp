package fr.coulon.recipe.app.gui.util;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class StringUtils {

    public static String removeAccentsAndLowerCase(String input) {
        String normalizedString = Normalizer.normalize(input.toLowerCase(), Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(normalizedString).replaceAll("");
    }
}
