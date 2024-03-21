package project.server.controller;

import project.server.views.LibrarianMainFrame;
import project.server.views.panels.HomePanel;
import project.server.views.panels.ManageAccountsPanel;
import project.server.views.panels.ViewBookPanel;
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
    private LibrarianMainFrame mainView;


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
    public LinkedList<Book> getAvailableBooks() {
        try {
            Response<LinkedList<Book>> response = serverMethods.getAvailableBooks();
            if (response.isSuccess()) {
                return response.getPayload();
            }
        } catch (RemoteException e) {
            return new LinkedList<>();
        }
        return new LinkedList<>();
    }


    @Override
    public LinkedList<Book> getUnavailableBooks() {
        try {
            Response<LinkedList<Book>> response = serverMethods.getUnavailableBooks();
            if (response.isSuccess()) {
                return response.getPayload();
            }
        } catch (RemoteException e) {
            return new LinkedList<>();
        }
        return new LinkedList<>();
    }


    @Override
    public LinkedList<Book> getCurrentBorrowedBooks() {
        try {
            Response<LinkedList<Book>> response = serverMethods.getCurrentBorrowedBooks();
            if (response.isSuccess()) {
                return response.getPayload();
            }
        } catch (RemoteException e) {
            return new LinkedList<>();
        }
        return new LinkedList<>();
    }


    @Override
    public LinkedList<Book> getPreviousBorrowedBooks() {
        try {
            Response<LinkedList<Book>> response = serverMethods.getPreviousBorrowedBooks();
            if (response.isSuccess()) {
                return response.getPayload();
            }
        } catch (RemoteException e) {
            return new LinkedList<>();
        }
        return new LinkedList<>();
    }


    @Override
    public LinkedList<Book> getPendingBorrowingBooks() {
        try {
            Response<LinkedList<Book>> response = serverMethods.getPendingBorrowingBooks();
            if (response.isSuccess()) {
                return response.getPayload();
            }
        } catch (RemoteException e) {
            return new LinkedList<>();
        }
        return new LinkedList<>();
    }

    @Override
    public LinkedList<Book> getPendingReturningBooks() {
        try {
            Response<LinkedList<Book>> response = serverMethods.getPendingReturningBooks();
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
        try {
            Response<String> response = serverMethods.broadcastMessage(message);
            if (response.isSuccess()) {
                System.out.println("Message broadcasted successfully.");
            } else {
                JOptionPane.showMessageDialog(null, response.getPayload());
            }
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void banAccount(Account account) {

    }

    @Override
    public void unbanAccount(Account account) {

    }

    @Override
    public void deleteAccount(Student account) {
        try {
            Response<String> response = serverMethods.deleteAccount(account);

            if (response.isSuccess()) {
                System.out.println("Account deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(null, response.getPayload());
            }
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createAccount(Account account) {
        try {
            Response<String> response = serverMethods.createAccount(account);

            if (response.isSuccess()) {
                System.out.println("Account created successfully.");
            } else {
                JOptionPane.showMessageDialog(null, response.getPayload());
            }
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void changeUserPassword(Account account, String newPassword) {

    }
    public LinkedList<Book> getBooks() {
        try {
            Response<LinkedList<Book>> response = serverMethods.getBooks();

            if (response.isSuccess()) {
                return response.getPayload();
            }

        } catch (RemoteException e) {
            return new LinkedList<>();
        }

        return new LinkedList<>();
    }

    @Override
    public void changeFrame(ServerPanels serverPanels) {
        mainView.getContentPane().remove(1);
        switch (serverPanels) {

            case HOME_PANEL -> {
                mainView.getContentPane().add(new HomePanel(this));
                mainView.getServerGuiHeader().setCurrentButton(mainView.getServerGuiHeader().getHome());
            }
            case VIEW_BOOKS_PANEL -> {
                mainView.getContentPane().add(new ViewBookPanel(this));
                mainView.getServerGuiHeader().setCurrentButton(mainView.getServerGuiHeader().getViewBooks());
            }
            case MANAGE_BOOK_PANEL -> {
                //TODO: fix
                JLabel label = new JLabel("Mange Books");
                JPanel panel = new JPanel();
                panel.add(label);
                mainView.getContentPane().add(panel);
                mainView.getServerGuiHeader().setCurrentButton(mainView.getServerGuiHeader().getManageBooks());
            }
            case MANAGE_ACCOUNTS_PANEL -> {

                try {
                    LinkedList<Student> students = serverMethods.getStudentAccounts().getPayload();
                    mainView.getContentPane().add(new ManageAccountsPanel(students, this));
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
                mainView.getServerGuiHeader().setCurrentButton(mainView.getServerGuiHeader().getAccounts());
            }
        }

        mainView.getContentPane().revalidate();
        mainView.getContentPane().repaint();
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
    public void setServerMainView(LibrarianMainFrame mainView) {
        this.mainView = mainView;
        loading = new Loading(this.mainView);
    }
    public LibrarianMainFrame getServerMainView() {
        return mainView;
    }
}
