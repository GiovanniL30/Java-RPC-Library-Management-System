package project.server.views.components.manageBookPanel;

import project.server.controller.ServerObserver;

import javax.swing.*;
import java.awt.*;

public class BorrowedPanel extends JPanel {
    public BorrowedPanel() {
        setBackground(Color.BLACK);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Borrowed Books");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JTextArea contentArea = new JTextArea();
        contentArea.setEditable(false);
        contentArea.setBackground(Color.BLACK);
        contentArea.setForeground(Color.WHITE);
        contentArea.setFont(new Font("Arial", Font.PLAIN, 16));
        contentArea.setText("List of borrowed books goes here...");
        contentArea.setPreferredSize(new Dimension(1000, 400));
        // Create a holder panel to center the content
        JPanel holder = new JPanel(new BorderLayout());
        holder.setBackground(Color.BLACK);
        holder.add(titleLabel, BorderLayout.NORTH);
        holder.add(contentArea, BorderLayout.CENTER);

        add(holder, BorderLayout.CENTER);
    }
}