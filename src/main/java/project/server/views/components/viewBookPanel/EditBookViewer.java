package project.server.views.components.viewBookPanel;

import project.client.controller.ClientController;
import project.server.controller.ServerController;
import project.server.controller.ServerObserver;
import project.utilities.referenceClasses.Book;
import project.utilities.referenceClasses.Student;
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

        setPreferredSize(new Dimension(900, 600));
        setResizable(false);
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        picture = new Picture(book.getImagePath(), 150, 200);

        bookTitle = new FieldInput("Title:", new Dimension(300, 50), 20, 1, false);
        bookTitle.getTextField().setText(book.getBookTitle());

        bookAuthor = new FieldInput("Author:",new Dimension(300, 50), 20, 1, false);
        bookAuthor.getTextField().setText(book.getAuthor());

        bookGenre = new DropDown(new Dimension(300, 48), true, "Comedy", "Horror", "Fantasy", "Fiction", "Novel", "Sci-Fi",
                "Young", "Adult", "Historical", "Thriller", "Fantasy", "Science", "Romance", "Mystery");

        bookCopies = new FieldInput("Copies:", new Dimension(300, 50), 20, 1, false);

        shortDescription = new JTextArea(book.getShortDescription());
        shortDescription.setLineWrap(true);
        shortDescription.setWrapStyleWord(true);
        shortDescription.setEditable(true);
        JScrollPane textScrollPane = new JScrollPane(shortDescription);
        textScrollPane.setPreferredSize(new Dimension(300, 120));

        JPanel buttonPanel = getjPanel(book, serverObserver);

        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.insets = new Insets(5, 5, 5, 70);
        panel.add(picture, constraints);

        constraints.insets = new Insets(5, 5, 5, 30);
        constraints.gridx = 2;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.WEST;
        panel.add(bookTitle, constraints);


        constraints.gridy = 2;
        panel.add(bookAuthor, constraints);

        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridy = 3;
        constraints.gridx = 0;
        panel.add(bookGenre, constraints);

        constraints.gridx = 2;
        constraints.gridy = 3;
        panel.add(bookCopies, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 3;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        panel.add(textScrollPane, constraints);

        constraints.gridy = 5;
        constraints.weightx = 3.0;
        panel.add(buttonPanel, constraints);

        add(panel);
        pack();

        setLocationRelativeTo(null);

    }

    private static JPanel getjPanel(Book book, ServerObserver serverObserver) {
        JPanel buttonPanel = new JPanel();

        Button saveChanges =  new Button("Save Changes", 200, 80, FontFactory.newPoppinsDefault(14));
        saveChanges.setForeground(Color.WHITE);
        saveChanges.setBackground(ColorFactory.green());

//        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));


        buttonPanel.add(saveChanges);
        saveChanges.addActionListener(e -> serverObserver.editBook(book));
        return buttonPanel;
    }
    private void saveChangeAction(ActionEvent e, ServerObserver serverObserver) {
        // Retrieves input values from input fields
        String author = bookAuthor.getInput();
        String title = bookTitle.getInput();
        String copies = bookCopies.getInput();
        String genre = bookGenre.dropDownChoice();
        String description = shortDescription.getText();

        // Checks if any of the input fields are null or empty
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

        // Creates a new Book object with the edited details
        Book editedBook = new Book(book.getBookId(), title, author, genre, description, book.getImagePath(), c, book.getCurrentBorrowers(), book.getPreviousBorrowers(), book.getPendingBorrowers(), book.getPendingBookReturners());

        // Calls the server controller to edit the book
        serverObserver.editBook(editedBook);

    } // end of saveChangeAction method

}
