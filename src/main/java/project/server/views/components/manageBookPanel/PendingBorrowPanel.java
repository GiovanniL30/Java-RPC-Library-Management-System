package project.server.views.components.manageBookPanel;

import javax.swing.*;
import java.awt.*;

public class PendingBorrowPanel extends JPanel {
    public PendingBorrowPanel() {
        JPanel holder = new JPanel();
        holder.setBackground(Color.WHITE);
        holder.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Pending Borrow Requests");
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JTextArea contentArea = new JTextArea();
        contentArea.setEditable(false);
        contentArea.setBackground(Color.WHITE);
        contentArea.setForeground(Color.BLACK);
        contentArea.setFont(new Font("Arial", Font.PLAIN, 16));
        contentArea.setText("List of pending borrow requests goes here...");

        holder.add(titleLabel, BorderLayout.NORTH);
        holder.add(contentArea, BorderLayout.CENTER);

        setLayout(new BorderLayout());
        add(holder);
    }
}

