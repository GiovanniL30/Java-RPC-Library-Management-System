package midproject.client.controller;

import midproject.client.utility.ClientPanels;
import midproject.utilities.referenceClasses.Authentication;
import midproject.utilities.referenceClasses.Book;
import midproject.utilities.referenceClasses.Student;
import midproject.utilities.utilityClasses.ServerActions;

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

} // end of ClientObserver
