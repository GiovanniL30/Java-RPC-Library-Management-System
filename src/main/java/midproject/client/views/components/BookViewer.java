package midproject.client.views.components;

import midproject.client.controller.ClientController;
import midproject.utilities.referenceClasses.Book;
import midproject.utilities.referenceClasses.Student;
import midproject.utilities.utilityClasses.ColorFactory;
import midproject.utilities.utilityClasses.FontFactory;
import  midproject.utilities.viewComponents.Button;
import midproject.utilities.viewComponents.Picture;

import javax.swing.*;
import java.awt.*;

/**
 * Class that shows a book's details in a JDialog
 */
public class BookViewer extends JDialog {

    /**
     * Constructor
     * @param owner owner frame
     * @param book the book to be viewed
     * @param student student who viewed the book
     * @param controller controller
     */
    public BookViewer(Frame owner, Book book, Student student, ClientController controller) {
        super(owner, book.getBookTitle(), true);

        setPreferredSize(new Dimension(400, 600));
        setResizable(false);

        // initialize a panel and its layout
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // fetch the book's image in the resources
        Picture picture = new Picture(book.getImagePath(), 150, 200);

        // show the details of the book
        JLabel title = new JLabel(book.getBookTitle());
        JLabel author = new JLabel("by: " + book.getAuthor());
        JLabel genre = new JLabel("Genre: " + book.getGenre());
        JLabel copies = new JLabel("Copies Left: " + book.getCopies());

        JTextArea textArea = new JTextArea(book.getShortDescription());
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        JScrollPane textScrollPane = new JScrollPane(textArea);
        textScrollPane.setPreferredSize(new Dimension(200, 120));

        JPanel buttonPanel = getjPanel(book, student, controller);

        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.insets = new Insets(5, 5, 5, 70);

        // add the picture using the given constraints
        panel.add(picture, constraints);

        constraints.insets = new Insets(5, 5, 5, 30);
        constraints.gridx = 2;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.WEST;

        // add the title using the given constraints
        panel.add(title, constraints);


        constraints.gridy = 2;

        // add the author using the given constraints
        panel.add(author, constraints);

        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridy = 3;
        constraints.gridx = 0;

        // add the genre using the given constraints
        panel.add(genre, constraints);

        constraints.gridx = 2;
        constraints.gridy = 3;

        // add the copies using the given constraints
        panel.add(copies, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 3;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        // add the scroll pane using the given constraints
        panel.add(textScrollPane, constraints);

        constraints.gridy = 5;
        constraints.weightx = 3.0;

        // add the button panel using the given constraints
        panel.add(buttonPanel, constraints);

        // add the panel to the parent panel
        add(panel);
        pack();

        setLocationRelativeTo(null);
    } // end of constructor

    /**
     *
     * @param book
     * @param student
     * @param clientController
     * @return
     */
    private static JPanel getjPanel(Book book, Student student, ClientController clientController) {
        JPanel buttonPanel = new JPanel();
        JLabel errorMessage = new JLabel();

        // initialize the add book button and its properties
        Button addBookButton =  new Button("Add Book", 200, 80, FontFactory.newPoppinsDefault(14));
        addBookButton.setForeground(Color.WHITE);
        addBookButton.setBackground(ColorFactory.green());

        // set the layout of the button panel to BoxLayout
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        if(book.getCopies() == 0) {
            errorMessage.setText("There are no copies of the book already");
            addBookButton.setEnabled(false);
        } // end of if statement

        for(String studentId: book.getPendingBorrowers()) {

            if(studentId.equals(student.getAccount().getAccountId())){
                errorMessage.setText("This book is already added on your pending");
                addBookButton.setEnabled(false);
                break;
            } // end of if statement

        } // end of for each

        for(String studentId: book.getCurrentBorrowers()) {

            if(studentId.equals(student.getAccount().getAccountId())){
                errorMessage.setText("You are already borrowing this book");
                addBookButton.setEnabled(false);
                break;
            } // end of if statement

        } // end of for each

        for(String studentId: book.getPendingBookReturners()) {

            if(studentId.equals(student.getAccount().getAccountId())){
                errorMessage.setText("This book is pending for return");
                addBookButton.setEnabled(false);
                break;
            } // end of if statement

        } // end of for each


        if(!errorMessage.getText().isEmpty()){
            buttonPanel.add(errorMessage);
        } // end of if statement

        buttonPanel.add(addBookButton);
        addBookButton.addActionListener(e -> clientController.borrowBook(book));
        return buttonPanel;
    } // end of getjPanel


} // end of class