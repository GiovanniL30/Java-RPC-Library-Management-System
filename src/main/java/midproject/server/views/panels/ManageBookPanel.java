package midproject.server.views.panels;

import midproject.server.controller.ServerObserver;
import midproject.server.views.components.ClickableText;
import midproject.server.views.components.SubHeader;
import midproject.server.views.components.manageBookPanel.*;
import midproject.server.views.utility.ServerPanels;
import midproject.utilities.referenceClasses.Book;
import midproject.utilities.referenceClasses.Student;
import midproject.utilities.utilityClasses.FontFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * Represents the panel for managing books in the server GUI.
 * It allows searching, viewing, and managing books based on different categories.
 */

public class ManageBookPanel extends JPanel {
    private final LinkedList<Student> students;
    private final ServerObserver serverObserver;
    private SubHeader subHeader;
    private boolean haveSearched = false;
    private ManageBookList manageBookList;

    /**
     * Constructs the ManageBookPanel with the specified list of students and server observer.
     * @param students       The list of students.
     * @param serverObserver The server observer to handle actions.
     */
    public ManageBookPanel( LinkedList<Student> students, ServerObserver serverObserver) {
        this.students = students;
        this.serverObserver = serverObserver;

        setBackground(Color.WHITE);
        setLayout(new BorderLayout(0, 20));

        subHeader = new SubHeader(new ClickableText(ServerPanels.PENDING_BORROW_PANEL.getDisplayName(), 0, 50, FontFactory.newPoppinsBold(14)),
                new ClickableText(ServerPanels.PENDING_RETURN_PANEL.getDisplayName(), 0, 50, FontFactory.newPoppinsBold(14)),
                new ClickableText(ServerPanels.BORROWED_PANEL.getDisplayName(), 0, 50, FontFactory.newPoppinsBold(14)),
                serverObserver);

        manageBookList = new ManageBookList(students, serverObserver, ServerPanels.PENDING_BORROW_PANEL);

        add(subHeader, BorderLayout.NORTH);
        add(manageBookList, BorderLayout.CENTER);

        subHeader.getSearchBar().getCancelSearch().addActionListener(this::closeSearch);
        subHeader.getSearchBar().getSearchButton().addActionListener(this::performSearch);
    } // end ManageBookPanel constructor

    /**
     * Sets the ManageBookList panel with the specified list of students and panel category.
     * @param students    The list of students.
     * @param serverPanels The panel category.
     */
    public void setManageBookList( LinkedList<Student> students, ServerPanels serverPanels) {
        manageBookList = new ManageBookList(students, serverObserver, serverPanels);
        remove(1);
        add(manageBookList);
        revalidate();
        repaint();
    } // end setManageBookList

    /**
     * Retrieves the SubHeader component.
     */
    public SubHeader getSubHeader() {
        return subHeader;
    }

    /**
     * Closes the search and resets the view to display all books.
     * @param event The action event triggering the method call.
     */
    private void closeSearch(ActionEvent event){
        if(haveSearched) {
            if (subHeader.getCurrentButton().equals(subHeader.getButton1())) {
                setManageBookList(serverObserver.getStudents(), ServerPanels.PENDING_BORROW_PANEL);
                subHeader.getSearchBar().getInputField().setText("");
                haveSearched = false;
            }
            if (subHeader.getCurrentButton().equals(subHeader.getButton2())) {
                setManageBookList(serverObserver.getStudents(), ServerPanels.PENDING_RETURN_PANEL);
                subHeader.getSearchBar().getInputField().setText("");
                haveSearched = false;
            }
            if (subHeader.getCurrentButton().equals(subHeader.getButton3())) {
                setManageBookList(serverObserver.getStudents(), ServerPanels.BORROWED_PANEL);
                subHeader.getSearchBar().getInputField().setText("");
                haveSearched = false;
            }
        }
    } // end closeSearch method

    /**
     * Performs a search based on the input provided and displays the search results.
     * @param e The action event triggering the method call.
     */
    private void performSearch(ActionEvent e) {
        String search = subHeader.getSearchBar().getSearch();

        if(search.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter something", "Empty Search", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (subHeader.getCurrentButton().equals(subHeader.getButton1())) {
            manageBookList.updateView(getStudents(search, ServerPanels.PENDING_BORROW_PANEL), ServerPanels.PENDING_BORROW_PANEL);
        } else if (subHeader.getCurrentButton().equals(subHeader.getButton2())) {
             manageBookList.updateView(getStudents(search, ServerPanels.PENDING_RETURN_PANEL), ServerPanels.PENDING_RETURN_PANEL);
        }else if (subHeader.getCurrentButton().equals(subHeader.getButton3())) {
            manageBookList.updateView(getStudents(search, ServerPanels.BORROWED_PANEL), ServerPanels.BORROWED_PANEL);
        }
        haveSearched = true;
    } // end performSearch method

    /**
     * Retrieves a list of students based on the search input and panel category.
     * @param search       The search input.
     * @param panels       The panel category.
     * @return             A list of students matching the search criteria.
     */
    private LinkedList<Student> getStudents(String search, ServerPanels panels) {
        LinkedList<Student> filtered;
        filtered =  students.stream().filter(student -> student.getAccount().getFirstName().concat(student.getAccount().getLastName()).toLowerCase().contains(search)).collect(Collectors.toCollection(LinkedList::new));

        if (filtered.isEmpty()) {
            for (Student student : serverObserver.getStudents()) {
                LinkedList<Book> booksToRemove = new LinkedList<>();

                switch (panels) {
                    case PENDING_BORROW_PANEL -> {
                        for (Book book : student.getPendingBooks()) {
                            if (book.getBookTitle().toLowerCase().contains(search)) {
                                filtered.add(student);
                            }else {
                                booksToRemove.add(book);
                            }
                        }
                        student.getPendingBooks().removeAll(booksToRemove);
                    }

                    case PENDING_RETURN_PANEL -> {
                        for (Book book : student.getPendingReturnBook()) {
                            if (book.getBookTitle().toLowerCase().contains(search)) {
                                filtered.add(student);
                            } else {
                                booksToRemove.add(book);
                            }
                        }
                        student.getPendingReturnBook().removeAll(booksToRemove);
                    }

                    case BORROWED_PANEL -> {
                        for (Book book : student.getBorrowedBooks()) {
                            if (book.getBookTitle().toLowerCase().contains(search)) {
                                filtered.add(student);
                            } else {
                                booksToRemove.add(book);
                            }
                        }
                        student.getBorrowedBooks().removeAll(booksToRemove);
                    }
                }
            }
        }
        return filtered;
    } // end getStudents method
} // end ManageBookPanel class