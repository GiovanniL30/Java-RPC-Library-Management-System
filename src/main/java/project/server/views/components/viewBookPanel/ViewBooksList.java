package project.server.views.components.viewBookPanel;

import project.client.views.components.HomePanel;
import project.server.controller.ServerController;
import project.server.controller.ServerObserver;
import project.utilities.referenceClasses.Book;
import project.utilities.viewComponents.Loading;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 * Represents a panel containing a list of books for viewing.
 */

public class ViewBooksList extends JPanel {
    private final ServerObserver serverObserver;
    private JPanel holder = new JPanel();

    /**
     * Constructs a ViewBooksList panel.
     * @param books          The list of books to display.
     * @param serverObserver The server observer for interacting with server functionalities.
     */
    public ViewBooksList(LinkedList<Book> books, ServerObserver serverObserver) {
        this.serverObserver = serverObserver;

        setBackground(Color.white);

        GridLayout gridLayout = new GridLayout(0, 1);
        gridLayout.setVgap(20);
        holder.setLayout(gridLayout);
        holder.setBackground(Color.white);

        updateView(books);

        JScrollPane scrollPane = new JScrollPane(holder);
        scrollPane.setPreferredSize(new Dimension(920, 550));

        add(scrollPane);
    } // end of ViewBooksList constructor

    /**
     * Updates the view with the given list of books.
     * @param books The list of books to display.
     */
    public void updateView(LinkedList<Book> books){
        new SwingWorker<>() {
            @Override
            protected Object doInBackground() {
                if(books.isEmpty()) {
                    holder.revalidate();
                    holder.repaint();
                }

                for (Book book : books) {
                    holder.add(new ViewBooksCard(book, serverObserver));
                    holder.revalidate();
                    holder.repaint();
                }
                return null;
            }


        }.execute();
    } // end of updateView method
} // end of ViewBooksList class
