package project.server.views.panels;

import project.server.controller.ServerObserver;
import project.server.views.components.homePanel.BookSummary;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {
    private final ServerObserver serverController;

    public HomePanel(ServerObserver serverObserver) {
        serverController = serverObserver;
        setLayout(new BorderLayout());

        // Create the BookSummary component
        BookSummary bookSummary = new BookSummary(serverController);

        // Add the BookSummary component to the HomePanel
        add(bookSummary, BorderLayout.SOUTH);
    }
}