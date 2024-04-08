package midproject.server.views.components;

import midproject.server.controller.ServerController;
import midproject.server.views.LibrarianMainFrame;
import midproject.server.views.utility.ServerPanels;
import midproject.utilities.utilityClasses.FontFactory;
import midproject.utilities.viewComponents.Picture;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class ServerGuiHeader extends JPanel {

    private Picture logo = new Picture("src/main/resources/images/logo/logo_vanni.png", 100, 100);
    private ClickableText home = new ClickableText("Home", 100, 50, FontFactory.newPoppinsBold(18));
    private ClickableText viewBooks  = new ClickableText("View Books", 100, 50, FontFactory.newPoppinsBold(18));
    private ClickableText addBooks = new ClickableText("Add Book", 100, 50, FontFactory.newPoppinsBold(18));
    private ClickableText manageBooks = new ClickableText("Manage Books", 100, 50, FontFactory.newPoppinsBold(18));
    private ClickableText accounts = new ClickableText("Accounts", 100, 50, FontFactory.newPoppinsBold(18));
    private LinkedList<ClickableText> clickableTexts = new LinkedList<>();

    //Panel Holders
    private JPanel clickablePanel;

    public ServerGuiHeader(ServerController serverController) {

        clickableTexts.add(home);
        clickableTexts.add(viewBooks);
        clickableTexts.add(addBooks);
        clickableTexts.add(manageBooks);
        clickableTexts.add(accounts);

        setBackground(Color.white);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = 0;
        constraints.gridx = 0;

        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(LibrarianMainFrame.WIDTH, 100));


        clickablePanel = new JPanel();
        clickablePanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 0));


        clickablePanel.add(home);
        clickablePanel.add(viewBooks);
        clickablePanel.add(addBooks);
        clickablePanel.add(manageBooks);
        clickablePanel.add(accounts);


        logo.setBackground(Color.WHITE);
        add(logo, constraints);


        constraints.gridx = 1;
        constraints.fill = 2;
        constraints.weightx = 2.0;
        clickablePanel.setBackground(Color.WHITE);
        add(clickablePanel, constraints);


        home.addActionListener( e -> serverController.changeFrame(ServerPanels.HOME_PANEL));
        viewBooks.addActionListener( e -> serverController.changeFrame(ServerPanels.VIEW_BOOKS_PANEL));
        addBooks.addActionListener(e -> serverController.changeFrame(ServerPanels.ADD_BOOKS_PANEL));
        manageBooks.addActionListener(e -> serverController.changeFrame(ServerPanels.MANAGE_BOOK_PANEL));
        accounts.addActionListener( e -> serverController.changeFrame(ServerPanels.MANAGE_ACCOUNTS_PANEL));

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

    public ClickableText getAddBooks() {
        return addBooks;
    }

}
