package midproject.utilities.RMI;

import midproject.client.controller.ClientUpdateReceiver;
import midproject.server.controller.ServerUpdateReceiver;
import midproject.utilities.referenceClasses.*;
import midproject.utilities.utilityClasses.ClientActions;
import midproject.utilities.utilityClasses.ServerActions;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.LinkedList;

public interface GlobalRemoteMethods extends Remote {

    Response<Student> logIn(Authentication credential, ClientUpdateReceiver clientUpdateReceiver) throws RemoteException;

    /**
     * Handle borrowing a book event
     *
     * @param book The book to be borrowed
     */
    Response<String> borrowBook(Book book, Student student) throws RemoteException;


    /**
     * Handle removing a pending book event
     *
     * @param book The book to be removed from pending list
     */
    Response<String> removePending(Book book, Student student) throws RemoteException;

    /**
     * Handle returning a book event
     *
     * @param book The book to be returned
     */
    Response<String> returnBook(Book book, Student student) throws RemoteException;

    /**
     * Retrieves a list of books based on the provided condition.
     */
    Response<LinkedList<Book>> getBooks(boolean isClient) throws RemoteException;

    /**
     * Logs out the specified student.
     */
    void logout(Student student) throws RemoteException;

    /**
     * Sends a message with the specified content from the sender student.
     */
    void sendMessage(String message, Student sender) throws RemoteException;

    /**
     * Sends a notification to the server indicating client actions.
     */
    void sendNotificationToServer(ClientActions clientActions) throws RemoteException;

    /**
     * Accepts a book by the specified student.
     */
    Response<String> acceptBook(Book book, Student student) throws RemoteException;

    /**
     * Retrieves a book by the specified student.
     */
    Response<String> retrieveBook(Book book, Student student) throws RemoteException;

    /**
     * Retrieves a pending return book by the specified student.
     */
    Response<String> retrievePendingReturnBook(Book book, Student student) throws RemoteException;

    /**
     * Edits the information of a book.
     */
    Response<String> editBook(Book book) throws RemoteException;

    /**
     * Deletes the specified book from the system.
     */
    Response<String> deleteBook(Book book) throws RemoteException;

    /**
     * Cancels a pending operation for the specified book by the student.
     */
    Response<String> cancelPending(Book book, Student student) throws RemoteException;

    /**
     * Creates a new book in the system.
     */
    Response<String> createNewBook(Book book) throws RemoteException;

    /**
     * Broadcasts a message to all users.
     */
    Response<String> broadcastMessage(String message) throws RemoteException;

    /**
     * Broadcasts a message to a specific receiver.
     */
    Response<String> broadcastMessage(String message, String receiver) throws RemoteException;

    /**
     * Bans the specified account from the system.
     */
    Response<String> banAccount(Student account) throws RemoteException;

    /**
     * Unbans the specified account.
     */
    Response<String> unbanAccount(Student account) throws RemoteException;

    /**
     * Deletes the specified account from the system.
     */
    Response<String> deleteAccount(Student account) throws RemoteException;

    /**
     * Edits the information of the specified account.
     */
    Response<String> editAccount(Student account) throws RemoteException;

    /**
     * Creates a new account in the system.
     */
    Response<String> createAccount(Account account) throws RemoteException;

    /**
     * Changes the password of the specified account.
     */
    Response<String> changeUserPassword(Student account, String newPassword) throws RemoteException;

    /**
     * Sends a notification to the client indicating server actions.
     */
    void sendNotificationToClient(ServerActions serverActions) throws RemoteException;

    /**
     * Sends a notification to the specified client indicating server actions.
     */
    void sendNotificationToClient(ServerActions serverActions, Student student) throws RemoteException;

    /**
     * Retrieves a list of books pending for borrowing.
     */
    Response<LinkedList<Book>> getPendingBorrowingBooks() throws RemoteException;

    /**
     * Retrieves a list of currently borrowed books.
     */
    Response<LinkedList<Book>> getCurrentBorrowedBooks() throws RemoteException;

    /**
     * Retrieves a list of previously borrowed books.
     */
    Response<LinkedList<Book>> getPreviousBorrowedBooks() throws RemoteException;

    /**
     * Retrieves a list of available books.
     */
    Response<LinkedList<Book>> getAvailableBooks() throws RemoteException;

    /**
     * Retrieves a list of unavailable books.
     */
    Response<LinkedList<Book>> getUnavailableBooks() throws RemoteException;

    /**
     * Retrieves a list of books pending for returning.
     */
    Response<LinkedList<Book>> getPendingReturningBooks() throws RemoteException;

    /**
     * Retrieves a list of all accounts.
     */
    Response<LinkedList<Account>> getAccounts() throws RemoteException;

    /**
     * Retrieves a list of student accounts.
     */
    Response<LinkedList<Student>> getStudentAccounts() throws RemoteException;

    /**
     * Registers a server controller for receiving updates.
     */
    void registerServerController(ServerUpdateReceiver serverUpdateReceiver)throws RemoteException;

}
