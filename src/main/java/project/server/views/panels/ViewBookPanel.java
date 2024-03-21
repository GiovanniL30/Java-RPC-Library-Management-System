package project.server.views.panels;

import project.server.controller.ServerController;
import project.server.views.components.viewBookPanel.BookList;
import project.server.views.components.viewBookPanel.ViewBooksHeader;

import javax.swing.*;

public class ViewBookPanel extends JPanel {

    private ServerController serverController;
    private ViewBooksHeader header;
    private BookList bookList;

    public ViewBookPanel(ServerController serverController) {
        this.serverController = serverController;
        add(new JLabel("View Book"));
    }

}
