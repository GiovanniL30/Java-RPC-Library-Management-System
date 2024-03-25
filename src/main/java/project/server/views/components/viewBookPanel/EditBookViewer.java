package project.server.views.components.viewBookPanel;

import project.server.controller.ServerObserver;
import project.utilities.referenceClasses.Book;
import project.utilities.utilityClasses.ColorFactory;
import project.utilities.utilityClasses.FontFactory;
import project.utilities.utilityClasses.UtilityMethods;
import project.utilities.viewComponents.Button;
import project.utilities.viewComponents.DropDown;
import project.utilities.viewComponents.FieldInput;
import project.utilities.viewComponents.Picture;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class EditBookViewer extends JDialog {

    private Book book;
    private final Picture picture;
    private final FieldInput bookTitle;
    private final FieldInput bookAuthor;
    private final FieldInput bookCopies;
    private final DropDown bookGenre;
    private final JTextArea shortDescription;

    public EditBookViewer(Frame frame, Book book, ServerObserver serverObserver) {
        super(frame, book.getBookTitle(), true);
        setBackground(Color.WHITE);

        setPreferredSize(new Dimension(900, 600));
        setResizable(false);
        GridBagConstraints constraints = new GridBagConstraints();

        JPanel picturePanel = new JPanel(new GridBagLayout());
        picturePanel.setBackground(Color.WHITE);
        JPanel detailsPanel = new JPanel();
        detailsPanel.setBackground(Color.WHITE);


        picture = new Picture(book.getImagePath(), 300, 550);
        picture.setBackground(Color.WHITE);

        bookTitle = new FieldInput("Title:", new Dimension(400, 50), 20, 1, false);
        bookTitle.getTextField().setText(book.getBookTitle());


        bookAuthor = new FieldInput("Author:",new Dimension(400, 50), 20, 1, false);
        bookAuthor.getTextField().setText(book.getAuthor());

        bookGenre = new DropDown(new Dimension(400, 48), true, "Comedy", "Horror", "Fantasy", "Fiction", "Novel", "Sci-Fi",
                "Young", "Adult", "Historical", "Thriller", "Fantasy", "Science", "Romance", "Mystery");

        bookCopies = new FieldInput("Copies:", new Dimension(400, 50), 20, 1, false);
        bookCopies.getTextField().setText(book.getCopies()+"");

        shortDescription = new JTextArea(book.getShortDescription());
        shortDescription.setLineWrap(true);
        shortDescription.setWrapStyleWord(true);
        shortDescription.setEditable(true);
        JScrollPane descPane = new JScrollPane(shortDescription);
        descPane.setPreferredSize(new Dimension(400, 120));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        Button saveChanges =  new Button("Save Changes", 200, 50, FontFactory.newPoppinsDefault(14));
        saveChanges.setForeground(Color.WHITE);
        saveChanges.setBackground(ColorFactory.green());

        Button cancel =  new Button("Cancel", 200, 50, FontFactory.newPoppinsDefault(14));
        cancel.setForeground(Color.black);
        cancel.setBackground(Color.white);


        buttonPanel.add(cancel);
        buttonPanel.add(saveChanges);

        constraints.gridx = 0;
        constraints.gridy = 0;
        detailsPanel.add(bookTitle, constraints);

        constraints.gridy = 1;
        detailsPanel.add(bookAuthor, constraints);

        constraints.gridy = 2;
        detailsPanel.add(bookGenre, constraints);

        constraints.gridy = 3;
        detailsPanel.add(bookCopies, constraints);

        constraints.gridy = 4;
        detailsPanel.add(descPane, constraints);

        constraints.gridy = 5;
        detailsPanel.add(buttonPanel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.BOTH;
        picturePanel.add(picture, constraints);

        constraints.gridx = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        picturePanel.add(detailsPanel, constraints);


        add(picturePanel, BorderLayout.CENTER);

        pack();

        setLocationRelativeTo(null);

        cancel.addActionListener( e -> this.setVisible(false));
        saveChanges.addActionListener(e -> {

            String author = bookAuthor.getInput();
            String title = bookTitle.getInput();
            String copies = bookCopies.getInput();
            String genre = bookGenre.dropDownChoice();
            String description = shortDescription.getText();


            if (UtilityMethods.haveNullOrEmpty(author, title, copies, description)) {
                return;
            }

            int c;

            try {
                // Parses the input for the number of copies into an integer
                c = Integer.parseInt(copies);
                // Checks if the number of copies is negative
                if (c < 0) {
                    // Displays an error message for negative values
                    bookCopies.enableError("Negative value not allowed");
                    // Clears the text field for number of copies
                    bookCopies.getTextField().setText("");
                    return;
                }

            } catch (NumberFormatException exception) {
                // Displays an error message for invalid integer input
                bookCopies.enableError("Please enter a valid Integer Value");
                return;
            }


            Book editedBook = new Book(book.getBookId(), title, author, genre, description, book.getImagePath(), c, book.getCurrentBorrowers(), book.getPrevBookBorrowers(), book.getPendingBorrowers(), book.getPendingBookReturners());
            serverObserver.editBook(editedBook);
            this.setVisible(false);
        });

    }


}
