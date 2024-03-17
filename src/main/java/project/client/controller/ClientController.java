package project.client.controller;

import project.client.RMI.ClientServant;
import project.client.utility.ClientPanels;
import project.client.views.ClientMainView;
import project.client.views.Login;
import project.client.views.MainPanel;
import project.client.views.components.*;
import project.utilities.RMI.GlobalRemoteMethods;
import project.utilities.referenceClasses.*;
import project.utilities.utilityClasses.ServerActions;
import project.utilities.viewComponents.Loading;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;

public class ClientController implements ClientObserver {

    private final GlobalRemoteMethods serverMethods;
    private ClientMainView mainView;
    private Loading loading;
    private Student loggedInAccount;
    private BookViewer bookViewer;
    private ChatView chatView;
    private ClientServant clientServant;

    public ClientController() {

        try {
            serverMethods = (GlobalRemoteMethods) LocateRegistry.getRegistry(1099).lookup("server");
            clientServant = new ClientServant(this);
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
                    serverMethods.logout(loggedInAccount);
                    System.exit(0);
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        new SwingWorker<Response<Student>, Void>() {
            @Override
            protected Response<Student> doInBackground() throws Exception {
                return serverMethods.logIn(credential, clientServant);
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

                       // loading.setVisible(false);
                        chatView = new ChatView(mainView, "Messages", ClientController.this);
                        mainView.getHeader().addMessageAction(ClientController.this);
                    }

                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }
        }.execute();

       // loading.setVisible(true);

    }

    @Override
    public void borrowBook(Book book) {
        bookViewer.setVisible(false);

        try {
            Response<String> response = serverMethods.borrowBook(book, loggedInAccount);

            if (response.isSuccess()) {
                loggedInAccount.getPendingBooks().add(book);
            } else {
                JOptionPane.showMessageDialog(mainView, response.getPayload());
            }

        } catch (RemoteException e) {

        }

    }

    @Override
    public void removePending(Book book) {
        try {
            Response<String> response = serverMethods.removePending(book, loggedInAccount);

            if (response.isSuccess()) {
                loggedInAccount.getPendingBooks().remove(book);
                changeFrame(ClientPanels.PENDING_PANEL);
            } else {
                JOptionPane.showMessageDialog(mainView, response.getPayload());
            }

        } catch (RemoteException e) {

        }

    }

    @Override
    public void returnBook(Book book) {
        try {
            Response<String> response = serverMethods.returnBook(book, loggedInAccount);

        } catch (RemoteException e) {

        }

    }

    @Override
    public void updateView(ServerActions serverActions)  {

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
                mainView.setContentPanel(new BookListPanel(loggedInAccount.getPendingBooks(), this, true));
                mainView.getMenu().setCurrentButton(mainView.getMenu().getPendingBooks());

            }
            case BORROWED_PANEL -> {
                mainView.setContentPanel(new BookListPanel(loggedInAccount.getBorrowedBooks(), this, false));
                mainView.getMenu().setCurrentButton(mainView.getMenu().getBorrowedBooks());

            }
        }

    }

    @Override
    public void logout() {

        try {
            serverMethods.logout(loggedInAccount);
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

    @Override
    public void receiveMessage(String message, Student sender) {
        MessageBlock messageBlock = new MessageBlock(sender, loggedInAccount, message, Calendar.getInstance().getTime().toString());
        chatView.addMessage(messageBlock);
    }

    public void openBook(Book book) {

        try {
            Book viewBook = serverMethods.getBooks().getPayload().stream().filter( b -> b.getBookId().equals(book.getBookId())).findFirst().get();
            bookViewer = new BookViewer(mainView, viewBook, loggedInAccount, this);
            bookViewer.setVisible(true);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

    }

    public void openMessageChat() {
        chatView.setVisible(true);
    }



    public void sendMessage(String message) {
        try {
            serverMethods.sendMessage(message, loggedInAccount);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
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

    public void setMainView(ClientMainView mainView) {
        this.mainView = mainView;
        loading = new Loading(this.mainView);

    }


}
