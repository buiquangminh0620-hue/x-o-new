package Logic;

import Fancy.Characters;
import Fancy.Theme;
import GUI.Play;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MenuActions {

    public static void openPlay(JFrame parent) {
        Play ui = new Play();
        ui.setVisible(true);
        if (parent != null) {
            parent.dispose();
        }
    }

    public static void showModeDialog(JFrame parent) {
        String[] options = {"3x3", "5x5"};
        String choice = themedInputDialog(
                parent,
                "Choose board size",
                "Mode",
                options,
                options[0]
        );
        if (choice == null) return;

        if (choice.startsWith("3")) {
            logic.setBoardSize(3);
        } else {
            logic.setBoardSize(5);
        }

        themedMessageDialog(parent, "Mode set to " + choice, "Mode");
    }

    public static void showThemeDialog(JFrame parent) {
        Theme[] themes = Theme.presets();
        String[] names = new String[themes.length];
        for (int i = 0; i < themes.length; i++) {
            names[i] = themes[i].name;
        }

        String choice = themedInputDialog(
                parent,
                "Choose a color theme",
                "Color",
                names,
                names[0]
        );
        if (choice == null) return;

        Theme selected = Theme.byName(choice);
        if (selected != null) {
            logic.setTheme(selected);
            themedMessageDialog(parent, "Theme set to " + selected.name, "Theme");
        }
    }

    public static void showCharacterDialog(JFrame parent) {
        String[] options = Characters.names();

        String player1 = themedInputDialog(
                parent,
                "Player 1 character",
                "Character",
                options,
                options[0]
        );
        if (player1 == null) return;

        String player2 = themedInputDialog(
                parent,
                "Player 2 character",
                "Character",
                options,
                options.length > 1 ? options[1] : options[0]
        );
        if (player2 == null) return;

        logic.setPlayerSymbols(player1, player2);
        themedMessageDialog(parent, "Characters set to " + player1 + " and " + player2, "Character");
    }

    private static String themedInputDialog(JFrame parent, String message, String title, String[] options, String initial) {
        Theme theme = logic.getTheme();
        return withTheme(theme, () -> (String) JOptionPane.showInputDialog(
                parent,
                message,
                title,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                initial
        ));
    }

    private static void themedMessageDialog(JFrame parent, String message, String title) {
        Theme theme = logic.getTheme();
        withTheme(theme, () -> {
            JOptionPane.showMessageDialog(parent, message, title, JOptionPane.PLAIN_MESSAGE);
            return null;
        });
    }

    private static <T> T withTheme(Theme theme, ThemeDialogAction<T> action) {
        Map<String, Object> oldValues = new HashMap<>();
        String[] keys = {
                "OptionPane.background",
                "Panel.background",
                "OptionPane.messageForeground",
                "OptionPane.messageFont",
                "OptionPane.buttonFont",
                "Button.background",
                "Button.foreground",
                "Button.select",
                "Button.focus"
        };
        for (String key : keys) {
            oldValues.put(key, UIManager.get(key));
        }

        UIManager.put("OptionPane.background", theme.primaryDark.darker());
        UIManager.put("Panel.background", theme.primaryDark.darker());
        UIManager.put("OptionPane.messageForeground", theme.text);
        UIManager.put("OptionPane.messageFont", new Font("SansSerif", Font.BOLD, 16));
        UIManager.put("OptionPane.buttonFont", new Font("SansSerif", Font.BOLD, 14));
        UIManager.put("Button.background", theme.primary);
        UIManager.put("Button.foreground", theme.text);
        UIManager.put("Button.select", theme.accent);
        UIManager.put("Button.focus", theme.accent);

        try {
            return action.run();
        } finally {
            for (Map.Entry<String, Object> entry : oldValues.entrySet()) {
                UIManager.put(entry.getKey(), entry.getValue());
            }
        }
    }

    private interface ThemeDialogAction<T> {
        T run();
    }
}