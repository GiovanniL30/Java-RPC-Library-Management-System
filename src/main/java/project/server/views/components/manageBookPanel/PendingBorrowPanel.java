package project.server.views.components.manageBookPanel;

import javax.swing.*;
import java.awt.*;

public class PendingBorrowPanel extends JPanel {
    public PendingBorrowPanel() {
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Pending Borrow Requests");
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JTextArea contentArea = new JTextArea();
        contentArea.setEditable(false);
        contentArea.setBackground(Color.WHITE);
        contentArea.setForeground(Color.BLACK);
        contentArea.setFont(new Font("Arial", Font.PLAIN, 16));
        contentArea.setPreferredSize(new Dimension(1000, 400));
        contentArea.setText("List of pending borrow requests goes here...");

        // Add the components directly to the panel
        add(titleLabel, BorderLayout.NORTH);
        add(contentArea, BorderLayout.CENTER);
    }
}

