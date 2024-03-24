package project.server.views.components.manageBookPanel;

import project.server.controller.ServerObserver;
import project.utilities.referenceClasses.Book;
import project.utilities.referenceClasses.Student;
import project.utilities.utilityClasses.ColorFactory;
import project.utilities.utilityClasses.FontFactory;
import project.utilities.viewComponents.Picture;
import project.utilities.viewComponents.Button;
import javax.swing.*;
import java.awt.*;

public class ManageBookCard extends JPanel{
    private final Picture bookPicture;
    private final JLabel borrowerName;
    private final JLabel bookTitle;
    private final Button acceptBook;
    private final Button cancelBook;
    private final Button retrieveBook;
    private final ServerObserver serverObserver;

    ManageBookCard(Book book, Student student, boolean isBorrowed, ServerObserver serverObserver) {
        this.serverObserver = serverObserver;
        setLayout(null);
        setPreferredSize(new Dimension(430, 170));
        setBackground(Color.white);

        // Create components for the card
        bookPicture = new Picture(book.getImagePath(), 100, 160);
        borrowerName = new JLabel("Student Name: " + student.getAccount().getFirstName() + " " + student.getAccount().getLastName());
        borrowerName.setFont(FontFactory.newPoppinsBold(14));
        bookTitle = new JLabel(book.getBookTitle());
        bookTitle.setFont(FontFactory.newPoppinsBold(14));

        acceptBook = new Button("Accept", 100, 50, FontFactory.newPoppinsDefault(13));
        acceptBook.setForeground(Color.white);
        acceptBook.setBackground(ColorFactory.green());

        cancelBook = new Button("Cancel", 100, 50, FontFactory.newPoppinsDefault(13));
        cancelBook.addActionListener(e -> {
            serverObserver.cancelPending(book, student);
        });
        cancelBook.setForeground(Color.white);
        cancelBook.setBackground(ColorFactory.red());

        retrieveBook = new Button("Retrieve Book", 200, 50, FontFactory.newPoppinsDefault(13));
        retrieveBook.setForeground(Color.white);
        retrieveBook.setBackground(ColorFactory.blue());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBackground(Color.white);

        // Add appropriate buttons based on borrow status
        if (isBorrowed) {
            buttonPanel.add(retrieveBook);
        } else {
            buttonPanel.add(cancelBook);
            buttonPanel.add(acceptBook);
        }

        // Set bounds for components
        bookPicture.setBounds(10, 10, 100, 160);
        borrowerName.setBounds(120, 10, 220, 30);
        bookTitle.setBounds(120, 40, 220, 30);
        buttonPanel.setBounds(120, 110, 210, 60);

        // Add components to the card
        add(bookPicture);
        add(borrowerName);
        add(bookTitle);
        add(buttonPanel);

        // Action listeners
        acceptBook.addActionListener(e -> {
            serverObserver.acceptBook(book, student);
        });

        cancelBook.addActionListener(e -> {

        });

        retrieveBook.addActionListener(e -> {

        }); // end of action listeners
    } // end of Card constructor
}

