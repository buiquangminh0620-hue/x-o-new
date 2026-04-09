import GUI.MainMenu;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            MainMenu ui = new MainMenu();
            ui.setVisible(true);
        });
    }
}