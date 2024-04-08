package midproject.server.views.panels;

import midproject.server.controller.ServerObserver;
import midproject.server.views.components.ClickableText;
import midproject.server.views.components.SubHeader;
import midproject.server.views.components.viewBookPanel.ViewBooksList;
import midproject.server.views.utility.ServerPanels;
import midproject.utilities.referenceClasses.Book;
import midproject.utilities.utilityClasses.FontFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * Represents the panel for viewing books in the server GUI.
 * It allows searching, viewing, and categorizing books based on different criteria.
 */

public class ViewBookPanel extends JPanel {
    private final ServerObserver serverObserver;
    private final SubHeader subHeader;
    private boolean haveSearched = false;
    private ViewBooksList viewBooksList;

    /**
     * Constructs the ViewBookPanel with the specified server observer.
     * @param serverObserver The server observer to handle actions.
     */
    public ViewBookPanel(ServerObserver serverObserver) {
        this.serverObserver = serverObserver;

        setLayout(new BorderLayout(0, 20));

        subHeader = new SubHeader(new ClickableText(ServerPanels.All_BOOKS_PANEL.getDisplayName(), 0, 50, FontFactory.newPoppinsBold(14)), new ClickableText(ServerPanels.AVAILABLE_BOOKS_PANEL.getDisplayName(), 0, 50, FontFactory.newPoppinsBold(14)), new ClickableText(ServerPanels.UNAVAILABLE_BOOKS_PANEL.getDisplayName(), 0, 50, FontFactory.newPoppinsBold(14)), serverObserver);
        viewBooksList = new ViewBooksList(serverObserver.getBooks(), this.serverObserver);
        setBackground(Color.white);

        add(subHeader, BorderLayout.NORTH);
        add(viewBooksList, BorderLayout.CENTER);

        subHeader.getSearchBar().getCancelSearch().addActionListener(this::closeSearch);
        subHeader.getSearchBar().getSearchButton().addActionListener(this::performSearch);
    } // end ViewBookPanel constructor

    /**
     * Gets the sub header of the panel.
     */
    public SubHeader getSubHeader() {
        return subHeader;
    } // end getSubHeader

    /**
     * Sets the view with the specified list of books.
     * @param books The list of books to be displayed.
     */
    public synchronized void setView(LinkedList<Book> books) {
        remove(1);
        viewBooksList = new ViewBooksList(books, this.serverObserver);
        add(viewBooksList);
        revalidate();
        repaint();
    } // end setView method

    /**
     * Closes the search and resets the view to display all books.
     * @param event The action event triggering the method call.
     */
    private void closeSearch(ActionEvent event){
        if(haveSearched) {
            if (subHeader.getCurrentButton().equals(subHeader.getButton1())) {
                setView(serverObserver.getBooks());
                subHeader.getSearchBar().getInputField().setText("");
                haveSearched = false;
            }
            if (subHeader.getCurrentButton().equals(subHeader.getButton2())) {
                setView(serverObserver.getAvailableBooks());
                subHeader.getSearchBar().getInputField().setText("");
                haveSearched = false;
            }
            if (subHeader.getCurrentButton().equals(subHeader.getButton3())) {
                setView(serverObserver.getUnavailableBooks());
                subHeader.getSearchBar().getInputField().setText("");
                haveSearched = false;
            }
        }
    } // end closeSearch method

    /**
     * Performs a search based on the input provided and displays the search results.
     * @param event The action event triggering the method call.
     */
    private void performSearch(ActionEvent event) {
        String searchInput = subHeader.getSearchBar().getSearch();

        if(searchInput == null){
            subHeader.enableError("Enter a Title or an Author");
            return;
        }

        if(searchInput.trim().isEmpty()) {
            subHeader.enableError("Enter a Title or an Author");
            return;
        }

        LinkedList<Book> searchedBooks = new LinkedList<>();
        if (subHeader.getCurrentButton().equals(subHeader.getButton1())) {
            searchedBooks = serverObserver.getBooks().stream().filter(book -> book.getBookTitle().toLowerCase().contains(searchInput.toLowerCase())
                    || book.getAuthor().toLowerCase().contains(searchInput.toLowerCase())
            ).collect(Collectors.toCollection(LinkedList::new));
        }

        if (subHeader.getCurrentButton().equals(subHeader.getButton2())) {
            searchedBooks = serverObserver.getAvailableBooks().stream().filter(book -> book.getBookTitle().toLowerCase().contains(searchInput.toLowerCase())
                    || book.getAuthor().toLowerCase().contains(searchInput.toLowerCase())
            ).collect(Collectors.toCollection(LinkedList::new));
        }

        if (subHeader.getCurrentButton().equals(subHeader.getButton3())) {
            searchedBooks = serverObserver.getUnavailableBooks().stream().filter(book -> book.getBookTitle().toLowerCase().contains(searchInput.toLowerCase())
                    || book.getAuthor().toLowerCase().contains(searchInput.toLowerCase())
            ).collect(Collectors.toCollection(LinkedList::new));
        }
        haveSearched = true;
        setView(searchedBooks);
    } // end performSearch method
} // end ViewBookPanel class
