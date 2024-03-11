package project.utilities.RMI;

import project.utilities.referenceClasses.Account;
import project.utilities.referenceClasses.Book;
import project.utilities.referenceClasses.Response;
import project.utilities.referenceClasses.Student;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.LinkedList;

public interface ServerRemoteMethods extends Remote {

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

    Response<LinkedList<Book>> getBooks() throws RemoteException;
}
