package project.server;

import project.client.controller.ClientController;
import project.client.controller.ClientObserver;
import project.utilities.RMI.ClientRemoteMethods;
import project.utilities.model.BookModel;
import project.utilities.referenceClasses.Authentication;
import project.utilities.referenceClasses.Book;
import project.utilities.referenceClasses.Response;
import project.utilities.utilityClasses.ServerActions;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.LinkedList;

public class ClientServant extends UnicastRemoteObject implements ClientRemoteMethods {

    private final HashMap<String, ClientController> clientsController;
    private final BookModel bookModel;

    public ClientServant(BookModel bookModel) throws RemoteException {
        super();
        this.bookModel = bookModel;
        clientsController = new HashMap<>();
    }

    @Override
    public Response<String> logIn(Authentication credential, ClientController clientObserver) {
        System.out.println("Client Request to log in");

        //clientsController.put(credential.getUserName(), clientObserver);
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
    public HashMap<String, ClientController> getClients() {
        return clientsController;
    }

    @Override
    public Response<LinkedList<Book>> getBooks() {
        System.out.println("Client Request to get all the books");
        return new Response<>(true, bookModel.getBooks());
    }

    @Override
    public void notification(ServerActions serverActions) {

    }

}
