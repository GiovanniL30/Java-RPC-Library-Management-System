package project.server.views.components;

import project.server.views.LibrarianMainFrame;
import project.utilities.utilityClasses.FontFactory;
import project.utilities.viewComponents.Picture;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.LinkedList;

public class ServerGuiHeader extends JPanel {

    private Picture logo = new Picture("src/main/resources/images/logo/logo_vanni.png", 150, 150);
    private ClickableText home = new ClickableText("Home", 100, 50, FontFactory.newPoppinsBold(18));
    private ClickableText viewBooks  = new ClickableText("View Books", 100, 50, FontFactory.newPoppinsBold(18));
    private ClickableText manageBooks = new ClickableText("Manage Books", 100, 50, FontFactory.newPoppinsBold(18));
    private ClickableText accounts = new ClickableText("Accounts", 100, 50, FontFactory.newPoppinsBold(18));
    private LinkedList<ClickableText> clickableTexts = new LinkedList<>();;

    //Panel Holders
    private JPanel clickablePanel;

    public ServerGuiHeader() {

        clickableTexts.add(home);
        clickableTexts.add(viewBooks);
        clickableTexts.add(manageBooks);
        clickableTexts.add(accounts);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = 0;
        constraints.gridx = 0;
        setBorder(new EmptyBorder(0, 10, 0, 15));

        setLayout(new GridBagLayout());
        setSize(new Dimension(LibrarianMainFrame.WIDTH, 200));


        clickablePanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(clickablePanel, BoxLayout.X_AXIS);
        clickablePanel.setLayout(boxLayout);


        clickablePanel.add(home);
        clickablePanel.add(viewBooks);
        clickablePanel.add(manageBooks);
        clickablePanel.add(accounts);


        add(logo, constraints);

        constraints.gridx = 1;
        constraints.fill = 3;
        constraints.weightx = 3.0;

        add(new JPanel(), constraints);
        constraints.gridx = 2;
        add(new JPanel(), constraints);
        constraints.gridx = 3;
        add(new JPanel(), constraints);

        constraints.gridx = 4;
        constraints.fill = 1;
        constraints.weightx = 1.0;
        add(clickablePanel, constraints);



    }
    public void setCurrentClickableText(ClickableText currentText) {

        currentText.setEnabled(false);


        for(ClickableText clickableText: clickableTexts) {

            if(!clickableText.equals(currentText)){
                clickableText.setEnabled(true);
            }

        }
    }
    public ClickableText getAccounts() {
        return accounts;
    }

    public ClickableText getHome() {
        return home;
    }

    public ClickableText getManageBooks() {
        return manageBooks;
    }

    public ClickableText getViewBooks() {
        return viewBooks;
    }

    public JPanel getClickablePanel() {
        return clickablePanel;
    }
}
