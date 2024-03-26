package project.server.views.panels;

import project.server.controller.ServerObserver;
import project.server.views.LibrarianMainFrame;
import project.server.views.components.ClickableText;
import project.server.views.components.accountPanel.AccountSearch;
import project.server.views.components.accountPanel.ManageAccountList;
import project.server.views.components.viewBookPanel.ViewBooksList;
import project.utilities.referenceClasses.Book;
import project.utilities.referenceClasses.Student;
import project.utilities.utilityClasses.FontFactory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class ManageAccountsPanel extends JPanel{
    private boolean haveSearched = false;
    private final ServerObserver serverController;
    private ManageAccountList manageAccountList;
    private final JPanel accountsPanel = new JPanel();
    private AccountSearch accountSearch = new AccountSearch(new Dimension(LibrarianMainFrame.WIDTH, 55));


    public ManageAccountsPanel(LinkedList<Student> accounts, ServerObserver serverController){
        this.serverController = serverController;
        setLayout(new BorderLayout());

        add(accountSearch, BorderLayout.NORTH);

        ManageAccountList mangeAccountList = new ManageAccountList(accounts, serverController);
        add(mangeAccountList, BorderLayout.CENTER);

        accountSearch.getCreateAccount().addActionListener(e -> serverController.openSignUp());
        accountSearch.getCancel().addActionListener(this::closeSearch);
        accountSearch.getSearch().addActionListener(this::performSearch);
    }
    public synchronized void setView(LinkedList<Student> students) {
        remove(1);
        manageAccountList = new ManageAccountList(students, serverController);
        add(manageAccountList);
        revalidate();
        repaint();
    }
    private void closeSearch(ActionEvent event){
        if (haveSearched) {
            setView(serverController.getStudents());
            accountSearch.getInputField().setText("");
            haveSearched = false;
        }
    }

    private void performSearch(ActionEvent event) {
        String searchInput = accountSearch.getInputField().getText();

        if (searchInput == null || searchInput.trim().isEmpty()) {
            accountSearch.enableError("Enter a Title or an Author");
            return;
        }

        LinkedList<Student> searchResults = serverController.getStudents().stream()
                .filter(student -> student.getAccount().getFirstName().toLowerCase().contains(searchInput.toLowerCase())
                        || student.getAccount().getLastName().toLowerCase().contains(searchInput.toLowerCase())
                        || student.getAccount().getUserName().toLowerCase().contains(searchInput.toLowerCase()))
                .collect(Collectors.toCollection(LinkedList::new));

        haveSearched = true;
        setView(searchResults);
    }



}
