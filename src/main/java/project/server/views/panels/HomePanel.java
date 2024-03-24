package project.server.views.panels;

import project.server.controller.ServerObserver;
import project.server.views.LibrarianMainFrame;
import project.server.views.components.homePanel.BookSummary;
import project.utilities.viewComponents.Picture;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {
    private final ServerObserver serverController;


    public HomePanel(ServerObserver serverObserver) {
        serverController = serverObserver;
        setBackground(Color.WHITE);

        JPanel holder = new JPanel();
        holder.setBackground(Color.WHITE);
        holder.setLayout(new BorderLayout());


        BookSummary bookSummary = new BookSummary(serverController);

        Picture banner = new Picture("src/main/resources/images/logo/vanni_banner.png", LibrarianMainFrame.FRAME_WIDTH, 300);
        banner.setBackground(Color.WHITE);

        holder.add(banner, BorderLayout.NORTH);
        holder.add(bookSummary, BorderLayout.CENTER);

        JScrollPane scrollPane = new JScrollPane(holder);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(LibrarianMainFrame.FRAME_WIDTH- 100,700));
        scrollPane.setBackground(Color.WHITE);

        add(scrollPane);
    }
}