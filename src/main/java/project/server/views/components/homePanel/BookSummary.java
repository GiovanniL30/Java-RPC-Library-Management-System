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
        JLabel unavailableBooks = new JLabel("Number of Unavailable Books: " + serverController.getUnavailableBooks().size());
        JLabel pendingBorrowingBooks = new JLabel("Number of Pending Books for Borrowing: " + serverController.getPendingBorrowingBooks().size());
        JLabel pendingReturningBooks = new JLabel("Number of Pending Books for Returning: " + serverController.getPendingReturningBooks().size());
        JLabel currentBorrowedBooks = new JLabel("Number of Current Borrowed Books: " + serverController.getCurrentBorrowedBooks().size());
        JLabel previousBorrowedBooks = new JLabel("Number of Previous Borrowed Books: " + serverController.getPreviousBorrowedBooks().size());
        summaryPanel.add(Box.createVerticalStrut(10));
        summaryPanel.setPreferredSize(new Dimension(1000,400));

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

        header.setFont(FontFactory.newPoppinsBold(30));
        availableBooks.setFont(FontFactory.newPoppinsDefault(25));
        unavailableBooks.setFont(FontFactory.newPoppinsDefault(25));
        pendingBorrowingBooks.setFont(FontFactory.newPoppinsDefault(25));
        pendingReturningBooks.setFont(FontFactory.newPoppinsDefault(25));
        currentBorrowedBooks.setFont(FontFactory.newPoppinsDefault(25));
        previousBorrowedBooks.setFont(FontFactory.newPoppinsDefault(25));

        header.setAlignmentX(CENTER_ALIGNMENT);
        availableBooks.setAlignmentX(CENTER_ALIGNMENT);
        unavailableBooks.setAlignmentX(CENTER_ALIGNMENT);
        pendingBorrowingBooks.setAlignmentX(CENTER_ALIGNMENT);
        pendingReturningBooks.setAlignmentX(CENTER_ALIGNMENT);
        currentBorrowedBooks.setAlignmentX(CENTER_ALIGNMENT);
        previousBorrowedBooks.setAlignmentX(CENTER_ALIGNMENT);


        summaryPanel.add(header);
        summaryPanel.add(availableBooks);
        summaryPanel.add(unavailableBooks);
        summaryPanel.add(pendingBorrowingBooks);
        summaryPanel.add(pendingReturningBooks);
        summaryPanel.add(currentBorrowedBooks);
        summaryPanel.add(previousBorrowedBooks);

        add(summaryPanel, BorderLayout.CENTER);
    }
}
