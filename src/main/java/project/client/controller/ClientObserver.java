package project.client.controller;

import project.client.utility.ClientPanels;
import project.utilities.referenceClasses.Authentication;
import project.utilities.referenceClasses.Book;
import project.utilities.referenceClasses.Student;
import project.utilities.utilityClasses.ClientActions;
import project.utilities.utilityClasses.ServerActions;

/**
 * Interface for client methods
 */
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

    /**
     * Handle updating the client's views according to server's actions
     * @param serverActions action that the server had done
     */
    void updateView(ServerActions serverActions);

    /**
     * Handle updating the client's views
     * @param clientPanels available views for the client
     */
    void changeFrame(ClientPanels clientPanels);

    /**
     * Handle logging a client out
     */
    void logout();

    /**
     * Handle receiving a message from a client
     * @param message message to be sent
     * @param sender who the message came from
     */
    void receiveMessage(String message, Student sender);

    /**
     * Handle receiving a broadcast message from the server
     * @param message message to be broadcasted
     */
    void receiveBroadcast(String message);

}
