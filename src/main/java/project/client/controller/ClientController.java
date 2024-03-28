package project.client.controller;

import project.client.utility.ClientPanels;
import project.client.views.ClientMainView;
import project.client.views.Login;
import project.client.views.MainPanel;
import project.client.views.components.*;
import project.utilities.RMI.GlobalRemoteMethods;
import project.server.controller.ServerUpdateReceiver;
import project.utilities.referenceClasses.*;
import project.utilities.utilityClasses.ClientActions;
import project.utilities.utilityClasses.IPGetter;
import project.utilities.utilityClasses.ServerActions;
import project.utilities.viewComponents.Loading;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;

public class ClientController implements ClientObserver {

    private  GlobalRemoteMethods serverMethods;
    private ClientMainView mainView;
    private Loading loading;
    private Student loggedInAccount;
    private BookViewer bookViewer;
    private ChatView chatView;
    private ClientUpdates clientUpdates;

    public ClientController() {

        try {
            serverMethods = (GlobalRemoteMethods) Naming.lookup("rmi://"+ IPGetter.askUserForIP("Enter Server IP address")+":3000/servermethods");
            clientUpdates = new ClientUpdates(this);
        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            JOptionPane.showMessageDialog(null, "Server Not Available", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

    }



    @Override
    public void logIn(Authentication credential) {

        new Thread(() -> this.mainView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    serverMethods.logout(loggedInAccount);
                    System.exit(0);
                } catch (RemoteException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        })).start();


        new SwingWorker<Response<Student>, Void>() {
            @Override
            protected Response<Student> doInBackground() throws Exception {
                return serverMethods.logIn(credential, clientUpdates);
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
                                JOptionPane.showMessageDialog(mainView, response.getPayload().getAccount().getAccountId());
                            }

                        }

                       loading.setVisible(false);
                        chatView = new ChatView(mainView, "Messages", ClientController.this);
                        mainView.getHeader().addMessageAction(ClientController.this);
                    }

                } catch (InterruptedException | ExecutionException e) {
                    System.err.println(e.getMessage());
                }
            }
        }.execute();

       loading.setVisible(true);

    }

    @Override
    public void borrowBook(Book book) {
        bookViewer.setVisible(false);

        try {
            Response<String> response = serverMethods.borrowBook(book, loggedInAccount);

            if (response.isSuccess()) {
                loggedInAccount.getPendingBooks().add(book);
                JOptionPane.showMessageDialog(mainView,  book.getBookTitle() + " is added for pending");

                serverMethods.sendNotificationToServer(ClientActions.BORROW_BOOK);

            } else {
                JOptionPane.showMessageDialog(mainView, response.getPayload());
            }

        } catch (RemoteException e) {
            System.err.println(e.getMessage());
        }

    }

    @Override
    public void removePending(Book book) {
        try {
            Response<String> response = serverMethods.removePending(book, loggedInAccount);

            if (response.isSuccess()) {
                loggedInAccount.getPendingBooks().remove(book);
                changeFrame(ClientPanels.PENDING_PANEL);

                serverMethods.sendNotificationToServer(ClientActions.CANCEL_PENDING);

            } else {
                JOptionPane.showMessageDialog(mainView, response.getPayload());
            }

        } catch (RemoteException e) {
            System.err.println(e.getMessage());
        }

    }

    @Override
    public void returnBook(Book book) {
        try {
            Response<String> response = serverMethods.returnBook(book, loggedInAccount);

            if (response.isSuccess()) {
                loggedInAccount.getBorrowedBooks().remove(book);
                changeFrame(ClientPanels.BORROWED_PANEL);

                serverMethods.sendNotificationToServer(ClientActions.RETURN_BOOK);
            } else {
                JOptionPane.showMessageDialog(mainView, response.getPayload());
            }

        } catch (RemoteException e) {
            System.err.println(e.getMessage());
        }

    }

    @Override
    public void updateView(ServerActions serverActions)  {

        switch (serverActions) {
            case EDIT_BOOK -> {
                loggedInAccount = updateMyAccount();
                JOptionPane.showMessageDialog(mainView, "Admin edited a book");

                if(mainView.getMenu() != null && mainView.getMenu().getCurrentButton().getText().equals("Books")) {
                    changeFrame(ClientPanels.HOME_PANEL);
                }
            }
            case BAN_ACCOUNT -> {
                JOptionPane.showMessageDialog(mainView, "Admin banned your account, you will be logged out");
                logout();
            }
            case DELETE_BOOK -> {
                loggedInAccount =  updateMyAccount();
                JOptionPane.showMessageDialog(mainView, "Admin deleted a book");

                if(mainView.getMenu() != null && mainView.getMenu().getCurrentButton().getText().equals("Books")) {
                    changeFrame(ClientPanels.HOME_PANEL);
                }
            }
            case ADDED_NEW_BOOK -> {
                JOptionPane.showMessageDialog(mainView, "Admin added a new book");

                if(mainView.getMenu() != null && mainView.getMenu().getCurrentButton().getText().equals("Books")) {
                    changeFrame(ClientPanels.HOME_PANEL);
                }
            }
            case DELETE_ACCOUNT -> {
                JOptionPane.showMessageDialog(mainView, "Admin deleted your account, you will be logged out");
                logout();
            }
            case RETRIEVES_BOOK -> {
                loggedInAccount = updateMyAccount();

                if(mainView.getMenu() != null && mainView.getMenu().getCurrentButton().getText().equals("Borrowed Books")) {
                    changeFrame(ClientPanels.BORROWED_PANEL);
                }

                JOptionPane.showMessageDialog(mainView, "The Admin retrieves your book");
            }
            case CHANGE_PASSWORD -> {
                JOptionPane.showMessageDialog(mainView, "Admin changed your account password, you will be logged out. Please login again");
                logout();
            }
            case BROADCAST_MESSAGE -> {

            }
            case ACCEPT_BOOK_PENDING -> {

                loggedInAccount = updateMyAccount();

                if(mainView.getMenu() != null && mainView.getMenu().getCurrentButton().getText().equals("Pending Books")) {
                    changeFrame(ClientPanels.PENDING_PANEL);
                }else if(mainView.getMenu() != null && mainView.getMenu().getCurrentButton().getText().equals("Borrowed Books")) {
                    changeFrame(ClientPanels.BORROWED_PANEL);
                }

                JOptionPane.showMessageDialog(mainView, "The Admin accepted your book pending");
            }
            case CANCEL_BOOK_PENDING -> {

                loggedInAccount = updateMyAccount();

                if(mainView.getMenu() != null && mainView.getMenu().getCurrentButton().getText().equals("Pending Books")) {
                    changeFrame(ClientPanels.PENDING_PANEL);
                }

                JOptionPane.showMessageDialog(mainView, "The Admin canceled your book pending");
            }

        }

    }

    private Student updateMyAccount() {

        try {
            Response<LinkedList<Student>> studentResponse = serverMethods.getStudentAccounts();

            if(studentResponse.isSuccess()) {
                return studentResponse.getPayload().stream().filter(student -> student.getAccount().getAccountId().equals(loggedInAccount.getAccount().getAccountId())).findFirst().get();
            }

        } catch (RemoteException e) {
            System.err.println(e.getMessage());
        }

        return loggedInAccount;
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
                mainView.setContentPanel(new BookListPanel(loggedInAccount.getPendingBooks(), this, true, 500));
                mainView.getMenu().setCurrentButton(mainView.getMenu().getPendingBooks());
            }
            case BORROWED_PANEL -> {
                mainView.setContentPanel(new BookListPanel(loggedInAccount.getBorrowedBooks(), this, false, 500));
                mainView.getMenu().setCurrentButton(mainView.getMenu().getBorrowedBooks());
            }
        }

    }

    @Override
    public void logout() {

        try {
            serverMethods.logout(loggedInAccount);
            loggedInAccount = new Student(new Account("", "", "", "", "", "", false), 0, null, null, null);
            mainView.getContentPane().removeAll();
            Login login = new Login(new Dimension(ClientMainView.FRAME_WIDTH, 900));
            login.addClickEvent(this);
            mainView.getContentPane().add(login);
            mainView.revalidate();
            mainView.repaint();
        } catch (RemoteException ex) {
            System.err.println(ex.getMessage());
        }

    }

    @Override
    public void receiveMessage(String message, Student sender) {
        MessageBlock messageBlock = new MessageBlock(sender, loggedInAccount, message, Calendar.getInstance().getTime().toString());
        chatView.addMessage(messageBlock);
    }

    @Override
    public void receiveBroadcast(String message) {
        JOptionPane.showMessageDialog(mainView, message);
    }

    public void openBook(Book book) {

        try {
            Book viewBook = serverMethods.getBooks(true).getPayload().stream().filter( b -> b.getBookId().equals(book.getBookId())).findFirst().get();
            bookViewer = new BookViewer(mainView, viewBook, loggedInAccount, this);
            bookViewer.setVisible(true);
        } catch (RemoteException e) {
            System.err.println(e.getMessage());
        }

    }

    public void openMessageChat() {
        chatView.setVisible(true);
    }



    public void sendMessage(String message) {
        try {
            serverMethods.sendMessage(message, loggedInAccount);
        } catch (RemoteException e) {
            System.err.println(e.getMessage());
        }
    }

    public LinkedList<Book> getBooks() {
        try {
            Response<LinkedList<Book>> response = serverMethods.getBooks(true);

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

    public ClientMainView getMainView() {
        return mainView;
    }

    public Student getLoggedInAccount() {
        return loggedInAccount;
    }
}
