package project.server.views.components.manageBookPanel;

import project.server.controller.ServerObserver;

import javax.swing.*;
import java.awt.*;

public class BorrowedPanel extends JPanel {
    public BorrowedPanel() {
        JPanel holder = new JPanel();
        holder.setBackground(Color.BLACK);
        holder.setLayout(new BorderLayout());

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

        holder.add(titleLabel, BorderLayout.NORTH);
        holder.add(contentArea, BorderLayout.CENTER);

        setLayout(new BorderLayout());
        add(holder);
    }
}