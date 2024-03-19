package project.server.controller;

import project.client.controller.ClientController;
import project.utilities.referenceClasses.Account;
import project.utilities.referenceClasses.Book;
import project.utilities.referenceClasses.Response;
import project.utilities.referenceClasses.Student;
import project.utilities.utilityClasses.ClientActions;

import java.rmi.RemoteException;
import java.util.LinkedList;

public interface ServerObserver {

    void acceptBook(Book book, Student student);

    void retrieveBook(Book book, Student student);

    void editBook(Book book);

    boolean deleteBook(Book book);

    void cancelPending(Book book, Student student);

    void createNewBook(Book book);

    //Methods for showing book information

    LinkedList<Book> viewAvailableBooks();

    LinkedList<Book> viewUnavailableBooks();

    LinkedList<Book> viewPendingBorrowingBooks();

    LinkedList<Book> viewCurrentBorrowedBooks();

    LinkedList<Book> viewPreviousBorrowedBooks();

    LinkedList<Book> viewPendingReturningBooks();

    // Methods for handling account-related actions
    void broadcastMessage(String message);

    void banAccount(Account account);

    void unbanAccount(Account account);

    void deleteAccount(Student account);

    void createAccount(Account account);

    void changeUserPassword(Account account, String newPassword);

    void updateView(ClientActions clientActions);


}
