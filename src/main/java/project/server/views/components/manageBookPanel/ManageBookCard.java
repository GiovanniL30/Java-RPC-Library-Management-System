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

        // Create components for the card
        bookPicture = new Picture(book.getImagePath(), 100, 160);
        borrowerName = new JLabel("Student Name: " + student.getAccount().getFirstName() + " " + student.getAccount().getLastName());
        bookTitle = new JLabel(book.getBookTitle());

        acceptBook = new Button("Accept", 100, 50, FontFactory.newPoppinsDefault(16));
        acceptBook.setForeground(Color.white);
        acceptBook.setBackground(ColorFactory.green());

        cancelBook = new Button("Cancel", 100, 50, FontFactory.newPoppinsDefault(16));
        cancelBook.setForeground(Color.white);
        cancelBook.setBackground(ColorFactory.red());

        retrieveBook = new Button("Retrieve Book", 200, 50, FontFactory.newPoppinsDefault(16));
        retrieveBook.setForeground(Color.white);
        retrieveBook.setBackground(ColorFactory.blue());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        // Add appropriate buttons based on borrow status
        if (isBorrowed) {
            buttonPanel.add(retrieveBook);
        } else {
            buttonPanel.add(cancelBook);
            buttonPanel.add(acceptBook);
        }

        // Set bounds for components
        bookPicture.setBounds(10, 10, 95, 160);
        borrowerName.setBounds(110, 10, 200, 30);
        bookTitle.setBounds(110, 40, 200, 30);
        buttonPanel.setBounds(110, 110, 210, 60);

        // Add components to the card
        add(bookPicture);
        add(borrowerName);
        add(bookTitle);
        add(buttonPanel);

//        // Action listeners
//        acceptBook.addActionListener(e -> {
//            serverObserver.acceptBook(student.getAccount().getAccountId(), book.getBookId(), book.getBookTitle());
//            serverObserver.changeFrame(ServerPanels.MANAGE_BOOKS);
//        });
//
//        cancelBook.addActionListener(e -> {
//            serverObserver.cancelPending(student.getAccount().getAccountId(), book.getBookId(), book.getBookTitle());
//            serverObserver.changeFrame(ServerPanels.MANAGE_BOOKS);
//        });
//
//        retrieveBook.addActionListener(e -> {
//            serverObserver.retrieveBook(student.getAccount().getAccountId(), book.getBookId(), book.getBookTitle());
//            serverObserver.changeFrame(ServerPanels.MANAGE_BOOKS);
//        }); // end of action listeners
    } // end of BookCard constructor
}

