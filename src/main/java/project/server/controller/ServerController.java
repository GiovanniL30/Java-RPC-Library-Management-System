package project.server.controller;

import project.utilities.RMI.ClientRemoteMethods;
import project.utilities.RMI.ServerRemoteMethods;
import project.utilities.referenceClasses.Account;
import project.utilities.referenceClasses.Book;
import project.utilities.referenceClasses.Student;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.LinkedList;

public class ServerController implements ServerObserver {

    private final ServerRemoteMethods serverRemoteMethods;

    public ServerController() {

        try {
            serverRemoteMethods = (ServerRemoteMethods) LocateRegistry.getRegistry("localhost", 1098).lookup("server");
        }catch (RemoteException | NotBoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void acceptBook(Book book, Student student) {

        try {
            serverRemoteMethods.acceptBook(book, student);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void retrieveBook(Book book, Student student) {

    }

    @Override
    public void editBook(Book book) {

    }

    @Override
    public boolean deleteBook(Book book) {
        return false;
    }

    @Override
    public void cancelPending(Book book, Student student) {

    }

    @Override
    public void createNewBook(Book book) {

    }

    @Override
    public void broadcastMessage(String message) {

    }

    @Override
    public void banAccount(Account account) {

    }

    @Override
    public void unbanAccount(Account account) {

    }

    @Override
    public void deleteAccount(Student account) {

    }

    @Override
    public void createAccount(Account account) {

    }

    @Override
    public void changeUserPassword(Account account, String newPassword) {

    }
}
