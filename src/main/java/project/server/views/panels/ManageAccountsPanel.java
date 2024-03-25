package project.server.views.panels;

import project.server.controller.ServerObserver;
import project.server.views.LibrarianMainFrame;
import project.server.views.components.ClickableText;
import project.server.views.components.accountPanel.AccountSearch;
import project.server.views.components.accountPanel.ManageAccountList;
import project.utilities.referenceClasses.Student;
import project.utilities.utilityClasses.FontFactory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.LinkedList;

public class ManageAccountsPanel extends JPanel{
    private final ServerObserver serverController;
    private final JPanel accountsPanel = new JPanel();
    private AccountSearch accountSearch = new AccountSearch(new Dimension(LibrarianMainFrame.WIDTH, 55));


    public ManageAccountsPanel(LinkedList<Student> accounts, ServerObserver serverController){
        this.serverController = serverController;
        setLayout(new BorderLayout());

        add(accountSearch, BorderLayout.NORTH);

        ManageAccountList mangeAccountList = new ManageAccountList(accounts, serverController);
        add(mangeAccountList, BorderLayout.CENTER);

        accountSearch.getCreateAccount().addActionListener(e -> serverController.openSignUp());
    }





}
