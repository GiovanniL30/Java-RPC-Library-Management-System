package project.server.controller;

import project.client.controller.ClientController;
import project.server.views.utility.ServerPanels;
import project.utilities.referenceClasses.Account;
import project.utilities.referenceClasses.Book;
import project.utilities.referenceClasses.Response;
import project.utilities.referenceClasses.Student;
import project.utilities.utilityClasses.ClientActions;

import java.rmi.RemoteException;
import java.util.LinkedList;

/**
 * Interface for the server methods
 */

public interface ServerObserver {
    /**
     * Handles the changing of frames
     */
    void changeFrame(ServerPanels serverPanels);

    /**
     * Handles the accepting of books
     */
    void acceptBook(Book book, Student student);

    /**
     * Handles the retrieving of books
     */
    void retrieveBook(Book book, Student student);

    /**
     * Handles the retrieving of list of pending return books
     */
    void retrievePendingReturnBook(Book book, Student student);

    /**
     * Handles the editing of books
     */
    void editBook(Book book);

    /**
     * Handles the opening of frame of editing books
     */
    void openBookEditor(Book book);

    /**
     * Handles the deletion of books
     */
    boolean deleteBook(Book book);

    /**
     * Handles the declining requests of pening borrowed books
     */
    void cancelPending(Book book, Student student);

    /**
     * Handles the creation of new books
     */
    void createNewBook(Book book);

    /**
     * Handles signup event
     */
    void openSignUp();

    //METHODS FOR SHOWING BOOK INFORMATION

    /**
     * Handles the retrieving of available books list
     */
    LinkedList<Book> getAvailableBooks();

    /**
     * Handles the retrieving of unavailable books list
     */
    LinkedList<Book> getUnavailableBooks();

    /**
     * Handles the retrieving of pending borrowing books list
     */
    LinkedList<Book> getPendingBorrowingBooks();

    /**
     * Handles the retrieving of current borrowed books list
     */
    LinkedList<Book> getCurrentBorrowedBooks();

    /**
     * Handles the retrieving of previously borrowed books list
     */
    LinkedList<Book> getPreviousBorrowedBooks();

    /**
     * Handles the retrieving of pending return books list
     */
    LinkedList<Book> getPendingReturningBooks();

    /**
     * Handles the retrieving of all books
     */
    LinkedList<Book> getBooks();

    /**
     * Handles the retrieving of all students users
     */
    LinkedList<Student> getStudents();

    // METHODS FOR HANDLING ACCOUNT-RELATED ACTIONS
    /**
     * Handles the action for broadcasting a message to certain user
     */
    void broadcastMessage(String message, String recipient);

    /**
     * Handles the action for broadcasting a message to all users
     */
    void broadcastMessageToAll(String message);

    /**
     * Handles the action for banning an account of a user
     */
    void banAccount(Student account);

    /**
     * Handles the action for unbanning an account of a user
     */
    void unbanAccount(Student account);

    /**
     * Handles the action for deleting an account of a user
     */
    void deleteAccount(Student account);

    /**
     * Handles the action for creating an account
     */
    void createAccount(Account account);

    /**
     * Handles the action for changing the password of a user
     */
    void changeUserPassword(Student account);

    /**
     * Handles the action for updating the view in the server side
     */
    void updateView(ClientActions clientActions);

    /**
     * Handles the action for accepting the request of a user to borrow a book
     */
    void acceptBook(Student student, Book book);
} // end of ServerController class
