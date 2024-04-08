package midproject.server.views.panels;

import midproject.server.controller.ServerObserver;
import midproject.server.views.LibrarianMainFrame;
import midproject.server.views.components.accountPanel.AccountSearch;
import midproject.server.views.components.accountPanel.ManageAccountList;
import midproject.utilities.referenceClasses.Student;

import javax.swing.*;
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
