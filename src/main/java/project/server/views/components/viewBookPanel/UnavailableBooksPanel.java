package project.server.views.components.viewBookPanel;

import project.server.controller.ServerController;
import javax.swing.*;
import java.awt.*;

public class UnavailableBooksPanel extends JPanel {
    private final ServerController serverController;
    private ViewBooksList bookList ;
    public UnavailableBooksPanel(ServerController serverController) {
        this.serverController = serverController;

        setBackground(Color.white);
        bookList = new ViewBooksList(serverController.getUnavailableBooks(), serverController);
        add(bookList);
    }
}
