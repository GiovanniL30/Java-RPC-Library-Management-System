package project.server.views.components.manageBookPanel;

import project.server.controller.ServerController;
import project.server.views.LibrarianMainFrame;
import project.server.views.components.ClickableText;
import project.server.views.utility.ServerPanels;
import project.utilities.utilityClasses.FontFactory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.LinkedList;

public class ManageBooksHeader extends JPanel {
    private LinkedList<ClickableText> clickableTexts = new LinkedList<>();
    private final ClickableText pendingBorrow = new ClickableText("Pending Borrow", 100, 50, FontFactory.newPoppinsBold(18));
    private final ClickableText pendingReturn = new ClickableText("Pending Return", 100, 50, FontFactory.newPoppinsBold(18));
    private final ClickableText borrowed = new ClickableText("Borrowed", 100, 50, FontFactory.newPoppinsBold(18));
    private final JPanel clickablePanel;

    public ManageBooksHeader(ServerController serverController) {
        setBorder(new EmptyBorder(0, 10, 0, 15));
        setLayout(new BorderLayout());
        setSize(new Dimension(LibrarianMainFrame.WIDTH, 150));

        clickablePanel = new JPanel();
        clickablePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        clickablePanel.add(pendingBorrow);
        clickablePanel.add(pendingReturn);
        clickablePanel.add(borrowed);

        add(clickablePanel);

        borrowed.addActionListener( e -> serverController.changeFrame(ServerPanels.BORROWED_PANEL));
    }
    public void setCurrentButton(ClickableText currentText) {

        currentText.setEnabled(false);


        for(ClickableText clickableText: clickableTexts) {

            if(!clickableText.equals(currentText)){
                clickableText.setEnabled(true);
            }
        }
    }

}