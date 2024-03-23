package project.server.views.components.manageBookPanel;

import javax.swing.*;
import java.awt.*;

public class PendingReturnPanel extends JPanel {
    public PendingReturnPanel() {
        setBackground(Color.BLUE);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Pending Return Requests");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JTextArea contentArea = new JTextArea();
        contentArea.setEditable(false);
        contentArea.setBackground(Color.BLUE);
        contentArea.setForeground(Color.WHITE);
        contentArea.setFont(new Font("Arial", Font.PLAIN, 16));
        contentArea.setPreferredSize(new Dimension(1000, 400));
        contentArea.setText("List of pending return requests goes here...");

        // Add the components directly to the panel
        add(titleLabel, BorderLayout.NORTH);
        add(contentArea, BorderLayout.CENTER);
    }
}