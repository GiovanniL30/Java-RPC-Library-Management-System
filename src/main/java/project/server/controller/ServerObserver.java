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

public interface ServerObserver {

    void changeFrame(ServerPanels serverPanels);
    void acceptBook(Book book, Student student);

    void retrieveBook(Book book, Student student);
    void retrievePendingReturnBook(Book book, Student student);

    void editBook(Book book);
    void openBookEditor(Book book);

    boolean deleteBook(Book book);

    void cancelPending(Book book, Student student);

    void createNewBook(Book book);
    void openSignUp();

    //Methods for showing book information

    LinkedList<Book> getAvailableBooks();

    LinkedList<Book> getUnavailableBooks();

    LinkedList<Book> getPendingBorrowingBooks();

    LinkedList<Book> getCurrentBorrowedBooks();

    LinkedList<Book> getPreviousBorrowedBooks();

    LinkedList<Book> getPendingReturningBooks();
    LinkedList<Book> getBooks();
    LinkedList<Student> getStudents();

    // Methods for handling account-related actions
    void broadcastMessage(String message, String recipient);

    void broadcastMessageToAll(String message);

    void banAccount(Student account);

    void unbanAccount(Student account);

    void deleteAccount(Student account);


    void createAccount(Account account);

    void changeUserPassword(Student account);

    void updateView(ClientActions clientActions);
    void acceptBook(Student student, Book book);


}
