package project.server.views.components.viewBookPanel;

import project.server.controller.ServerController;
import project.server.controller.ServerObserver;
import project.server.views.LibrarianMainFrame;
import project.utilities.referenceClasses.Book;
import project.utilities.utilityClasses.ColorFactory;
import project.utilities.utilityClasses.FontFactory;
import project.utilities.viewComponents.Button;
import project.utilities.viewComponents.Picture;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ViewBooksCard extends JPanel {
    private ServerObserver serverObserver;;
    private EditBookViewer editBookViewer;
    private LibrarianMainFrame mainView;
    private Button editBookButton = new Button("Edit", 130, 50, FontFactory.newPoppinsDefault(13));
    private Button deleteBookButton = new Button("Delete", 130, 50, FontFactory.newPoppinsDefault(13));
    private Button prevOwnersButton = new Button("Previous Owners", 130, 50, FontFactory.newPoppinsDefault(13));
    private Button currentOwnersButton = new Button("Current Owners", 130, 50, FontFactory.newPoppinsDefault(13));
    public ViewBooksCard(Book book, ServerObserver serverObserver) {
        this.serverObserver = serverObserver;
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

        editBookButton.addActionListener(e -> {
            editBookViewer = new EditBookViewer(mainView, book, serverObserver);
            editBookViewer.setVisible(true);
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

        prevOwnersButton.addActionListener(e -> {

        });
        currentOwnersButton.addActionListener(e -> {

        });
    }

}

