package project.client.controller;

import project.utilities.RMI.ClientRemoteMethods;
import project.utilities.referenceClasses.Authentication;
import project.utilities.referenceClasses.Book;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class ClientController implements ClientObserver {

    private final ClientRemoteMethods clientRemoteMethods;

    public ClientController() {

        try {
            clientRemoteMethods = (ClientRemoteMethods) LocateRegistry.getRegistry("localhost", 1099).lookup("client");
        }catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (NotBoundException e) {
            throw new RuntimeException(e);
        }


    }

    public static void main(String[] args) {
        ClientController clientController = new ClientController();
        clientController.logIn(new Authentication("", ""));
    }

    @Override
    public void logIn(Authentication credential) {

        try {
            System.out.println(clientRemoteMethods.logIn(credential).getPayload());
        }catch (RemoteException e) {
            System.out.println(e.getMessage());
        }



    }

    @Override
    public void borrowBook(Book book) {
        try {
            clientRemoteMethods.borrowBook(book);
        }catch (RemoteException e) {

        }

    }

    @Override
    public void removePending(Book book) {
        try {
            clientRemoteMethods.removePending(book);
        }catch (RemoteException e) {

        }

    }

    @Override
    public void returnBook(Book book) {
        try {
            clientRemoteMethods.returnBook(book);
        }catch (RemoteException e) {

        }

    }
}
