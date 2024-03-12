package project.client.views.components;

import project.utilities.referenceClasses.Book;
import project.utilities.utilityClasses.FontFactory;
import project.utilities.viewComponents.Button;
import project.utilities.viewComponents.IconButton;
import project.utilities.viewComponents.Picture;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class HomePanel extends JPanel {

    private LinkedList<Book> books;

    public HomePanel(LinkedList<Book> books){
        this.books = books;

        GridLayout gridLayout = new GridLayout(0, 2);
        gridLayout.setVgap(10);
        gridLayout.setHgap(10);

        JPanel panel = new JPanel();
        panel.setLayout(gridLayout);

        for(Book book : books) {
            panel.add(new BookCardComponent(book));
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setPreferredSize(new Dimension(920, 400));

        add(scrollPane);

    }

    private static class BookCardComponent extends JPanel {

        private BookCardComponent(Book book) {

            setSize(new Dimension(100, 200));

            IconButton button = new IconButton(book.getImagePath(), 250, 300);
            JLabel label = new JLabel(book.getBookTitle());
            label.setFont(FontFactory.newPoppinsBold(13));

            setLayout(new GridBagLayout());

            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridy = 0;
            constraints.gridx = 0;
            add(button, constraints);
            constraints.gridy = 1;
            add(label, constraints);

        }

    }

}