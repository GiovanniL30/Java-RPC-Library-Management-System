package midproject.server.views.components.viewBookPanel;

import midproject.server.controller.ServerObserver;
import midproject.utilities.referenceClasses.Book;
import midproject.utilities.referenceClasses.PrevBorrower;
import midproject.utilities.utilityClasses.ColorFactory;
import midproject.utilities.utilityClasses.FontFactory;
import midproject.utilities.viewComponents.Button;
import midproject.utilities.viewComponents.Picture;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Represents a card for displaying book details and actions in the view books panel.
 */

public class ViewBooksCard extends JPanel {
    private Button editBookButton = new Button("Edit", 130, 50, FontFactory.newPoppinsDefault(13));
    private Button deleteBookButton = new Button("Delete", 130, 50, FontFactory.newPoppinsDefault(13));
    private Button prevOwnersButton = new Button("Previous Owners", 130, 50, FontFactory.newPoppinsDefault(13));
    private Button currentOwnersButton = new Button("Current Owners", 130, 50, FontFactory.newPoppinsDefault(13));

    /**
     * Constructs a ViewBooksCard.
     * @param book           The book to display.
     * @param serverObserver The server observer for interacting with server functionalities.
     */
    public ViewBooksCard(Book book, ServerObserver serverObserver) {
        setSize(new Dimension(300, 200));
        setLayout(new GridBagLayout());
        setBorder(new EmptyBorder(0, 10, 0, 20));
        setBackground(Color.white);

        Picture bookPicture = new Picture(book.getImagePath(), 130, 200);
        bookPicture.setBackground(Color.white);

        JLabel bookTitle = new JLabel(book.getBookTitle());
        bookTitle.setFont(FontFactory.newPoppinsBold(18));
        JLabel bookCopies = new JLabel("Copies: " + book.getCopies());
        bookCopies.setFont(FontFactory.newPoppinsDefault(12));

        editBookButton.setForeground(Color.white);
        editBookButton.setBackground(ColorFactory.blue());

        deleteBookButton.setForeground(Color.white);
        deleteBookButton.setBackground(ColorFactory.red());

        prevOwnersButton.setForeground(Color.white);
        prevOwnersButton.setBackground(ColorFactory.green());

        JPanel bookPanel = new JPanel();
        JPanel bookInfoPanel = new JPanel();
        JPanel bookTitlePanel = new JPanel();
        JPanel bookCopiesPanel = new JPanel();
        JPanel buttonsPanel = new JPanel();
        JPanel prevButtonPanel = new JPanel();

        bookInfoPanel.setLayout(new BoxLayout(bookInfoPanel, BoxLayout.Y_AXIS));
        bookInfoPanel.setBackground(Color.white);

        bookTitlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        bookTitlePanel.setBackground(Color.white);

        bookCopiesPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        bookCopiesPanel.setBackground(Color.white);

        bookTitlePanel.add(bookTitle);
        bookCopiesPanel.add(bookCopies);

        buttonsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        buttonsPanel.setBackground(Color.white);

        buttonsPanel.add(editBookButton);
        buttonsPanel.add(deleteBookButton);

        prevButtonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        prevButtonPanel.setBackground(Color.white);
        prevButtonPanel.add(prevOwnersButton);
        prevButtonPanel.add(currentOwnersButton);

        bookInfoPanel.add(bookTitlePanel);
        bookInfoPanel.add(bookCopiesPanel);
        bookInfoPanel.add(buttonsPanel);
        bookInfoPanel.add(prevButtonPanel);

        bookPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        bookPanel.setBackground(Color.white);
        bookPanel.add(bookPicture);
        bookPanel.add(bookInfoPanel);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 2.0;
        constraints.fill = 2;
        add(bookPanel, constraints);

        // ACTION LISTENERS FOR THE BUTTONS
        editBookButton.addActionListener(e -> {
           serverObserver.openBookEditor(book);
        });

        deleteBookButton.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this book?", "Book delete confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, null);
            if (choice == JOptionPane.YES_OPTION) {
               serverObserver.deleteBook(book);
            }
        });

        prevOwnersButton.addActionListener(e -> {
            JPanel prevOwnersPanel = new JPanel();
            prevOwnersPanel.setLayout(new BoxLayout(prevOwnersPanel, BoxLayout.Y_AXIS));
            prevOwnersPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding

            for (PrevBorrower prevBorrower : book.getPrevBookBorrowers()) {
                JLabel prevOwnerLabel = new JLabel("Name: " + prevBorrower.getName());
                JLabel returnDateLabel = new JLabel("Return Date: " + prevBorrower.getReturnDate());
                prevOwnerLabel.setFont(FontFactory.newPoppinsDefault(13));
                returnDateLabel.setFont(FontFactory.newPoppinsDefault(13));
                prevOwnerLabel.setHorizontalAlignment(JLabel.LEFT); // Align text to the left
                prevOwnersPanel.add(prevOwnerLabel);
                prevOwnersPanel.add(returnDateLabel);
            }
            JScrollPane scrollPane = new JScrollPane(prevOwnersPanel);
            scrollPane.setPreferredSize(new Dimension(300, 200)); // Set the preferred size of the scroll pane
            scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            JOptionPane.showMessageDialog(null, scrollPane, "Previous owners", JOptionPane.INFORMATION_MESSAGE);
        });

        currentOwnersButton.addActionListener(e -> {
            JPanel currentOwnersPanel = new JPanel();
            currentOwnersPanel.setLayout(new BoxLayout(currentOwnersPanel, BoxLayout.Y_AXIS));
            currentOwnersPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding

            for (String currentBorrower : book.getCurrentBorrowers()) {
                JLabel currentOwnerLabel = new JLabel("Name: " + currentBorrower);
                currentOwnerLabel.setFont(FontFactory.newPoppinsDefault(13));
                currentOwnerLabel.setHorizontalAlignment(JLabel.LEFT); // Align text to the left
                currentOwnersPanel.add(currentOwnerLabel);
            }
            JScrollPane scrollPane = new JScrollPane(currentOwnersPanel);
            scrollPane.setPreferredSize(new Dimension(300, 200)); // Set the preferred size of the scroll pane
            scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            JOptionPane.showMessageDialog(null, scrollPane, "Current owners", JOptionPane.INFORMATION_MESSAGE);
        });
    } // end of ViewBooksCard Constructor
} // end of ViewBooksCard class

