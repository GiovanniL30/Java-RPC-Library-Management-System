package project.server.views.panels;

import project.server.controller.ServerController;
import project.server.views.components.viewBookPanel.AllBooksPanel;
import project.server.views.components.viewBookPanel.ViewBooksList;
import project.server.views.components.viewBookPanel.ViewBooksHeader;

import javax.swing.*;
import java.awt.*;

public class ViewBookPanel extends JPanel {

    private ServerController serverController;
    private ViewBooksHeader header = new ViewBooksHeader();
    private AllBooksPanel allBooksPanel;

    public ViewBookPanel(ServerController serverController) {
        this.serverController = serverController;
        setBackground(Color.white);

        header.setLayout(new FlowLayout(FlowLayout.CENTER));

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel,BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.black);
        contentPanel.add(header);
        allBooksPanel = new AllBooksPanel(serverController);
        contentPanel.add(allBooksPanel);
        add(contentPanel);

    }

}
