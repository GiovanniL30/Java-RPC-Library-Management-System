package project.server.views.components.manageBookPanel;

import project.server.controller.ServerController;
import project.server.views.LibrarianMainFrame;
import project.server.views.components.ClickableText;
import project.server.views.panels.ManageBookPanel;
import project.server.views.utility.ServerPanels;
import project.utilities.utilityClasses.FontFactory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.LinkedList;

public class ManageBooksHeader extends JPanel {
    private final ServerController serverController;
    private final ManageBookPanel manageBookPanel; // Reference to ManageBookPanel
    private final ClickableText pendingBorrow;
    private final ClickableText pendingReturn;
    private final ClickableText borrowed;

    public ManageBooksHeader(ServerController serverController, ManageBookPanel manageBookPanel) {
        this.serverController = serverController;
        this.manageBookPanel = manageBookPanel; // Assign the reference

        setBorder(new EmptyBorder(0, 10, 0, 15));
        setLayout(new BorderLayout());
        setSize(new Dimension(LibrarianMainFrame.WIDTH, 150));

        JPanel clickablePanel = new JPanel();
        clickablePanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        pendingBorrow = new ClickableText("Pending Borrow", 100, 50, FontFactory.newPoppinsBold(18));
        pendingReturn = new ClickableText("Pending Return", 100, 50, FontFactory.newPoppinsBold(18));
        borrowed = new ClickableText("Borrowed", 100, 50, FontFactory.newPoppinsBold(18));

        clickablePanel.add(pendingBorrow);
        clickablePanel.add(pendingReturn);
        clickablePanel.add(borrowed);

        add(clickablePanel, BorderLayout.NORTH);

        pendingBorrow.addActionListener(e -> switchToPanel(ServerPanels.PENDING_BORROW_PANEL));
        pendingReturn.addActionListener(e -> switchToPanel(ServerPanels.PENDING_RETURN_PANEL));
        borrowed.addActionListener(e -> switchToPanel(ServerPanels.BORROWED_PANEL));
    }

    private void switchToPanel(ServerPanels panel) {
        manageBookPanel.switchToPanel(panel); // Call switchToPanel of ManageBookPanel
    }
}