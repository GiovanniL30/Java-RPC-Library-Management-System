package project.utilities.RMI;

import project.utilities.referenceClasses.Account;
import project.utilities.referenceClasses.Book;
import project.utilities.referenceClasses.Response;
import project.utilities.referenceClasses.Student;

import java.rmi.Remote;
import java.util.LinkedList;

public interface ServerRemoteMethods extends Remote {

    Response<String> acceptBook(String studentId, String bookId, String bookTitle);

    Response<String> retrieveBook(String studentId, String bookId, String bookTitle);

    Response<String> editBook(Book book);

    Response<String> deleteBook(String bookId);

    Response<String> cancelPending(String studentId, String bookId, String bookTitle);

    Response<String> createNewBook(Book book);

    Response<String> broadcastMessage(String message);

    Response<String> banAccount(Account account);

    Response<String> unbanAccount(Account account);

    Response<String> deleteAccount(Student account);

    Response<String> createAccount(Account account);

    Response<String> changeUserPassword(String accountId, String newPassword);

    Response<LinkedList<Book>> getBooks();
}
