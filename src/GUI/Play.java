import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Play extends JFrame {
    // Existing code... 

    private JButton backToMenuButton;

    public Play() {
        // Existing constructor code...

        // Create Back to Menu button
        backToMenuButton = new JButton("Back to Menu");
        backToMenuButton.setFocusable(false);
        backToMenuButton.setPreferredSize(new Dimension(100, 30));
        backToMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Dispose current Play window and open MainMenu
                dispose();
                new MainMenu();
            }
        });

        // Add the button to the bottom panel alongside existing buttons
        bottomPanel.add(backToMenuButton);
        // Existing code for adding other buttons...
    }

    // Existing methods...
}