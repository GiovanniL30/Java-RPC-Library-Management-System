package project.server.views.components.homePanel;

import project.server.controller.ServerObserver;
import project.utilities.utilityClasses.FontFactory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.jar.JarEntry;

public class BookSummary extends JPanel {
    private final JPanel summaryPanel;
    private final ServerObserver serverController;

    public BookSummary(ServerObserver serverObserver) {
        serverController = serverObserver;
        setLayout(new BorderLayout());

        summaryPanel = new JPanel();
        summaryPanel.setLayout(new BoxLayout(summaryPanel, BoxLayout.Y_AXIS));

        JLabel header = new JLabel("Summary Report");
        JLabel availableBooks = new JLabel("Number of Available Books: " + serverController.getAvailableBooks().size());

//        summaryPanel.add(Box.createVerticalStrut(10));
//        JTextArea summaryTitle = new JTextArea("\tSummary Report");
//                JTextArea summary = new JTextArea("\n" +
//                        "Number of Available Books: "
//                        + serverController.getAvailableBooks().size()
//                        + "\n\nNumber of Unavailable Books: "
//                        + serverController.getUnavailableBooks().size()
//                        + "\n\nNumber of Pending Books for Borrowing: "
//                        + serverController.getPendingBorrowingBooks().size()
//                        + "\n\nNumber of Pending Books for Returning: "
//                        + serverController.getPendingReturningBooks().size()
//                        + "\n\nNumber of Current Borrowed Books: "
//                        + serverController.getCurrentBorrowedBooks().size()
//                        + "\n\nNumber of Previous Borrowed Books: "
//                        + serverController.getPreviousBorrowedBooks().size()
//                );




        JScrollPane scrollPane = new JScrollPane(summaryPanel);
        scrollPane.setPreferredSize(new Dimension(1000, 400));
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane, BorderLayout.CENTER);
    }
}
