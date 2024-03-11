package project.client.controller;

import project.utilities.RMI.ClientRemoteMethods;
import project.utilities.RMI.ServerRemoteMethods;
import project.utilities.referenceClasses.Authentication;
import project.utilities.referenceClasses.Book;

import java.io.Serializable;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class ClientController implements ClientObserver, Serializable {

    private final ClientRemoteMethods clientRemoteMethods;

    public ClientController() {

        try {
            clientRemoteMethods = (ClientRemoteMethods) LocateRegistry.getRegistry(1099).lookup("ClientRemote");
        } catch (RemoteException | NotBoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void logIn(Authentication credential) {

        try {
            clientRemoteMethods.logIn(credential, this);
            serverRemoteMethods().notification();
        } catch (RemoteException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void borrowBook(Book book) {

        try {
            clientRemoteMethods.borrowBook(book);


        } catch (RemoteException e) {

        }

    }

    @Override
    public void removePending(Book book) {
        try {
            clientRemoteMethods.removePending(book);
        } catch (RemoteException e) {

        }

    }

    @Override
    public void returnBook(Book book) {
        try {
            clientRemoteMethods.returnBook(book);

        } catch (RemoteException e) {

        }

    }

    private ServerRemoteMethods serverRemoteMethods() {

        try {
            return (ServerRemoteMethods) LocateRegistry.getRegistry(1099).lookup("ServerRemote");
        } catch (NotBoundException | RemoteException e) {
            throw new RuntimeException(e);
        }

    }


}
