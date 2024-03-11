package project.server;

import project.client.controller.ClientController;
import project.client.controller.ClientObserver;
import project.utilities.RMI.ClientRemoteMethods;

import project.utilities.referenceClasses.Authentication;
import project.utilities.referenceClasses.Book;
import project.utilities.referenceClasses.Response;
import project.utilities.utilityClasses.NotifyType;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.UUID;

public class ClientServant extends UnicastRemoteObject implements ClientRemoteMethods {

    private final HashMap<String, ClientObserver> clientsController;

    public ClientServant() throws RemoteException {
        super();
        clientsController = new HashMap<>();
    }

    @Override
    public Response<String> logIn(Authentication credential) {
        System.out.println("Client Request to log in");
        return new Response<>(true, "Hi");
    }

    @Override
    public Response<String> borrowBook(Book book) {
        System.out.println("Client Request to borrow a book");
        return null;
    }

    @Override
    public Response<String> removePending(Book book) {
        System.out.println("Client Request to remove a book pending");
        return null;
    }

    @Override
    public Response<String> returnBook(Book book) {
        System.out.println("Client Request to return a book");
        return null;
    }

    @Override
    public void registerClient(String id, ClientObserver clientObserver) {
        clientsController.put(id, clientObserver);
    }

    @Override
    public void unregisterClient(String id) {
        clientsController.remove(id);
    }

    @Override
    public HashMap<String, ClientObserver> getClients() {
        return clientsController;
    }

    @Override
    public Response<LinkedList<Book>> getBooks() {
        System.out.println("Client Request to get all the books");
        return null;
    }

    @Override
    public void notifyServer(NotifyType notifyType, ClientController controller) throws RemoteException {

    }

}
