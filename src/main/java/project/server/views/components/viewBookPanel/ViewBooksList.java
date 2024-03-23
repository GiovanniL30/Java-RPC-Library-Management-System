package project.server.views.components.viewBookPanel;

import project.server.controller.ServerController;
import project.utilities.referenceClasses.Book;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class ViewBooksList extends JPanel {
    private final ServerController serverController;

    public ViewBooksList(LinkedList<Book> books, ServerController serverController) {

        this.serverController = serverController;

        JPanel holder = new JPanel();
        GridLayout gridLayout = new GridLayout(0, 1);
        gridLayout.setVgap(20);
        holder.setLayout(gridLayout);

        for (Book book : books) {
            holder.add(new ViewBooksCard(book));
        }

        JScrollPane scrollPane = new JScrollPane(holder);
        scrollPane.setPreferredSize(new Dimension(900, 400));

        add(scrollPane);

    }


}
