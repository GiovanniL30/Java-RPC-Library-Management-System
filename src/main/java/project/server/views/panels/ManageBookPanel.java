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
import java.util.LinkedList;
import java.util.stream.Collectors;

public class ManageBookPanel extends JPanel {
    private final LinkedList<Book> books;
    private final LinkedList<Student> students;
    private final ServerObserver serverObserver;
    private SubHeader subHeader;
    private boolean haveSearched = false;
    private ViewBooksList viewBooksList;

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

        manageBookList = new ManageBookList( students, serverObserver, ServerPanels.PENDING_BORROW_PANEL);

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
            if (subHeader.isButton1NotEnabled()) {
                setView(serverObserver.getPendingBorrowingBooks());
                subHeader.getSearchBar().getInputField().setText("");
                haveSearched = false;
            }
            if (subHeader.isButton2NotEnabled()) {
                setView(serverObserver.getPendingReturningBooks());
                subHeader.getSearchBar().getInputField().setText("");
                haveSearched = false;
            }
            if (subHeader.isButton3NotEnabled()) {
                setView(serverObserver.getCurrentBorrowedBooks());
                subHeader.getSearchBar().getInputField().setText("");
                haveSearched = false;
            }
        }

    }
    public synchronized void setView(LinkedList<Book> books) {
        remove(1);
        viewBooksList = new ViewBooksList(books, this.serverObserver);
        add(viewBooksList);
        revalidate();
        repaint();
    }
    private void performSearch(ActionEvent event) {
        String searchInput = subHeader.getSearchBar().getSearch();

        if (searchInput == null || searchInput.trim().isEmpty()) {
            subHeader.enableError("Enter something");
            return;
        }

        LinkedList<Book> searchedBooks = new LinkedList<>();

        if (subHeader.isButton1NotEnabled()) {
            searchedBooks = searchByTitleThenAuthorThenStudent(serverObserver.getPendingBorrowingBooks(), searchInput);
        } else if (subHeader.isButton2NotEnabled()) {
            searchedBooks = searchByTitleThenAuthorThenStudent(serverObserver.getPendingReturningBooks(), searchInput);
        } else if (subHeader.isButton3NotEnabled()) {
            searchedBooks = searchByTitleThenAuthorThenStudent(serverObserver.getCurrentBorrowedBooks(), searchInput);
        }

        haveSearched = true;
        setView(searchedBooks);
    }

    private LinkedList<Book> searchByTitleThenAuthorThenStudent(LinkedList<Book> books, String searchInput) {
        LinkedList<Book> searchedBooks;

        searchedBooks = books.stream()
                .filter(book -> book.getBookTitle().toLowerCase().contains(searchInput.toLowerCase()))
                .collect(Collectors.toCollection(LinkedList::new));

        if (searchedBooks.isEmpty()) {
            searchedBooks = books.stream()
                    .filter(book -> book.getAuthor().toLowerCase().contains(searchInput.toLowerCase()))
                    .collect(Collectors.toCollection(LinkedList::new));
        }

        if (searchedBooks.isEmpty()) {
            searchedBooks = books.stream()
                    .filter(book -> book.getCurrentBorrowers().stream()
                            .anyMatch(studentName -> studentName.toLowerCase().contains(searchInput.toLowerCase())))
                    .collect(Collectors.toCollection(LinkedList::new));
        }

        return searchedBooks;
    }
}