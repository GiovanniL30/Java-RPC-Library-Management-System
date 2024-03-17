package project.server.controller;

import project.utilities.RMI.GlobalRemoteMethods;
import project.utilities.referenceClasses.Account;
import project.utilities.referenceClasses.Book;
import project.utilities.referenceClasses.Response;
import project.utilities.referenceClasses.Student;
import project.utilities.utilityClasses.ClientActions;

import javax.swing.*;


import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class ServerController implements ServerObserver, Serializable {

    private  GlobalRemoteMethods serverMethods;

    @Override
    public void acceptBook(Book book, Student student) {

        try {
            Response<String> response = serverMethods.acceptBook(book, student);
            if (response.isSuccess()) {
                student.getPendingBooks().remove(book);
            } else {
                JOptionPane.showMessageDialog(null, response.getPayload());
            }

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

    @Override
    public void updateView(ClientActions clientActions) {
        System.out.println("I will now be updating my view action = " + clientActions.toString());
    }

    public void setServerMethods() {
        try {
            serverMethods = (GlobalRemoteMethods) LocateRegistry.getRegistry(1099).lookup("server");
        }catch (RemoteException | NotBoundException e) {
            throw new RuntimeException(e);
        }
    }
}
