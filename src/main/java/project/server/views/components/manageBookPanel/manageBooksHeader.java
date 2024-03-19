package project.server.views.components.manageBookPanel;

import project.server.views.LibrarianMainFrame;
import project.server.views.components.ClickableText;
import project.utilities.utilityClasses.FontFactory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class manageBooksHeader extends JPanel {
    private ClickableText pendingBorrow = new ClickableText("Pending Borrow", 100, 50, FontFactory.newPoppinsBold(18));
    private ClickableText pendingReturn = new ClickableText("Pending Return", 100, 50, FontFactory.newPoppinsBold(18));
    private ClickableText borrowed = new ClickableText("Borrowed", 100, 50, FontFactory.newPoppinsBold(18));

    private JPanel clickablePanel;

    public manageBooksHeader() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = 0;
        constraints.gridx = 0;

        setBorder(new EmptyBorder(0, 10, 0, 15));

        setLayout(new GridBagLayout());
        setSize(new Dimension(LibrarianMainFrame.WIDTH, 150));

        clickablePanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(clickablePanel, BoxLayout.X_AXIS);
        clickablePanel.setLayout(boxLayout);
        clickablePanel.add(pendingBorrow);
        clickablePanel.add(pendingReturn);
        clickablePanel.add(borrowed);

        add(new JPanel(), constraints);
        constraints.gridx = 2;
        add(new JPanel(), constraints);
        constraints.gridx = 3;
        add(new JPanel(), constraints);

        constraints.gridy = 4;
        constraints.fill = 1;
        constraints.weightx = 1.0;
        add(clickablePanel, constraints);
    }
}
