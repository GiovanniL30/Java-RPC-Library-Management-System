package project.server.views.components.viewBookPanel;

import project.server.controller.ServerController;
import project.server.views.components.BookCard;
import project.utilities.referenceClasses.Book;
import project.utilities.utilityClasses.ColorFactory;
import project.utilities.utilityClasses.FontFactory;
import project.utilities.viewComponents.Button;
import project.utilities.viewComponents.Picture;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ViewBookCard extends JPanel implements BookCard {
    private ServerController serverController;;
    public ViewBookCard(Book book) {

        setSize(new Dimension(300, 200));
        setLayout(new GridBagLayout());
        setBorder(new EmptyBorder(0, 10, 0, 20));

        Picture bookPicture = new Picture(book.getImagePath(), 130, 200);
        JLabel bookTitle = new JLabel(book.getBookTitle());
        bookTitle.setFont(FontFactory.newPoppinsBold(18));
        JLabel bookCopies = new JLabel("Copies: " + book.getCopies());
        bookCopies.setFont(FontFactory.newPoppinsDefault(12));

        Button editBookButton = new Button("Edit", 100, 50, FontFactory.newPoppinsDefault(13));
        editBookButton.setForeground(Color.white);
        editBookButton.setBackground(ColorFactory.blue());

        Button deleteBookButton = new Button("Delete", 100, 50, FontFactory.newPoppinsDefault(13));
        deleteBookButton.setForeground(Color.white);
        deleteBookButton.setBackground(ColorFactory.red());

        Button prevOwnersButton = new Button("Previous Owners", 100, 50, FontFactory.newPoppinsDefault(13));
        prevOwnersButton.setForeground(Color.white);
        prevOwnersButton.setBackground(ColorFactory.green());

        JPanel bookPanel = new JPanel();
        JPanel bookInfoPanel = new JPanel();

        bookInfoPanel.setLayout(new BoxLayout(bookInfoPanel, BoxLayout.Y_AXIS));
        bookInfoPanel.add(bookTitle);
        bookInfoPanel.add(bookCopies);

        bookPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        bookPanel.add(bookPicture);
        bookPanel.add(bookInfoPanel);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 2.0;
        constraints.fill = 2;
        add(bookPanel, constraints);

        constraints.gridx = 1;
        constraints.weightx = 0.0;
        constraints.fill = 0;
        add(editBookButton, constraints);

        constraints.gridx = 2;
        constraints.weightx = 0.0;
        constraints.fill = 0;
        add(deleteBookButton, constraints);

        constraints.gridx = 3;
        constraints.weightx = 0.0;
        constraints.fill = 0;
        add(prevOwnersButton, constraints);

        editBookButton.addActionListener(e -> {

        });

        deleteBookButton.addActionListener(e -> {


//            int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this book?", "Book delete confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, null);
//
//            if (choice == JOptionPane.YES_OPTION) {
//                if (serverController.deleteBook(book.getBookId())) {
//                    // Message dialog for successful deletion
//                    JOptionPane.showMessageDialog(null, "Book deleted successfully.");
//                    booksPanel.remove(this);
//                    booksPanel.revalidate();
//                    booksPanel.repaint();
//                } else {
//                    // Message dialog for failed deletion
//                    JOptionPane.showMessageDialog(null, "Failed to delete book");
//                }
//            }


        });


    }

}
