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

/**
 * Represents the panel for managing accounts in the server GUI.
 * It allows searching, viewing, and managing user accounts.
 */

public class ManageAccountsPanel extends JPanel{
    private boolean haveSearched = false;
    private final ServerObserver serverController;
    private ManageAccountList manageAccountList;
    private final JPanel accountsPanel = new JPanel();
    private AccountSearch accountSearch = new AccountSearch(new Dimension(LibrarianMainFrame.WIDTH, 55));

    /**
     * Constructs the ManageAccountsPanel with the specified list of accounts and server observer.
     * @param accounts         The list of student accounts.
     * @param serverController The server observer to handle actions.
     */
    public ManageAccountsPanel(LinkedList<Student> accounts, ServerObserver serverController){
        this.serverController = serverController;
        setLayout(new BorderLayout());

        add(accountSearch, BorderLayout.NORTH);

        ManageAccountList mangeAccountList = new ManageAccountList(accounts, serverController);
        add(mangeAccountList, BorderLayout.CENTER);

        accountSearch.getCreateAccount().addActionListener(e -> serverController.openSignUp());
        accountSearch.getCancel().addActionListener(this::closeSearch);
        accountSearch.getSearch().addActionListener(this::performSearch);
    } // end ManageAccountsPanel constructor

    /**
     * Sets the view with the specified list of students.
     * @param students The list of student accounts to display.
     */
    public synchronized void setView(LinkedList<Student> students) {
        remove(1);
        manageAccountList = new ManageAccountList(students, serverController);
        add(manageAccountList);
        revalidate();
        repaint();
    } // end setView method

    /**
     * Closes the search and resets the view to display all accounts.
     * @param event The action event triggering the method call.
     */
    private void closeSearch(ActionEvent event){
        if (haveSearched) {
            setView(serverController.getStudents());
            accountSearch.getInputField().setText("");
            haveSearched = false;
        }
    } // end closeSearch method

    /**
     * Performs a search based on the input provided and displays the search results.
     * @param event The action event triggering the method call.
     */
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
    } // end performSearch method
} // end ManageAccountsPanel class
