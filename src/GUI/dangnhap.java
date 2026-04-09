package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class dangnhap extends JFrame {

    public dangnhap() {
        setTitle("Login");
        setSize(420, 320);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JPanel root = new JPanel(new BorderLayout());
        root.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(root);

        JLabel title = new JLabel("Login", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        root.add(title, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(2, 2, 10, 10));
        JLabel userLabel = new JLabel("Username:");
        JTextField userField = new JTextField();
        JLabel passLabel = new JLabel("Password:");
        JPasswordField passField = new JPasswordField();

        form.add(userLabel);
        form.add(userField);
        form.add(passLabel);
        form.add(passField);
        root.add(form, BorderLayout.CENTER);

        JButton loginBtn = new JButton("Login");
        loginBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Login feature coming soon"));

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.CENTER));
        actions.add(loginBtn);
        root.add(actions, BorderLayout.SOUTH);

        setVisible(true);
    }
}