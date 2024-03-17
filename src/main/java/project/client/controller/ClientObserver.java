package project.client.controller;

import project.client.utility.ClientPanels;
import project.utilities.referenceClasses.Authentication;
import project.utilities.referenceClasses.Book;
import project.utilities.referenceClasses.Student;
import project.utilities.utilityClasses.ClientActions;
import project.utilities.utilityClasses.ServerActions;

public interface ClientObserver {

    /**
     * Handle login event
     * @param credential The authentication credentials entered by the user
     */
    void logIn(Authentication credential);

    /**
     * Handle borrowing a book event
     * @param book The book to be borrowed
     */
    void borrowBook(Book book);


    /**
     * Handle removing a pending book event
     * @param book The book to be removed from pending list
     */
    void removePending(Book book);

    /**
     * Handle returning a book event
     * @param book The book to be returned
     */
    void returnBook(Book book);

    void updateView(ServerActions serverActions);


    void changeFrame(ClientPanels clientPanels);

    void logout();

    void receiveMessage(String message, Student sender);

}
