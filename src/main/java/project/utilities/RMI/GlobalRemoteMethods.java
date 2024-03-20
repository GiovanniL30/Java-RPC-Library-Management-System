package project.utilities.RMI;

import project.client.RMI.ClientRemoteMethods;
import project.utilities.referenceClasses.*;
import project.utilities.utilityClasses.ClientActions;
import project.utilities.utilityClasses.ServerActions;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.LinkedList;

public interface GlobalRemoteMethods extends Remote {

    Response<Student> logIn(Authentication credential, ClientRemoteMethods clientRemoteMethods) throws RemoteException;

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


    Response<LinkedList<Book>> getBooks() throws RemoteException;

    void logout(Student student) throws RemoteException;

    void sendMessage(String message, Student sender) throws RemoteException;

    void sendNotificationToServer(ClientActions clientActions) throws RemoteException;

    Response<String> acceptBook(Book book, Student student) throws RemoteException;

    Response<String> retrieveBook(Book book, Student student) throws RemoteException;

    Response<String> editBook(Book book) throws RemoteException;

    Response<String> deleteBook(Book book) throws RemoteException;

    Response<String> cancelPending(Book book, Student student) throws RemoteException;

    Response<String> createNewBook(Book book) throws RemoteException;

    Response<String> broadcastMessage(String message) throws RemoteException;

    Response<String> banAccount(Account account) throws RemoteException;

    Response<String> unbanAccount(Account account) throws RemoteException;

    Response<String> deleteAccount(Student account) throws RemoteException;

    Response<String> createAccount(Account account) throws RemoteException;

    Response<String> changeUserPassword(Account account, String newPassword) throws RemoteException;
    void sendNotificationToClient(ServerActions serverActions) throws RemoteException;

    Response<LinkedList<Book>> getPendingBorrowingBooks() throws RemoteException;

    Response<LinkedList<Book>> getCurrentBorrowedBooks() throws RemoteException;

    Response<LinkedList<Book>> getPreviousBorrowedBooks() throws RemoteException;

    Response<LinkedList<Book>> getAvailableBooks() throws RemoteException;

    Response<LinkedList<Book>> getUnavailableBooks() throws RemoteException;

    Response<LinkedList<Book>> getPendingReturningBooks() throws RemoteException;


}
