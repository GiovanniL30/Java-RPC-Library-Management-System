package project.server.views.components.homePanel;

import project.server.controller.ServerObserver;
import project.utilities.utilityClasses.FontFactory;

import javax.swing.*;
import java.awt.*;

public class BookSummary extends JPanel {
    private final JPanel summaryPanel;
    private final ServerObserver serverController;

    public BookSummary(ServerObserver serverObserver) {
        serverController = serverObserver;
        setLayout(new BorderLayout());

        summaryPanel = new JPanel();
        JTextArea summaryTitle = new JTextArea("\tSummary Report");
                JTextArea summary = new JTextArea("\n" +
                        "Number of Available Books: "
                        + serverController.viewAvailableBooks().size()
                        + "\n\nNumber of Unavailable Books: "
                        + serverController.viewUnavailableBooks().size()
                        + "\n\nNumber of Pending Books for Borrowing: "
                        + serverController.viewPendingBorrowingBooks().size()
                        + "\n\nNumber of Pending Books for Returning: "
                        + serverController.viewPendingReturningBooks().size()
                        + "\n\nNumber of Current Borrowed Books: "
                        + serverController.viewCurrentBorrowedBooks().size()
                        + "\n\nNumber of Previous Borrowed Books: "
                        + serverController.viewPreviousBorrowedBooks().size()
                );

        // configure summary title panel
        summaryTitle.setFont(FontFactory.newPoppinsBold(40));
        summaryTitle.setOpaque(false);
        summaryTitle.setEditable(false);
        summaryTitle.setWrapStyleWord(true);
        summaryTitle.setForeground(Color.white);
        summaryTitle.setTabSize(3);

        // Edit summary panel text
        summary.setFont(FontFactory.newPoppinsDefault(35));
        summary.setOpaque(false);
        summary.setEditable(false);
        summary.setWrapStyleWord(true);
        summary.setForeground(Color.white);

        // Add the components to the summary panel
        summaryPanel.add(summaryTitle);
        summaryPanel.add(summary);
        JScrollPane scrollPane = new JScrollPane(summaryPanel);
        scrollPane.setPreferredSize(new Dimension(1000, 720));
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane, BorderLayout.SOUTH);
    }
}
