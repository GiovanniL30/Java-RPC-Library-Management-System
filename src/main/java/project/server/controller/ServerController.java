package project.server.controller;

import project.server.views.utility.ServerPanels;
import project.utilities.RMI.GlobalRemoteMethods;
import project.utilities.referenceClasses.Account;
import project.utilities.referenceClasses.Book;
import project.utilities.referenceClasses.Response;
import project.utilities.referenceClasses.Student;
import project.utilities.utilityClasses.ClientActions;
import project.utilities.viewComponents.Loading;

import javax.swing.*;


import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.LinkedList;

public class ServerController implements ServerObserver, Serializable {

    private  GlobalRemoteMethods serverMethods;
    private Loading loading;

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
    public LinkedList<Book> viewAvailableBooks() {
        try {
            Response<LinkedList<Book>> response = serverMethods.viewAvailableBooks();
            if (response.isSuccess()) {
                return response.getPayload();
            }
        } catch (RemoteException e) {
            return new LinkedList<>();
        }
        return new LinkedList<>();
    }


    @Override
    public LinkedList<Book> viewUnavailableBooks() {
        try {
            Response<LinkedList<Book>> response = serverMethods.viewUnavailableBooks();
            if (response.isSuccess()) {
                return response.getPayload();
            }
        } catch (RemoteException e) {
            return new LinkedList<>();
        }
        return new LinkedList<>();
    }


    @Override
    public LinkedList<Book> viewCurrentBorrowedBooks() {
        try {
            Response<LinkedList<Book>> response = serverMethods.viewCurrentBorrowedBooks();
            if (response.isSuccess()) {
                return response.getPayload();
            }
        } catch (RemoteException e) {
            return new LinkedList<>();
        }
        return new LinkedList<>();
    }


    @Override
    public LinkedList<Book> viewPreviousBorrowedBooks() {
        try {
            Response<LinkedList<Book>> response = serverMethods.viewPreviousBorrowedBooks();
            if (response.isSuccess()) {
                return response.getPayload();
            }
        } catch (RemoteException e) {
            return new LinkedList<>();
        }
        return new LinkedList<>();
    }


    @Override
    public LinkedList<Book> viewPendingBorrowingBooks() {
        try {
            Response<LinkedList<Book>> response = serverMethods.viewPendingBorrowingBooks();
            if (response.isSuccess()) {
                return response.getPayload();
            }
        } catch (RemoteException e) {
            return new LinkedList<>();
        }
        return new LinkedList<>();
    }

    @Override
    public LinkedList<Book> viewPendingReturningBooks() {
        try {
            Response<LinkedList<Book>> response = serverMethods.viewPendingReturningBooks();
            if (response.isSuccess()) {
                return response.getPayload();
            }
        } catch (RemoteException e) {
            return new LinkedList<>();
        }
        return new LinkedList<>();
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

    public void changeFrame(ServerPanels serverPanels) {

        switch (serverPanels) {

            case HOME_PANEL -> {
                SwingWorker<Void, Void> worker = new SwingWorker<>() {
                    @Override
                    protected Void doInBackground() {

                        return null;
                    }

                    @Override
                    protected void done() {

                        loading.setVisible(false);

                    }
                };
                worker.execute();

                loading.setVisible(true);
            }
            case VIEW_PANEL -> {

            }
            case MANAGE_PANEL -> {

            }
            case ACCOUNTS_PANEL -> {

            }
        }

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
