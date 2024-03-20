package project.server.views.components.homePanel;

import project.server.controller.ServerObserver;
import project.utilities.utilityClasses.FontFactory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class BookSummary extends JPanel {
    private final JPanel summaryPanel;
    private final ServerObserver serverController;

    public BookSummary(ServerObserver serverObserver) {
        serverController = serverObserver;
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 10, 10, 10));

        summaryPanel = new JPanel();
        summaryPanel.setLayout(new BoxLayout(summaryPanel, BoxLayout.Y_AXIS));

        summaryPanel.add(Box.createVerticalStrut(10));
        JTextArea summaryTitle = new JTextArea("\tSummary Report");
                JTextArea summary = new JTextArea("\n" +
                        "Number of Available Books: "
                        + serverController.getAvailableBooks().size()
                        + "\n\nNumber of Unavailable Books: "
                        + serverController.getUnavailableBooks().size()
                        + "\n\nNumber of Pending Books for Borrowing: "
                        + serverController.getPendingBorrowingBooks().size()
                        + "\n\nNumber of Pending Books for Returning: "
                        + serverController.getPendingReturningBooks().size()
                        + "\n\nNumber of Current Borrowed Books: "
                        + serverController.getCurrentBorrowedBooks().size()
                        + "\n\nNumber of Previous Borrowed Books: "
                        + serverController.getPreviousBorrowedBooks().size()
                );

        // configure summary title panel
        summaryTitle.setFont(FontFactory.newPoppinsBold(40));
        summaryTitle.setOpaque(false);
        summaryTitle.setEditable(false);
        summaryTitle.setWrapStyleWord(true);
        summaryTitle.setForeground(Color.black);

        // Edit summary panel text
        summary.setFont(FontFactory.newPoppinsDefault(25));
        summary.setOpaque(false);
        summary.setEditable(false);
        summary.setWrapStyleWord(true);
        summary.setForeground(Color.black);

        // Add the components to the summary panel
        summaryPanel.add(summaryTitle);
        summaryPanel.add(summary);
        JScrollPane scrollPane = new JScrollPane(summaryPanel);
        scrollPane.setPreferredSize(new Dimension(1000, 400));
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane, BorderLayout.SOUTH);
    }
}
