package fr.coulon.recipe.app.gui.util.ui.customtextfield;

import fr.coulon.recipe.app.gui.util.RecipeAppConstants;
import fr.coulon.recipe.app.gui.util.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchableTextField extends JComboBox<String> {
    private static final List<Integer> NON_UPDATING_KEY_CODES = Arrays.asList(
            KeyEvent.VK_CONTROL,
            KeyEvent.VK_ALT_GRAPH,
            KeyEvent.VK_ALT,
            KeyEvent.VK_LEFT,
            KeyEvent.VK_RIGHT,
            KeyEvent.VK_DOWN,
            KeyEvent.VK_UP
    );
    private final SearchableTextFieldItemsGetter searchableTextFieldItemsGetter;
    private final JTextField textField;

    public SearchableTextField(SearchableTextFieldItemsGetter searchableTextFieldItemsGetter, String initialText) {
        super(searchableTextFieldItemsGetter.getItems());
        this.searchableTextFieldItemsGetter = searchableTextFieldItemsGetter;
        this.setEditable(true);

        this.setUI(new SearchableTextFieldBasicComboBoxUI());
        this.remove(this.getComponent(0));

        textField = (JTextField) this.getEditor().getEditorComponent();
        textField.setCaretColor(Color.WHITE);

        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_ENTER || keyCode == KeyEvent.VK_ESCAPE) {
                    hidePopup();
                } else if (!e.isAltDown() && !e.isControlDown() && !e.isAltGraphDown() && !NON_UPDATING_KEY_CODES.contains(keyCode)) {
                    SwingUtilities.invokeLater(() -> comboFilter(textField.getText(), true));
                }
            }
        });

        ListCellRenderer<? super String> defaultRenderer = this.getRenderer();
        setRenderer((list, value, index, isSelected, cellHasFocus) -> {
            JComponent component = (JComponent) defaultRenderer.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (isSelected) {
                component.setBackground(RecipeAppConstants.SELECTED_COLOR);
            } else if (cellHasFocus) {
                component.setBackground(RecipeAppConstants.BACKGROUND_COLOR);
            } else {
                component.setBackground(RecipeAppConstants.PANEL_BACKGROUND_COLOR);
            }
            component.setBorder(BorderFactory.createEmptyBorder(4,4,4,4));
            return component;
        });

        setText(initialText);
        MouseListener[] mouseListeners = this.getMouseListeners();
        for (MouseListener mouseListener : mouseListeners) {
            this.removeMouseListener(mouseListener);
        }
    }

    private void comboFilter(String enteredText, boolean canShowPopup) {
        List<String> filteredItems = new ArrayList<>();
        String enteredTextWithoutAccents = StringUtils.removeAccentsAndLowerCase(enteredText);
        for (String baseItem : searchableTextFieldItemsGetter.getItems()) {
            if (StringUtils.removeAccentsAndLowerCase(baseItem).contains(enteredTextWithoutAccents)) {
                filteredItems.add(baseItem);
            }
        }
        int caretPos = textField.getCaretPosition();
        if (!enteredText.isEmpty() && filteredItems.size() > 0 && (filteredItems.size() < 10 || enteredText.length() >= 3)) {
            this.setModel(new DefaultComboBoxModel<>(filteredItems.toArray(String[]::new)));
            this.setSelectedItem(enteredText);
            if (canShowPopup) {
                this.showPopup();
            }
        } else {
            this.hidePopup();
        }
        textField.setCaretPosition(caretPos);
    }

    public void setText(String text) {
        SwingUtilities.invokeLater(() -> comboFilter(text, false));
        setSelectedItem(text);
    }

    public String getText() {
        return textField.getText();
    }

    @Override
    public void setBackground(Color bg) {
        super.setBackground(bg);
        if (textField != null) {
            textField.setBackground(bg);
        }
    }

    public JTextField getTextField() {
        return textField;
    }
}
