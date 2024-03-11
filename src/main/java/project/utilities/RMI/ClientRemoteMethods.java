package project.utilities.RMI;

import project.client.controller.ClientController;
import project.client.controller.ClientObserver;
import project.utilities.referenceClasses.Authentication;
import project.utilities.referenceClasses.Book;
import project.utilities.referenceClasses.Response;
import project.utilities.utilityClasses.ServerActions;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.LinkedList;

public interface ClientRemoteMethods extends Remote {

    /**
     * Handle login event
     *
     * @param credential The authentication credentials entered by the user
     */
    Response<String> logIn(Authentication credential, ClientController clientObserver) throws RemoteException;

    /**
     * Handle borrowing a book event
     *
     * @param book The book to be borrowed
     */
    Response<String> borrowBook(Book book) throws RemoteException;


    /**
     * Handle removing a pending book event
     *
     * @param book The book to be removed from pending list
     */
    Response<String> removePending(Book book) throws RemoteException;

    /**
     * Handle returning a book event
     *
     * @param book The book to be returned
     */
    Response<String> returnBook(Book book) throws RemoteException;


    HashMap<String, ClientController> getClients() throws RemoteException;

    Response<LinkedList<Book>> getBooks() throws RemoteException;
    void notification(ServerActions serverActions);


}
