package GUI;

import Fancy.Theme;
import Logic.MenuActions;
import Logic.logic;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainMenu extends JFrame {

    public MainMenu() {
        Theme theme = logic.getTheme();

        Color BG = theme.background;
        Color PRIMARY = theme.primary;
        Color PRIMARY_DARK = theme.primaryDark;
        Color TEXT = theme.text;
        Color ACCENT = theme.accent;

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setTitle("Tic Tac Toe - Main Menu");

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(BG);
        setContentPane(root);

        JPanel headerPanel = new JPanel(new GridBagLayout());
        headerPanel.setBackground(BG);
        headerPanel.setBorder(new EmptyBorder(30, 20, 20, 20));

        JLabel titleLabel = new JLabel("TIC TAC TOE");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 56));
        titleLabel.setForeground(ACCENT);
        headerPanel.add(titleLabel);
        root.add(headerPanel, BorderLayout.NORTH);

        JPanel center = new JPanel(new GridBagLayout());
        center.setBackground(BG);
        center.setBorder(new EmptyBorder(20, 20, 40, 20));

        RoundedPanel menuCard = new RoundedPanel(30);
        menuCard.setBackground(PRIMARY_DARK);
        menuCard.setBorder(new EmptyBorder(30, 60, 30, 60));
        menuCard.setLayout(new GridLayout(4, 1, 0, 20));

        JButton playBtn = menuButton("Play", TEXT);
        playBtn.addActionListener(e -> MenuActions.openPlay(this));

        JButton modeBtn = menuButton("Mode", TEXT);
        modeBtn.addActionListener(e -> MenuActions.showModeDialog(this));

        JButton colorBtn = menuButton("Color", TEXT);
        colorBtn.addActionListener(e -> MenuActions.showThemeDialog(this));

        JButton characterBtn = menuButton("Character", TEXT);
        characterBtn.addActionListener(e -> MenuActions.showCharacterDialog(this));

        menuCard.add(playBtn);
        menuCard.add(modeBtn);
        menuCard.add(colorBtn);
        menuCard.add(characterBtn);

        center.add(menuCard);
        root.add(center, BorderLayout.CENTER);
    }

    private static JButton menuButton(String text, Color fg) {
        JButton b = new JButton(text);
        b.setForeground(fg);
        b.setFont(new Font("Arial", Font.BOLD, 26));
        b.setOpaque(false);
        b.setContentAreaFilled(false);
        b.setBorderPainted(false);
        b.setFocusPainted(false);
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));

        b.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                b.setFont(new Font("Arial", Font.BOLD, 30));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                b.setFont(new Font("Arial", Font.BOLD, 26));
            }
        });

        return b;
    }

    static class RoundedPanel extends JPanel {
        private final int arc;
        RoundedPanel(int arc) { this.arc = arc; setOpaque(false); }

        @Override protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
            g2.dispose();
            super.paintComponent(g);
        }
    }
}