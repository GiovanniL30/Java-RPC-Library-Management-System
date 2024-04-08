package project.client.views.components;

import project.client.controller.ClientController;
import project.utilities.referenceClasses.Book;
import project.utilities.utilityClasses.ColorFactory;
import project.utilities.utilityClasses.FontFactory;
import project.utilities.viewComponents.Button;
import project.utilities.viewComponents.Picture;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.LinkedList;

/**
 * Class that shows the books in a JPanel
 */
public class BookListPanel extends JPanel {

    private final boolean isPending;
    private final ClientController clientController;

    /**
     * Constructor
     * @param pendingBooks pending books of a user
     * @param clientController controller object
     * @param isPending boolean value if the books are pending
     * @param height used in setting the size of the panel
     */
    public BookListPanel(LinkedList<Book> pendingBooks, ClientController clientController, boolean isPending, int height) {

        this.isPending = isPending;
        this.clientController = clientController;

        // instantiate a panel to hold other components
        JPanel holder = new JPanel();
        GridLayout gridLayout = new GridLayout(0, 1);
        gridLayout.setVgap(20);

        // set the layout of the holder as GridLayout
        holder.setLayout(gridLayout);

        for (Book book : pendingBooks) {
            // add each pending book to the panel
            holder.add(new BookCard(book));
        } // end of for each

        JScrollPane scrollPane = new JScrollPane(holder);
        scrollPane.setPreferredSize(new Dimension(900, height));

        add(scrollPane);

    } // end of constructor

    /**
     * Book Cards of each book to be added to the holder panel
     */
    private class BookCard extends JPanel {

        /**
         * Constructor
         * @param book book to be made into a book card
         */
        private BookCard(Book book) {

            // set the book card's properties
            setSize(new Dimension(300, 200));
            setLayout(new GridBagLayout());
            setBorder(new EmptyBorder(0, 10, 0, 20));

            // fetch components to be put in the book card
            Picture picture = new Picture(book.getImagePath(), 130, 200);
            JLabel bookName = new JLabel(book.getBookTitle());
            bookName.setFont(FontFactory.newPoppinsBold(18));
            JLabel author = new JLabel("by: " + book.getAuthor());
            author.setFont(FontFactory.newPoppinsDefault(12));

            Button button;
            // set the button's text depending on the status of the book
            if (isPending) {
                button = new Button("Cancel", 100, 50, FontFactory.newPoppinsDefault(13));
            } else {
                button = new Button("Return", 100, 50, FontFactory.newPoppinsDefault(13));
            } // end of if else

            button.setForeground(Color.white);
            button.setBackground(ColorFactory.red());

            // instantiate panels and add their components
            JPanel bookInfoPanel = new JPanel();
            JPanel bookPanel = new JPanel();

            bookInfoPanel.setLayout(new BoxLayout(bookInfoPanel, BoxLayout.Y_AXIS));
            bookInfoPanel.add(bookName);
            bookInfoPanel.add(author);

            bookPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            bookPanel.add(picture);
            bookPanel.add(bookInfoPanel);

            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = 0;
            constraints.gridy = 0;
            constraints.weightx = 2.0;
            constraints.fill = 2;

            // add the book panel using the given constraints
            add(bookPanel, constraints);

            constraints.gridx = 1;
            constraints.weightx = 0.0;
            constraints.fill = 0;

            // add the buttons using the given constraints
            add(button, constraints);


            button.addActionListener(e -> {

                if (isPending) {

                    int option = JOptionPane.showConfirmDialog(clientController.getMainView(), "Are you sure you want to cancel " + book.getBookTitle() + "?", "Confirm Cancel", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        // remove the book from pending
                        clientController.removePending(book);
                    } // end of if statement


                } else {
                    int option = JOptionPane.showConfirmDialog(clientController.getMainView(), "Are you sure you want to cancel " + book.getBookTitle() + "?", "Confirm Cancel", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        // return the book
                        clientController.returnBook(book);
                    } // end of if statement
                } // end of if else

            });


        } // end of BookCard constructor


    } // end of BookCard class


} // end of class
