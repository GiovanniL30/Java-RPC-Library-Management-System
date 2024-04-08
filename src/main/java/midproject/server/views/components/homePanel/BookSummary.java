package midproject.server.views.components.homePanel;

import midproject.server.controller.ServerObserver;
import midproject.utilities.referenceClasses.Book;
import midproject.utilities.utilityClasses.FontFactory;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 * Represents a panel displaying a summary report of book information.
 */

public class BookSummary extends JPanel {
    private final JPanel summaryPanel;
    private final ServerObserver serverController;

    /**
     * Constructs a BookSummary panel with the specified server observer.
     * @param serverObserver The server observer used to retrieve book information.
     */
    public BookSummary(ServerObserver serverObserver) {
        serverController = serverObserver;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        summaryPanel = new JPanel();
        summaryPanel.setBackground(Color.WHITE);
        summaryPanel.setLayout(new BoxLayout(summaryPanel, BoxLayout.Y_AXIS));

        // Retrieve book information from the server
        LinkedList<Book> books = serverController.getBooks();

        // Calculate summary statistics
        int numberOfAvailableBooks = (int) books.stream().filter(book ->  book.getCopies() > 0).count();
        int numberOfUnAvailableBooks = (int) books.stream().filter(book ->  book.getCopies() <= 0).count();
        int numberOfPendingBooks = (int) books.stream().filter(book -> !book.getPendingBookReturners().isEmpty()).count();
        int numberOfPendingReturningBooks = (int) books.stream().filter(book -> !book.getPendingBookReturners().isEmpty()).count();
        int numberOfBorrowedBooks = (int) books.stream().filter(book -> !book.getCurrentBorrowers().isEmpty()).count();
        int numberOfPreviousBooks = (int) books.stream().filter(book -> !book.getPrevBookBorrowers().isEmpty()).count();

        // Create and format summary labels
        JLabel header = new JLabel("Summary Report");
        JLabel availableBooks = new JLabel("Number of Available Books: " + numberOfAvailableBooks);
        JLabel unavailableBooks = new JLabel("Number of Unavailable Books: " + numberOfUnAvailableBooks);
        JLabel pendingBorrowingBooks = new JLabel("Number of Pending Books for Borrowing: " + numberOfPendingBooks);
        JLabel pendingReturningBooks = new JLabel("Number of Pending Books for Returning: " + numberOfPendingReturningBooks);
        JLabel currentBorrowedBooks = new JLabel("Number of Current Borrowed Books: " + numberOfBorrowedBooks);
        JLabel previousBorrowedBooks = new JLabel("Number of Previous Borrowed Books: " + numberOfPreviousBooks);
        summaryPanel.add(Box.createVerticalStrut(10));
        summaryPanel.setPreferredSize(new Dimension(1000,400));

        // Set fonts and alignments
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

        // Add labels to the summary panel
        summaryPanel.add(header);
        summaryPanel.add(availableBooks);
        summaryPanel.add(unavailableBooks);
        summaryPanel.add(pendingBorrowingBooks);
        summaryPanel.add(pendingReturningBooks);
        summaryPanel.add(currentBorrowedBooks);
        summaryPanel.add(previousBorrowedBooks);

        // Add the summary panel to the BookSummary panel
        add(summaryPanel, BorderLayout.CENTER);
    } // end BookSummary constructor
} // end of BookSummary class
