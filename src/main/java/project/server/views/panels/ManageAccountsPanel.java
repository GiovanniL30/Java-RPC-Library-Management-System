package project.server.views.panels;

import project.server.controller.ServerController;
import project.server.controller.ServerObserver;
import project.server.views.LibrarianMainFrame;
import project.server.views.components.ClickableText;
import project.server.views.components.accountPanel.AccountSearch;
import project.server.views.components.accountPanel.MangeAccountList;
import project.server.views.components.manageBookPanel.ManageBookList;
import project.utilities.referenceClasses.Student;
import project.utilities.utilityClasses.ColorFactory;
import project.utilities.utilityClasses.FontFactory;
import project.utilities.viewComponents.Picture;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.LinkedList;

public class ManageAccountsPanel extends JPanel{
    private final ServerObserver serverController;
    private final JPanel accountsPanel = new JPanel();
    private AccountSearch accountSearch = new AccountSearch(new Dimension(LibrarianMainFrame.WIDTH, 55));
    private final ClickableText createAccount = new ClickableText("Create account for " +
            "student", 100, 50, FontFactory.newPoppinsBold(15));

    public ManageAccountsPanel(LinkedList<Student> accounts, ServerObserver serverController){
        this.serverController = serverController;
        setLayout(new BorderLayout());

        add(accountSearch, BorderLayout.NORTH);


        MangeAccountList mangeAccountList = new MangeAccountList(accounts, serverController);
        add(mangeAccountList, BorderLayout.CENTER);

        createAccount.setBorder(new EmptyBorder(60, 0, 0, 0));
        createAccount.setForeground(Color.BLUE);
        // createAccount.addActionListener(e -> signup.setVisible(true));
    }





}
