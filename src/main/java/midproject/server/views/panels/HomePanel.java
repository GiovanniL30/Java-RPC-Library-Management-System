package midproject.server.views.panels;

import midproject.server.controller.ServerObserver;
import midproject.server.views.LibrarianMainFrame;
import midproject.server.views.components.homePanel.BookSummary;
import midproject.server.views.components.homePanel.BroadcastMessage;
import midproject.utilities.viewComponents.Picture;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {
    private final ServerObserver serverController;

    public HomePanel(ServerObserver serverObserver) {
        serverController = serverObserver;
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        JPanel holder = new JPanel();
        holder.setBackground(Color.WHITE);
        holder.setLayout(new BorderLayout());
        holder.setPreferredSize(new Dimension(LibrarianMainFrame.FRAME_WIDTH - 100, 575));

        BookSummary bookSummary = new BookSummary(serverController);
        Picture banner = new Picture("src/main/resources/images/logo/vanni_banner.png", LibrarianMainFrame.FRAME_WIDTH - 200, 250);
        banner.setBackground(Color.WHITE);

        holder.add(banner, BorderLayout.NORTH);
        holder.add(bookSummary, BorderLayout.CENTER);

        add(holder, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,50,5));
        buttonPanel.setBackground(Color.WHITE);
        JButton broadcastButton = new JButton("Broadcast");
        broadcastButton.setPreferredSize(new Dimension(200, 35));
        broadcastButton.setForeground(Color.BLACK);
        broadcastButton.setBackground(Color.WHITE);

        buttonPanel.add(broadcastButton);

        broadcastButton.addActionListener(e -> {
            BroadcastMessage broadcastMessage = new BroadcastMessage((Frame) SwingUtilities.getWindowAncestor(HomePanel.this),
                    serverController);
            broadcastMessage.setVisible(true);
        });

        add(buttonPanel, BorderLayout.SOUTH);
    }
}