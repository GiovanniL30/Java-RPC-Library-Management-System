package project.client.controller;

import project.client.utility.ClientPanels;
import project.client.views.ClientMainView;
import project.client.views.Login;
import project.client.views.MainPanel;
import project.client.views.components.*;
import project.utilities.RMI.ClientRemoteMethods;
import project.utilities.RMI.ServerRemoteMethods;
import project.utilities.referenceClasses.*;
import project.utilities.utilityClasses.ClientActions;
import project.utilities.utilityClasses.ServerActions;
import project.utilities.viewComponents.Loading;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;

public class ClientController implements ClientObserver, Serializable {

    private final ClientRemoteMethods clientRemoteMethods;
    private ClientMainView mainView;
    private Loading loading;
    private Student loggedInAccount;
    private BookViewer bookViewer;

    public ClientController() {

        try {
            clientRemoteMethods = (ClientRemoteMethods) LocateRegistry.getRegistry(1099).lookup("ClientRemote");
        } catch (RemoteException | NotBoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void logIn(Authentication credential) {

        this.mainView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    clientRemoteMethods.logout(loggedInAccount);
                    System.exit(0);
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        new SwingWorker<Response<Student>, Void>() {
            @Override
            protected Response<Student> doInBackground() throws Exception {
                return clientRemoteMethods.logIn(credential, ClientController.this);
            }

            @Override
            protected void done() {
                try {

                    if (isDone()) {

                        Response<Student> response = get();

                        if (response.isSuccess()) {
                            loggedInAccount = response.getPayload();
                            mainView.getContentPane().removeAll();
                            MainPanel mainPanel = new MainPanel(ClientController.this);
                            mainView.setMainPanel(mainPanel);
                            mainView.getContentPane().add(mainPanel);
                            mainView.getContentPane().revalidate();
                            mainView.getContentPane().repaint();
                            System.out.println(loggedInAccount);
                        } else {

                            if (response.getPayload().getTotalBorrowedBooks() == 0) {
                                JOptionPane.showMessageDialog(mainView, "Invalid Username or Password");
                            } else {
                                JOptionPane.showMessageDialog(mainView, "Your account is already logged in on another machine");
                            }

                        }

                        loading.setVisible(false);

                    }

                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }
        }.execute();

        loading.setVisible(true);


    }

    @Override
    public void borrowBook(Book book) {
        bookViewer.setVisible(false);

        try {
            Response<String> response = clientRemoteMethods.borrowBook(book, loggedInAccount);

            if(response.isSuccess()) {
                changeFrame(ClientPanels.HOME_PANEL);
                loggedInAccount.getPendingBooks().add(book);
            }else {
                JOptionPane.showMessageDialog(mainView, response.getPayload());
            }

            serverRemoteMethods().notification(ClientActions.BORROW_BOOK);
        } catch (RemoteException e) {

        }

    }

    @Override
    public void removePending(Book book) {
        try {
            Response<String> response = clientRemoteMethods.removePending(book, loggedInAccount);

            //TODO: handle response

            serverRemoteMethods().notification(ClientActions.CANCEL_PENDING);
        } catch (RemoteException e) {

        }

    }

    @Override
    public void returnBook(Book book) {
        try {
            Response<String> response = clientRemoteMethods.returnBook(book, loggedInAccount);



            serverRemoteMethods().notification(ClientActions.RETURN_BOOK);
        } catch (RemoteException e) {

        }

    }

    @Override
    public void updateView(ServerActions serverActions) {

        switch (serverActions) {
            case EDIT_BOOK -> {

            }
            case BAN_ACCOUNT -> {

            }
            case DELETE_BOOK -> {

            }
            case UNBAN_ACCOUNT -> {

            }
            case ADDED_NEW_BOOK -> {

            }
            case DELETE_ACCOUNT -> {

            }
            case RETRIEVES_BOOK -> {

            }
            case CHANGE_PASSWORD -> {

            }
            case BROADCAST_MESSAGE -> {

            }
            case ACCEPT_BOOK_PENDING -> {

            }
            case CANCEL_BOOK_PENDING -> {

            }
            default -> {
            }
        }

    }


    @Override
    public void changeFrame(ClientPanels clientPanels) {

        switch (clientPanels) {

            case HOME_PANEL -> {
                SwingWorker<Void, Void> worker = new SwingWorker<>() {
                    @Override
                    protected Void doInBackground() {
                        mainView.setContentPanel(new HomePanel(getBooks(), ClientController.this));
                        return null;
                    }

                    @Override
                    protected void done() {
                        mainView.getMenu().setCurrentButton(mainView.getMenu().getHomeButton());
                        loading.setVisible(false);

                    }
                };
                worker.execute();

                loading.setVisible(true);
            }
            case ACCOUNT_PANEL -> {
                mainView.setContentPanel(new AccountPanel(loggedInAccount));
                mainView.getMenu().setCurrentButton(mainView.getMenu().getAccount());
            }
            case PENDING_PANEL -> {
                mainView.setContentPanel(new BookListPanel(loggedInAccount.getPendingBooks(), true));
                mainView.getMenu().setCurrentButton(mainView.getMenu().getPendingBooks());

            }
            case BORROWED_PANEL -> {
                mainView.setContentPanel(new BookListPanel(loggedInAccount.getBorrowedBooks(), false));
                mainView.getMenu().setCurrentButton(mainView.getMenu().getBorrowedBooks());

            }
        }

    }

    @Override
    public void logout() {

        try {
            clientRemoteMethods.logout(loggedInAccount);
            loggedInAccount = new Student(new Account("", "", "", "", "", ""), 0, null, null);
            mainView.getContentPane().removeAll();
            Login login = new Login(new Dimension(ClientMainView.FRAME_WIDTH, 900));
            login.addClickEvent(this);
            mainView.getContentPane().add(login);
            mainView.revalidate();
            mainView.repaint();
        } catch (RemoteException ex) {
            throw new RuntimeException(ex);
        }

    }

    public void openBook(Book book){
        bookViewer = new BookViewer(mainView, book, loggedInAccount,this);
        bookViewer.setVisible(true);
    }

    public LinkedList<Book> getBooks() {
        try {
            Response<LinkedList<Book>> response = clientRemoteMethods.getBooks();

            if (response.isSuccess()) {
                return response.getPayload();
            }

            serverRemoteMethods().notification(ClientActions.RETURN_BOOK);
        } catch (RemoteException e) {
            return new LinkedList<>();
        }

        return new LinkedList<>();
    }

    public void setMainView(ClientMainView mainView) {
        this.mainView = mainView;
        loading = new Loading(this.mainView);

    }

    private ServerRemoteMethods serverRemoteMethods() {

        try {
            return (ServerRemoteMethods) LocateRegistry.getRegistry(1099).lookup("ServerRemote");
        } catch (NotBoundException | RemoteException e) {
            throw new RuntimeException(e);
        }

    }


}
