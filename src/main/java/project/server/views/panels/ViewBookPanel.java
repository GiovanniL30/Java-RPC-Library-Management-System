package project.server.views.panels;

import project.server.controller.ServerController;
import project.server.views.components.viewBookPanel.AllBooksPanel;
import project.server.views.components.viewBookPanel.ViewBooksList;
import project.server.views.components.viewBookPanel.ViewBooksHeader;
import project.server.views.utility.ServerPanels;
import project.utilities.viewComponents.Loading;

import javax.swing.*;
import java.awt.*;

public class ViewBookPanel extends JPanel {

    private ServerController serverController;
    private ViewBooksHeader header;

    public ViewBookPanel(ServerController serverController) {
        this.serverController = serverController;
        this.header = new ViewBooksHeader(this.serverController);
        AllBooksPanel allBooksPanel = new AllBooksPanel(this.serverController);

        setBackground(Color.white);
        header.setLayout(new FlowLayout(FlowLayout.CENTER));

        add(header);
        add(allBooksPanel);

    }

}
