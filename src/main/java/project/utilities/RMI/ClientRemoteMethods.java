package project.utilities.RMI;

import project.client.controller.ClientController;
import project.client.controller.ClientObserver;
import project.utilities.referenceClasses.*;
import project.utilities.utilityClasses.ServerActions;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;

public interface ClientRemoteMethods extends Remote {

     HashMap<String, ClientController> clients = new HashMap<>();

    /**
     * Handle login event
     *
     * @param credential The authentication credentials entered by the user
     */
    Response<Student> logIn(Authentication credential, ClientController clientObserver) throws RemoteException;

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


    HashMap<String, ClientController> getClients() throws RemoteException;
    void addController(String accountId, ClientController clientController) throws RemoteException;

    Response<LinkedList<Book>> getBooks() throws RemoteException;

    void notification(ServerActions serverActions) throws RemoteException;

    void logout(Student student) throws RemoteException;

   void sendMessage(String message, Student sender) throws RemoteException;


}
