package Logic;

import Fancy.Characters;
import Fancy.Theme;
import GUI.Play;

import javax.swing.*;

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
        String choice = (String) JOptionPane.showInputDialog(
                parent,
                "Choose board size",
                "Mode",
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]
        );
        if (choice == null) return;

        if (choice.startsWith("3")) {
            logic.setBoardSize(3);
        } else {
            logic.setBoardSize(5);
        }

        JOptionPane.showMessageDialog(parent, "Mode set to " + choice);
    }

    public static void showThemeDialog(JFrame parent) {
        Theme[] themes = Theme.presets();
        String[] names = new String[themes.length];
        for (int i = 0; i < themes.length; i++) {
            names[i] = themes[i].name;
        }

        String choice = (String) JOptionPane.showInputDialog(
                parent,
                "Choose a color theme",
                "Color",
                JOptionPane.PLAIN_MESSAGE,
                null,
                names,
                names[0]
        );
        if (choice == null) return;

        Theme selected = Theme.byName(choice);
        if (selected != null) {
            logic.setTheme(selected);
            JOptionPane.showMessageDialog(parent, "Theme set to " + selected.name);
        }
    }

    public static void showCharacterDialog(JFrame parent) {
        String[] options = Characters.names();

        String player1 = (String) JOptionPane.showInputDialog(
                parent,
                "Player 1 character",
                "Character",
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]
        );
        if (player1 == null) return;

        String player2 = (String) JOptionPane.showInputDialog(
                parent,
                "Player 2 character",
                "Character",
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[1]
        );
        if (player2 == null) return;

        logic.setPlayerSymbols(player1, player2);
        JOptionPane.showMessageDialog(parent, "Characters set to " + player1 + " and " + player2);
    }
}