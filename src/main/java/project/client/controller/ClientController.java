package project.client.controller;

import project.server.Server;
import project.utilities.RMI.ClientRemoteMethods;
import project.utilities.referenceClasses.Authentication;
import project.utilities.referenceClasses.Book;
import project.utilities.utilityClasses.NotifyType;


import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.UUID;

public class ClientController implements ClientObserver, Serializable {

    private final ClientRemoteMethods clientRemoteMethods;
    private final String uuid;

    public ClientController() {

        try {

            clientRemoteMethods = (ClientRemoteMethods) LocateRegistry.getRegistry(1099).lookup("ClientRemote");
            uuid = UUID.randomUUID().toString();
            clientRemoteMethods.registerClient(uuid, this);

        }catch (RemoteException | NotBoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void logIn(Authentication credential) {

        try {
          clientRemoteMethods.logIn(credential).getPayload();
        }catch (RemoteException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void borrowBook(Book book) {

        try {
            clientRemoteMethods.borrowBook(book);
            clientRemoteMethods.notifyServer(NotifyType.SERVER_ACCEPT_PENDING_BOOK, this);
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

    public void notifyClient(NotifyType notifyType){

        switch (notifyType) {
            case SERVER_ACCEPT_PENDING_BOOK -> System.out.println("Server Accepted Booking");
        }

    }

}
