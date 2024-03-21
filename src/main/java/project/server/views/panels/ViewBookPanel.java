package project.server.views.panels;

import project.server.controller.ServerController;
import project.server.views.components.viewBookPanel.BookList;
import project.server.views.components.viewBookPanel.ViewBooksHeader;

import javax.swing.*;
import java.awt.*;

public class ViewBookPanel extends JPanel {

    private ServerController serverController;
    private ViewBooksHeader header = new ViewBooksHeader();
    private BookList bookList;

    public ViewBookPanel(ServerController serverController) {
        this.serverController = serverController;
        header.setLayout(new FlowLayout(FlowLayout.LEFT));
        header.add(header.getTextHeaderPanel());
        header.add(header.getSearchHeaderPanel());
        add(header);
    }

}
