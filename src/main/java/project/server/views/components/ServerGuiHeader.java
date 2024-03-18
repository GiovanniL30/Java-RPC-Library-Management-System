package project.server.views.components;

import project.server.views.LibrarianMainFrame;
import project.utilities.utilityClasses.FontFactory;
import project.utilities.viewComponents.Picture;

import javax.swing.*;
import java.awt.*;

public class ServerGuiHeader extends JPanel {

    private Picture logo = new Picture("src/main/resources/images/logo/logo_vanni.png", 200, 200);
    private ClickableText home = new ClickableText("Home", 100, 50, FontFactory.newPoppinsBold(13));
    private ClickableText viewBooks  = new ClickableText("View Books", 100, 50, FontFactory.newPoppinsBold(13));
    private ClickableText manageBooks = new ClickableText("Manage Books", 100, 50, FontFactory.newPoppinsBold(13));
    private ClickableText accounts = new ClickableText("Accounts", 100, 50, FontFactory.newPoppinsBold(13));


    //Panel Holders
    private JPanel clickablePanel;

    public ServerGuiHeader() {

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = 0;
        constraints.gridx = 0;
        constraints.fill = 2;
        constraints.weightx = 2.0;
        setLayout(new GridBagLayout());
        setSize(new Dimension(LibrarianMainFrame.WIDTH, LibrarianMainFrame.HEIGHT));


        clickablePanel = new JPanel();
        FlowLayout flowLayout = new FlowLayout(FlowLayout.RIGHT);
        flowLayout.setHgap(15);
        clickablePanel.setLayout(flowLayout);

        clickablePanel.add(home);
        clickablePanel.add(viewBooks);
        clickablePanel.add(manageBooks);
        clickablePanel.add(accounts);


        add(logo, constraints);

        constraints.gridx = 1;
        constraints.fill = 1;
        constraints.weightx = 0.0;
        add(clickablePanel, constraints);


    }


}
