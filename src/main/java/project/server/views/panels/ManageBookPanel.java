package project.server.views.panels;

import project.server.controller.ServerController;
import project.server.controller.ServerObserver;
import project.server.views.components.ClickableText;
import project.server.views.components.ServerSearchBar;
import project.server.views.components.SubHeader;
import project.server.views.components.manageBookPanel.*;
import project.server.views.components.viewBookPanel.ViewBooksList;
import project.server.views.utility.ServerPanels;
import project.utilities.referenceClasses.Book;
import project.utilities.referenceClasses.Student;
import project.utilities.utilityClasses.ColorFactory;
import project.utilities.utilityClasses.FontFactory;
import project.utilities.viewComponents.Button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class ManageBookPanel extends JPanel {
    private final LinkedList<Book> books;
    private final LinkedList<Student> students;
    private final ServerObserver serverObserver;
    private SubHeader subHeader;
    private boolean haveSearched = false;
    private ViewBooksList viewBooksList;
    private ServerPanels serverPanels;
    private ManageBookList manageBookList;

    public ManageBookPanel(LinkedList<Book> books, LinkedList<Student> students, ServerObserver serverObserver) {
        this.books = books;
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
    }

    public void setManageBookList( LinkedList<Student> students, ServerPanels serverPanels) {
        manageBookList = new ManageBookList(students, serverObserver, serverPanels);
        remove(1);
        add(manageBookList);
        revalidate();
        repaint();

    }

    public SubHeader getSubHeader() {
        return subHeader;
    }
    private void closeSearch(ActionEvent event){

        if(haveSearched) {
            if (subHeader.getCurrentButton().equals(subHeader.getButton1())) {
                setManageBookList(students, ServerPanels.PENDING_BORROW_PANEL);
                subHeader.getSearchBar().getInputField().setText("");
                haveSearched = false;
            }
            if (subHeader.getCurrentButton().equals(subHeader.getButton2())) {
                setManageBookList(students, ServerPanels.PENDING_RETURN_PANEL);
                subHeader.getSearchBar().getInputField().setText("");
                haveSearched = false;
            }
            if (subHeader.getCurrentButton().equals(subHeader.getButton3())) {
                setManageBookList(students, ServerPanels.BORROWED_PANEL);
                subHeader.getSearchBar().getInputField().setText("");
                haveSearched = false;
            }
        }

    }

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
    }

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
                            }else {
                                booksToRemove.add(book);
                            }
                        }

                        student.getPendingReturnBook().removeAll(booksToRemove);

                    }
                    case BORROWED_PANEL -> {

                        for (Book book : student.getBorrowedBooks()) {
                            if (book.getBookTitle().toLowerCase().contains(search)) {
                                filtered.add(student);
                            }else {
                                booksToRemove.add(book);
                            }
                        }


                        student.getBorrowedBooks().removeAll(booksToRemove);

                    }
                }

            }
        }

        return filtered;
    }
}