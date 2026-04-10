package GUI;

import Fancy.Theme;
import Logic.logic;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class dangnhap extends JFrame {

    public dangnhap() {
        Theme theme = logic.getTheme();
        Color BG = theme.background;
        Color PRIMARY = theme.primary;
        Color PRIMARY_DARK = theme.primaryDark;
        Color TEXT = theme.text;
        Color ACCENT = theme.accent;

        setTitle("Player Login");
        setSize(820, 560);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        GradientPanel root = new GradientPanel(BG, PRIMARY_DARK.darker());
        root.setLayout(new BorderLayout());
        root.setBorder(new EmptyBorder(28, 36, 36, 36));
        setContentPane(root);

        JPanel header = new JPanel();
        header.setOpaque(false);
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        JLabel title = new JLabel("WELCOME BACK");
        title.setFont(new Font("SansSerif", Font.BOLD, 32));
        title.setForeground(ACCENT);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel subtitle = new JLabel("Sign in to continue your game");
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 16));
        subtitle.setForeground(TEXT);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        header.add(title);
        header.add(Box.createVerticalStrut(6));
        header.add(subtitle);
        root.add(header, BorderLayout.NORTH);

        JPanel content = new JPanel(new GridBagLayout());
        content.setOpaque(false);
        GridBagConstraints gbcRoot = new GridBagConstraints();
        gbcRoot.gridx = 0;
        gbcRoot.gridy = 0;
        gbcRoot.insets = new Insets(12, 0, 0, 0);
        gbcRoot.fill = GridBagConstraints.BOTH;
        gbcRoot.weightx = 1;
        gbcRoot.weighty = 1;

        RoundedPanel leftInfo = new RoundedPanel(24);
        leftInfo.setBackground(new Color(0, 0, 0, 110));
        leftInfo.setBorder(new EmptyBorder(24, 24, 24, 24));
        leftInfo.setLayout(new BoxLayout(leftInfo, BoxLayout.Y_AXIS));

        JLabel infoTitle = new JLabel("New here?");
        infoTitle.setFont(new Font("SansSerif", Font.BOLD, 20));
        infoTitle.setForeground(ACCENT);
        infoTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel infoText = new JLabel("Create an account to start playing.");
        infoText.setFont(new Font("SansSerif", Font.PLAIN, 14));
        infoText.setForeground(TEXT);
        infoText.setAlignmentX(Component.LEFT_ALIGNMENT);

        JComponent signupBtn = outlineButton("Create Account", PRIMARY, TEXT, ACCENT, () -> {
            Signup signup = new Signup();
            signup.setVisible(true);
            dispose();
        });
        signupBtn.setAlignmentX(Component.LEFT_ALIGNMENT);

        leftInfo.add(infoTitle);
        leftInfo.add(Box.createVerticalStrut(8));
        leftInfo.add(infoText);
        leftInfo.add(Box.createVerticalStrut(18));
        leftInfo.add(signupBtn);
        leftInfo.add(Box.createVerticalGlue());

        RoundedPanel formCard = new RoundedPanel(24);
        formCard.setBackground(new Color(0, 0, 0, 150));
        formCard.setBorder(new EmptyBorder(28, 28, 28, 28));
        formCard.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(6, 0, 6, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        JLabel userLabel = label("Username", TEXT);
        JTextField userField = field(TEXT);
        JLabel passLabel = label("Password", TEXT);
        JPasswordField passField = passwordField(TEXT);

        formCard.add(userLabel, gbc);
        gbc.gridy++;
        formCard.add(userField, gbc);
        gbc.gridy++;
        formCard.add(passLabel, gbc);
        gbc.gridy++;
        formCard.add(passField, gbc);

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.CENTER, 14, 8));
        actions.setOpaque(false);

        JComponent continueBtn = actionButton("Continue", PRIMARY, TEXT, ACCENT, () -> {
            MainMenu menu = new MainMenu();
            menu.setVisible(true);
            dispose();
        });
        JComponent exitBtn = outlineButton("Exit", PRIMARY_DARK, TEXT, ACCENT, () -> System.exit(0));

        actions.add(continueBtn);
        actions.add(exitBtn);

        JPanel formWrap = new JPanel(new BorderLayout());
        formWrap.setOpaque(false);
        formWrap.add(formCard, BorderLayout.CENTER);
        formWrap.add(actions, BorderLayout.SOUTH);

        JPanel columns = new JPanel(new GridBagLayout());
        columns.setOpaque(false);
        GridBagConstraints col = new GridBagConstraints();
        col.gridy = 0;
        col.fill = GridBagConstraints.BOTH;
        col.weighty = 1;

        col.gridx = 0;
        col.weightx = 0.42;
        col.insets = new Insets(0, 0, 0, 18);
        columns.add(leftInfo, col);

        col.gridx = 1;
        col.weightx = 0.58;
        col.insets = new Insets(0, 0, 0, 0);
        columns.add(formWrap, col);

        content.add(columns, gbcRoot);
        root.add(content, BorderLayout.CENTER);

        setVisible(true);
    }

    private static JLabel label(String text, Color fg) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", Font.BOLD, 16));
        label.setForeground(fg);
        return label;
    }

    private static JTextField field(Color fg) {
        JTextField field = new JTextField();
        field.setFont(new Font("SansSerif", Font.PLAIN, 16));
        field.setForeground(fg);
        field.setBackground(new Color(255, 255, 255, 235));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(255, 255, 255, 90), 1),
                new EmptyBorder(12, 12, 12, 12)));
        return field;
    }

    private static JPasswordField passwordField(Color fg) {
        JPasswordField field = new JPasswordField();
        field.setFont(new Font("SansSerif", Font.PLAIN, 16));
        field.setForeground(fg);
        field.setBackground(new Color(255, 255, 255, 235));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(255, 255, 255, 90), 1),
                new EmptyBorder(12, 12, 12, 12)));
        return field;
    }

    private static JComponent actionButton(String text, Color base, Color fg, Color glow, Runnable action) {
        JButton btn = new JButton(text);
        btn.setForeground(fg);
        btn.setFont(new Font("SansSerif", Font.BOLD, 16));
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        RoundedButton wrap = new RoundedButton(18, base, glow);
        wrap.setBorder(new EmptyBorder(10, 28, 10, 28));
        wrap.setLayout(new BorderLayout());
        wrap.add(btn, BorderLayout.CENTER);

        btn.addActionListener(e -> action.run());
        btn.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) { wrap.setHover(true); }
            @Override public void mouseExited(MouseEvent e) { wrap.setHover(false); }
        });

        return wrap;
    }

    private static JComponent outlineButton(String text, Color base, Color fg, Color glow, Runnable action) {
        JButton btn = new JButton(text);
        btn.setForeground(fg);
        btn.setFont(new Font("SansSerif", Font.BOLD, 16));
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        RoundedButton wrap = new RoundedButton(18, new Color(0, 0, 0, 90), glow);
        wrap.setBorder(new EmptyBorder(10, 24, 10, 24));
        wrap.setLayout(new BorderLayout());
        wrap.add(btn, BorderLayout.CENTER);

        btn.addActionListener(e -> action.run());
        btn.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) { wrap.setHover(true); }
            @Override public void mouseExited(MouseEvent e) { wrap.setHover(false); }
        });

        return wrap;
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
}