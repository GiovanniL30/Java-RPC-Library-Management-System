package project.server.views.panels;

import project.server.controller.ServerController;
import project.server.views.components.viewBookPanel.ViewBooksList;
import project.server.views.components.viewBookPanel.ViewBooksHeader;

import javax.swing.*;
import java.awt.*;

public class ViewBookPanel extends JPanel {

    private ServerController serverController;
    private ViewBooksHeader header = new ViewBooksHeader();
    private ViewBooksList bookList;

    public ViewBookPanel(ServerController serverController) {
        this.serverController = serverController;
        setBackground(Color.white);

        header.setLayout(new FlowLayout(FlowLayout.CENTER));
        add(header);
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));


    }

}
