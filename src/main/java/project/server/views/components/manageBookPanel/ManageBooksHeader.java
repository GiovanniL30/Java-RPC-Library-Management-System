package project.server.views.components.manageBookPanel;

import project.server.views.LibrarianMainFrame;
import project.server.views.components.ClickableText;
import project.utilities.utilityClasses.FontFactory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ManageBooksHeader extends JPanel {
    private final ClickableText pendingBorrow = new ClickableText("Pending Borrow", 100, 50, FontFactory.newPoppinsBold(18));
    private final ClickableText pendingReturn = new ClickableText("Pending Return", 100, 50, FontFactory.newPoppinsBold(18));
    private final ClickableText borrowed = new ClickableText("Borrowed", 100, 50, FontFactory.newPoppinsBold(18));
    private final JPanel clickablePanel;

    public ManageBooksHeader() {
        setBorder(new EmptyBorder(0, 10, 0, 15));
        setLayout(new BorderLayout());
        setSize(new Dimension(LibrarianMainFrame.WIDTH, 150));

        clickablePanel = new JPanel();
        clickablePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        clickablePanel.add(pendingBorrow);
        clickablePanel.add(pendingReturn);
        clickablePanel.add(borrowed);

        add(clickablePanel);

    }
}