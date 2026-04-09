package GUI;

import Fancy.Theme;
import Logic.logic;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Play extends JFrame {

    private JLabel statusLabel;
    private JLabel player1ScoreLabel;
    private JLabel player2ScoreLabel;
    private JButton[] boardButtons;

    public Play() {
        Theme theme = logic.getTheme();

        Color BG = theme.background;
        Color PRIMARY = theme.primary;
        Color PRIMARY_DARK = theme.primaryDark;
        Color BOARD_BG = theme.board;
        Color TILE_BG = theme.tile;
        Color TEXT = theme.text;
        Color TEXT_DARK = theme.textDark;
        Color ACCENT = theme.accent;

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1440, 900);
        setLocationRelativeTo(null);
        setTitle("Tic Tac Toe - X O Game");

        GradientPanel root = new GradientPanel(BG, PRIMARY_DARK.darker());
        root.setLayout(new BorderLayout());
        root.setBorder(new EmptyBorder(20, 30, 25, 30));
        setContentPane(root);

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);
        headerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("TIC TAC TOE");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 46));
        titleLabel.setForeground(ACCENT);
        headerPanel.add(titleLabel, BorderLayout.WEST);

        JPanel navBar = new JPanel(new GridLayout(1, 4, 12, 0));
        navBar.setOpaque(false);

        JComponent settingBtn = navButton("⚙ Settings", TEXT, ACCENT, () -> logic.handleSettingsClick());
        JComponent loginBtn = navButton("→ Login", TEXT, ACCENT, () -> new dangnhap());
        JComponent rulesBtn = navButton("ℹ Rules", TEXT, ACCENT, () -> logic.handleRulesClick());
        JComponent exitBtn = navButton("✖ Exit", TEXT, ACCENT, () -> logic.handleExitClick());

        navBar.add(settingBtn);
        navBar.add(loginBtn);
        navBar.add(rulesBtn);
        navBar.add(exitBtn);

        headerPanel.add(navBar, BorderLayout.EAST);
        root.add(headerPanel, BorderLayout.NORTH);

        JPanel mid = new JPanel(new GridBagLayout());
        mid.setOpaque(false);
        mid.setBorder(new EmptyBorder(20, 30, 15, 30));
        root.add(mid, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 30, 0, 30);

        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        JComponent player1Card = scoreCard("Player 1", "SCORE", PRIMARY, TEXT_DARK, TEXT, true, ACCENT);
        mid.add(player1Card, gbc);

        gbc.gridx = 2;
        gbc.anchor = GridBagConstraints.EAST;
        JComponent player2Card = scoreCard("Player 2", "SCORE", PRIMARY, TEXT_DARK, TEXT, false, ACCENT);
        mid.add(player2Card, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.CENTER;

        JPanel boardContainer = new JPanel(new BorderLayout());
        boardContainer.setOpaque(false);

        statusLabel = new JLabel("Player " + logic.getPlayerX() + "'s turn", SwingConstants.CENTER);
        statusLabel.setForeground(ACCENT);
        statusLabel.setFont(new Font("SansSerif", Font.BOLD, 26));
        boardContainer.add(statusLabel, BorderLayout.NORTH);

        int boardSize = logic.getBoardSize();
        JComponent boardComponent = createBoardPanel(boardSize, BOARD_BG, TILE_BG, TEXT, ACCENT);
        boardContainer.add(boardComponent, BorderLayout.CENTER);

        mid.add(boardContainer, gbc);

        logic.initMenuGame(boardButtons, statusLabel, player1ScoreLabel, player2ScoreLabel);

        JPanel bottom = new JPanel(new GridBagLayout());
        bottom.setOpaque(false);
        bottom.setBorder(new EmptyBorder(10, 0, 5, 0));

        GridBagConstraints bottomGbc = new GridBagConstraints();
        bottomGbc.insets = new Insets(0, 15, 0, 15);
        bottomGbc.gridy = 0;

        JComponent newGameBtn = actionButton("New Game", PRIMARY, TEXT, ACCENT, () -> logic.resetMenuGame());
        bottomGbc.gridx = 0;
        bottom.add(newGameBtn, bottomGbc);

        JComponent undoBtn = actionButton("Undo", PRIMARY, TEXT, ACCENT, () -> logic.handleUndoClick());
        bottomGbc.gridx = 1;
        bottom.add(undoBtn, bottomGbc);

        JComponent hintBtn = actionButton("Hint", PRIMARY, TEXT, ACCENT, () -> logic.handleHintClick());
        bottomGbc.gridx = 2;
        bottom.add(hintBtn, bottomGbc);

        root.add(bottom, BorderLayout.SOUTH);
    }

    private static JComponent navButton(String text, Color fg, Color glow, Runnable action) {
        JButton btn = baseButton(text, fg, 16);
        RoundedButton wrap = new RoundedButton(16, new Color(0, 0, 0, 140), glow);
        wrap.setBorder(new EmptyBorder(10, 16, 10, 16));
        wrap.setLayout(new BorderLayout());
        wrap.add(btn, BorderLayout.CENTER);

        btn.addActionListener(e -> action.run());
        btn.addMouseListener(new HoverEffect(wrap));
        return wrap;
    }

    private static JComponent actionButton(String text, Color bg, Color fg, Color glow, Runnable action) {
        JButton btn = baseButton(text, fg, 18);
        RoundedButton wrap = new RoundedButton(18, bg, glow);
        wrap.setBorder(new EmptyBorder(12, 35, 12, 35));
        wrap.setLayout(new BorderLayout());
        wrap.add(btn, BorderLayout.CENTER);

        btn.addActionListener(e -> action.run());
        btn.addMouseListener(new HoverEffect(wrap));
        return wrap;
    }

    private static JButton baseButton(String text, Color fg, int fontSize) {
        JButton b = new JButton(text);
        b.setForeground(fg);
        b.setFont(new Font("SansSerif", Font.BOLD, fontSize));
        b.setOpaque(false);
        b.setContentAreaFilled(false);
        b.setBorderPainted(false);
        b.setFocusPainted(false);
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return b;
    }

    private JComponent scoreCard(
            String player, String scoreWord,
            Color cardColor, Color titleColor, Color scoreColor, boolean isPlayer1, Color glow
    ) {
        RoundedPanel card = new RoundedPanel(22);
        card.setBackground(cardColor);
        card.setPreferredSize(new Dimension(260, 320));
        card.setLayout(new BorderLayout());
        card.setBorder(new EmptyBorder(22, 18, 22, 18));

        String symbol = isPlayer1 ? logic.getPlayerX() : logic.getPlayerO();
        JLabel title = new JLabel(
                "<html><div style='text-align:center;'><span style='font-size:18px;'>[" + symbol + "]</span><br/>" + player + "<br/>" + scoreWord + "</div></html>",
                SwingConstants.CENTER
        );
        title.setForeground(titleColor);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));

        JLabel scoreLbl = new JLabel("0", SwingConstants.CENTER);
        scoreLbl.setForeground(scoreColor);
        scoreLbl.setFont(new Font("SansSerif", Font.BOLD, 90));

        if(isPlayer1) {
            player1ScoreLabel = scoreLbl;
        } else {
            player2ScoreLabel = scoreLbl;
        }

        card.add(title, BorderLayout.NORTH);
        card.add(scoreLbl, BorderLayout.CENTER);

        GlowPanel wrap = new GlowPanel(24, glow);
        wrap.setLayout(new GridBagLayout());
        wrap.add(card);
        return wrap;
    }

    private JComponent createBoardPanel(int size, Color boardBg, Color tileBg, Color markColor, Color glow) {
        RoundedPanel boardWrap = new RoundedPanel(26);
        boardWrap.setBackground(boardBg);
        boardWrap.setBorder(new EmptyBorder(22, 22, 22, 22));
        boardWrap.setLayout(new GridLayout(size, size, 14, 14));

        int boardPx = (size == 3) ? 460 : 680;
        boardWrap.setPreferredSize(new Dimension(boardPx, boardPx));

        int fontSize = (size == 3) ? 68 : 36;
        Font markFont = new Font("SansSerif", Font.BOLD, fontSize);
        boardButtons = new JButton[size * size];

        for (int i = 0; i < size * size; i++) {
            final JButton cell = new JButton("");
            cell.setFont(markFont);
            cell.setForeground(markColor);
            cell.setFocusPainted(false);
            cell.setBorderPainted(false);
            cell.setContentAreaFilled(false);
            cell.setOpaque(false);
            cell.setCursor(new Cursor(Cursor.HAND_CURSOR));

            boardButtons[i] = cell;
            int index = i;
            cell.addActionListener(e -> logic.handleBoardClick(boardButtons[index]));

            RoundedButton tile = new RoundedButton(16, tileBg, glow);
            tile.setLayout(new BorderLayout());
            tile.add(cell, BorderLayout.CENTER);

            cell.addMouseListener(new HoverEffect(tile));

            boardWrap.add(tile);
        }

        JPanel center = new JPanel(new GridBagLayout());
        center.setOpaque(false);
        center.add(boardWrap);
        return center;
    }

    static class GradientPanel extends JPanel {
        private final Color top;
        private final Color bottom;

        GradientPanel(Color top, Color bottom) {
            this.top = top;
            this.bottom = bottom;
            setOpaque(false);
        }

        @Override protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setPaint(new GradientPaint(0, 0, top, 0, getHeight(), bottom));
            g2.fillRect(0, 0, getWidth(), getHeight());
            g2.dispose();
            super.paintComponent(g);
        }
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

    static class GlowPanel extends JPanel {
        private final int arc;
        private final Color glow;

        GlowPanel(int arc, Color glow) {
            this.arc = arc;
            this.glow = glow;
            setOpaque(false);
        }

        @Override protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(glow.getRed(), glow.getGreen(), glow.getBlue(), 60));
            g2.fillRoundRect(2, 4, getWidth() - 4, getHeight() - 4, arc, arc);
            g2.dispose();
            super.paintComponent(g);
        }
    }

    static class RoundedButton extends JPanel {
        private final int arc;
        private final Color base;
        private final Color glow;
        private boolean hover;

        RoundedButton(int arc, Color base, Color glow) {
            this.arc = arc;
            this.base = base;
            this.glow = glow;
            setOpaque(false);
        }

        void setHover(boolean hover) {
            this.hover = hover;
            repaint();
        }

        @Override protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(new Color(0, 0, 0, 90));
            g2.fillRoundRect(4, 5, getWidth() - 8, getHeight() - 8, arc, arc);

            Color fill = hover ? glow : base;
            g2.setColor(fill);
            g2.fillRoundRect(0, 0, getWidth() - 4, getHeight() - 4, arc, arc);

            g2.setColor(new Color(255, 255, 255, hover ? 70 : 40));
            g2.drawRoundRect(1, 1, getWidth() - 6, getHeight() - 6, arc, arc);

            g2.dispose();
            super.paintComponent(g);
        }
    }

    static class HoverEffect extends MouseAdapter {
        private final RoundedButton target;

        HoverEffect(RoundedButton target) {
            this.target = target;
        }

        @Override public void mouseEntered(MouseEvent e) { target.setHover(true); }
        @Override public void mouseExited(MouseEvent e) { target.setHover(false); }
    }
}