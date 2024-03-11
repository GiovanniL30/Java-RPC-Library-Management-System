package project.utilities.RMI;

import project.client.controller.ClientController;
import project.client.controller.ClientObserver;
import project.utilities.referenceClasses.*;
import project.utilities.utilityClasses.NotifyType;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.UUID;

public interface ClientRemoteMethods extends Remote {

    /**
     * Handle login event
     * @param credential The authentication credentials entered by the user
     */
    Response<String> logIn(Authentication credential) throws RemoteException;

    /**
     * Handle borrowing a book event
     * @param book The book to be borrowed
     */
    Response<String> borrowBook(Book book) throws RemoteException;


    /**
     * Handle removing a pending book event
     * @param book The book to be removed from pending list
     */
    Response<String> removePending(Book book) throws RemoteException;

    /**
     * Handle returning a book event
     * @param book The book to be returned
     */
    Response<String> returnBook(Book book) throws RemoteException;

    void registerClient(String id, ClientObserver clientObserver) throws RemoteException;
    void unregisterClient(String id) throws RemoteException;

    HashMap<String, ClientObserver> getClients() throws RemoteException;

    Response<LinkedList<Book>> getBooks() throws RemoteException;

    void notifyServer(NotifyType notifyType, ClientController clientController) throws RemoteException;

}
