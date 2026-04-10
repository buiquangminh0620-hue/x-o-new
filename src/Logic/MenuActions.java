import GUI.MainMenu;

    // existing code...

    if (selected != null) {
        logic.setTheme(selected);
        themedMessageDialog(parent, "Theme set to " + selected.name, "Theme");
        if (parent != null) {
            SwingUtilities.invokeLater(() -> {
                parent.dispose();
                MainMenu menu = new MainMenu();
                menu.setVisible(true);
            });
        }
    }