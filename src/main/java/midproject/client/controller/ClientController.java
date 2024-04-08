package midproject.client.controller;

import midproject.client.utility.ClientPanels;
import midproject.client.views.ClientMainView;
import midproject.client.views.Login;
import midproject.client.views.MainPanel;
import midproject.client.views.components.*;
import midproject.utilities.RMI.GlobalRemoteMethods;
import midproject.utilities.referenceClasses.*;
import midproject.utilities.utilityClasses.ClientActions;
import midproject.utilities.utilityClasses.IPGetter;
import midproject.utilities.utilityClasses.ServerActions;
import midproject.utilities.viewComponents.Loading;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;

/**
 * Implementation of methods from ClientObserver interface
 */
public class ClientController implements ClientObserver {

    private  GlobalRemoteMethods serverMethods;
    private ClientMainView mainView;
    private Loading loading;
    private Student loggedInAccount;
    private BookViewer bookViewer;
    private ChatView chatView;
    private ClientUpdates clientUpdates;

    /**
     * Constructor class for ClientController
     */
    public ClientController() {

        try {
            // lookup remote methods
            serverMethods = (GlobalRemoteMethods) Naming.lookup("rmi://"+ IPGetter.askUserForIP("Enter Server IP address")+":3000/servermethods");
            clientUpdates = new ClientUpdates(this);
        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            // notify user if server is not available
            JOptionPane.showMessageDialog(null, "Server Not Available", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        } // end of try catch

    } // end of constructor


    /**
     * Authentication for client log in
     * @param credential The authentication credentials entered by the user
     */
    @Override
    public void logIn(Authentication credential) {

        new Thread(() -> this.mainView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // logs user out if the user closed the window
                try {
                    serverMethods.logout(loggedInAccount);
                    System.exit(0);
                } catch (RemoteException ex) {
                    System.err.println(ex.getMessage());
                } // end of the try catch
            } // end of windowClosing
        })).start();


        new SwingWorker<Response<Student>, Void>() {
            @Override
            protected Response<Student> doInBackground() throws Exception {
                // logs in clients using entered credentials
                return serverMethods.logIn(credential, clientUpdates);
            } // end of doInBackground

            @Override
            protected void done() {
                try {

                    if (isDone()) {

                        Response<Student> response = get();

                        if (response.isSuccess()) {
                            // initializes frames and panels for newly logged-in user
                            loggedInAccount = response.getPayload();
                            mainView.getContentPane().removeAll();
                            MainPanel mainPanel = new MainPanel(ClientController.this);
                            mainView.setMainPanel(mainPanel);
                            mainView.getContentPane().add(mainPanel);
                            mainView.getContentPane().revalidate();
                            mainView.getContentPane().repaint();
                            System.out.println(loggedInAccount);
                        } else {

                            // notify user about invalid credentials
                            if (response.getPayload().getTotalBorrowedBooks() == 0) {
                                JOptionPane.showMessageDialog(mainView, "Invalid Username or Password");
                            } else {
                                JOptionPane.showMessageDialog(mainView, response.getPayload().getAccount().getAccountId());
                            } // end of inner if else

                        } // end of else statement

                       loading.setVisible(false);

                        // initialize chatView
                        chatView = new ChatView(mainView, "Messages", ClientController.this);
                        mainView.getHeader().addMessageAction(ClientController.this);
                    } // end of if statement

                } catch (InterruptedException | ExecutionException e) {
                    System.err.println(e.getMessage());
                } // end of try catch
            } // end of done
        }.execute();

       loading.setVisible(true);

    } // end of logIn

    /**
     * Allows a user to borrow a book
     * @param book The book to be borrowed
     */
    @Override
    public void borrowBook(Book book) {


        bookViewer.setVisible(false);

        try {
            // borrows a book from available books
            Response<String> response = serverMethods.borrowBook(book, loggedInAccount);

            if (response.isSuccess()) {
                // adds the borrowed book to the user's pending books
                loggedInAccount.getPendingBooks().add(book);
                JOptionPane.showMessageDialog(mainView,  book.getBookTitle() + " is added for pending");

                // sends notification to server
                serverMethods.sendNotificationToServer(ClientActions.BORROW_BOOK);

            } else {
                // notifies the client about the failed borrow
                JOptionPane.showMessageDialog(mainView, response.getPayload());
            } // end of if else statement

        } catch (RemoteException e) {
            System.err.println(e.getMessage());
        } // end of try catch

    } // end of borrowBook

    /**
     * Removes a book from a user's pending list
     * @param book The book to be removed from pending list
     */
    @Override
    public void removePending(Book book) {
        try {
            Response<String> response = serverMethods.removePending(book, loggedInAccount);

            if (response.isSuccess()) {
                // removes the specified book from a user's pending list
                loggedInAccount.getPendingBooks().remove(book);
                changeFrame(ClientPanels.PENDING_PANEL);

                // notifies the server about the action
                serverMethods.sendNotificationToServer(ClientActions.CANCEL_PENDING);

            } else {
                // notify the client about the failed removal
                JOptionPane.showMessageDialog(mainView, response.getPayload());
            } // end of if else statement

        } catch (RemoteException e) {
            System.err.println(e.getMessage());
        } // end of try catch

    } // end of removePending

    /**
     * Returns a previously borrowed book
     * @param book The book to be returned
     */
    @Override
    public void returnBook(Book book) {
        try {
            Response<String> response = serverMethods.returnBook(book, loggedInAccount);

            if (response.isSuccess()) {
                // remove the borrowed book from the list of borrowed books
                loggedInAccount.getBorrowedBooks().remove(book);
                changeFrame(ClientPanels.BORROWED_PANEL);

                // notifies the server about the action
                serverMethods.sendNotificationToServer(ClientActions.RETURN_BOOK);
            } else {
                // notifies the user about the failed returning of their book
                JOptionPane.showMessageDialog(mainView, response.getPayload());
            } // end of if else statement

        } catch (RemoteException e) {
            System.err.println(e.getMessage());
        } // end of try catch

    } // end of return book

    /**
     * Updates the view of a client
     * @param serverActions
     */
    @Override
    public void updateView(ServerActions serverActions)  {

        switch (serverActions) {
            case EDIT_BOOK -> {
                loggedInAccount = updateMyAccount();
                // notifies the clients that the server edited a book
                new Thread(() -> JOptionPane.showMessageDialog(mainView, "Admin edited a book")).start();
                if(mainView.getMenu() != null && mainView.getMenu().getCurrentButton().getText().equals("Books")) {
                    // changes the frame of the client to the home page
                    changeFrame(ClientPanels.HOME_PANEL);
                } // end of if statement
            } // end of EDIT_BOOK

            case BAN_ACCOUNT -> {
                // notifies the client that the admin had banned their account
                new Thread(()->JOptionPane.showMessageDialog(mainView, "Admin banned your account, you will be logged out")).start();
                // logs out the banned user
                logout();
            } // end of BAN_ACCOUNT

            case DELETE_BOOK -> {
                loggedInAccount =  updateMyAccount();
                // notifies the clients that the admin had deleted a book
                new Thread(()-> JOptionPane.showMessageDialog(mainView, "Admin deleted a book")).start();

                if(mainView.getMenu() != null && mainView.getMenu().getCurrentButton().getText().equals("Books")) {
                    // changes the frame of the client to the home page
                    changeFrame(ClientPanels.HOME_PANEL);
                } // end of if statement
            } // end of DELETE_BOOK

            case ADDED_NEW_BOOK -> {

            } // end of ADDED_NEW_BOOK

            case DELETE_ACCOUNT -> {
                // notifies the client that the admin had deleted their account
                new Thread(() -> JOptionPane.showMessageDialog(mainView, "Admin deleted your account, you will be logged out")).start();
                // logs out the user with the deleted account
                logout();
            } // end of DELETE_ACCOUNT

            case RETRIEVES_BOOK -> {
                loggedInAccount = updateMyAccount();

                if(mainView.getMenu() != null && mainView.getMenu().getCurrentButton().getText().equals("Borrowed Books")) {
                    // changes the frame of the client to the borrowed panel
                    changeFrame(ClientPanels.BORROWED_PANEL);
                } // end of if statement

                // notifies the client about the retrieved book
                new Thread(() ->JOptionPane.showMessageDialog(mainView, "The Admin retrieved your book")).start();
            } // end of RETRIEVES_BOOK

            case CHANGE_PASSWORD -> {
                // notifies the client that the admin had changed their password
                new Thread(() ->JOptionPane.showMessageDialog(mainView, "Admin changed your account password, you will be logged out. Please login again")).start();
                // logs out the user
                logout();
            } // end of CHANGE_PASSWORD

            case ACCEPT_BOOK_PENDING -> {

                loggedInAccount = updateMyAccount();

                if(mainView.getMenu() != null && mainView.getMenu().getCurrentButton().getText().equals("Pending Books")) {
                    // changes the frame of the client into the pending panel
                    changeFrame(ClientPanels.PENDING_PANEL);
                }else if(mainView.getMenu() != null && mainView.getMenu().getCurrentButton().getText().equals("Borrowed Books")) {
                    // changes the frame of the client into the borrowed panel
                    changeFrame(ClientPanels.BORROWED_PANEL);
                } // end of if else statement

                // notifies the user about the accepted book
                new Thread(() -> JOptionPane.showMessageDialog(mainView, "The Admin accepted your book pending")).start();
            } // end of ACCEPT_BOOK_PENDING

            case CANCEL_BOOK_PENDING -> {

                loggedInAccount = updateMyAccount();

                if (mainView.getMenu() != null && mainView.getMenu().getCurrentButton().getText().equals("Pending Books")) {
                    // changes the frame of the client into the pending panel
                    changeFrame(ClientPanels.PENDING_PANEL);
                } // end of if else statement

                // notifies the client about the cancelled pending book
                new Thread(() ->JOptionPane.showMessageDialog(mainView, "The Admin canceled your book pending") ).start();
            } // end of CANCEL_BOOK_PENDING

        } // end of switch case

    } // end of updateView

    /**
     * Updates the logged in account
     * @return the updated logged in account
     */
    private Student updateMyAccount() {

        try {
            // fetches the student accounts
            Response<LinkedList<Student>> studentResponse = serverMethods.getStudentAccounts();

            if(studentResponse.isSuccess()) {
                return studentResponse.getPayload().stream().filter(student -> student.getAccount().getAccountId().equals(loggedInAccount.getAccount().getAccountId())).findFirst().get();
            } // end of if else statement

        } catch (RemoteException e) {
            System.err.println(e.getMessage());
        } // end of try catch

        return loggedInAccount;
    } // end of updateMyAccount

    /**
     * Changes the frame of the client
     * @param clientPanels
     */
    @Override
    public void changeFrame(ClientPanels clientPanels) {

        switch (clientPanels) {

            case HOME_PANEL -> {
                SwingWorker<Void, Void> worker = new SwingWorker<>() {
                    @Override
                    protected Void doInBackground() {
                        // sets the panel of the client as the HomePanel
                        mainView.setContentPanel(new HomePanel(getBooks(), ClientController.this));
                        return null;
                    } // end of doInBackground

                    @Override
                    protected void done() {
                        // disables the home button
                        mainView.getMenu().setCurrentButton(mainView.getMenu().getHomeButton());
                        loading.setVisible(false);

                    } // end of done
                };
                worker.execute();

                loading.setVisible(true);
            } // end of HOME_PANEL

            case ACCOUNT_PANEL -> {
                // sets the panel of the client as the AccountPanel
                mainView.setContentPanel(new AccountPanel(loggedInAccount));
                // disables the account button
                mainView.getMenu().setCurrentButton(mainView.getMenu().getAccount());
            } // end of ACCOUNT_PANEL

            case PENDING_PANEL -> {
                // sets the panel of the client as the BookListPanel of pending books
                mainView.setContentPanel(new BookListPanel(loggedInAccount.getPendingBooks(), this, true, 500));
                // disables the pending books button
                mainView.getMenu().setCurrentButton(mainView.getMenu().getPendingBooks());
            } // end of PENDING_PANEL

            case BORROWED_PANEL -> {
                // sets the panel of the client as the BookListPanel of borrowed books
                mainView.setContentPanel(new BookListPanel(loggedInAccount.getBorrowedBooks(), this, false, 500));
                // disables the borrowed books button
                mainView.getMenu().setCurrentButton(mainView.getMenu().getBorrowedBooks());
            } // end of BORROWED_PANEL
        } // end of switch case

    } // end of changeFrame

    /**
     * Logs out the client
     */
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
        } // end of try catch

    } // end of logout

    /**
     * Receive message from another client
     * @param message
     * @param sender
     */
    @Override
    public void receiveMessage(String message, Student sender) {
        MessageBlock messageBlock = new MessageBlock(sender, loggedInAccount, message, Calendar.getInstance().getTime().toString());
        chatView.addMessage(messageBlock);
    } // end of receiveMessage

    /**
     * Receives a broadcast message from the server
     * @param message
     */
    @Override
    public void receiveBroadcast(String message) {
        new Thread(() -> JOptionPane.showMessageDialog(mainView, message) ).start();
    ;
    } // end of receiveBroadcast

    /**
     * Opens a book with its details
     * @param book
     */
    public void openBook(Book book) {

        try {
            // retrieves the book to be looked into
            Book viewBook = serverMethods.getBooks(true).getPayload().stream().filter( b -> b.getBookId().equals(book.getBookId())).findFirst().get();
            bookViewer = new BookViewer(mainView, viewBook, loggedInAccount, this);
            bookViewer.setVisible(true);
        } catch (RemoteException e) {
            System.err.println(e.getMessage());
        } // end of try catch
    }  // end of openBook

    /**
     * Open the message chat between clients
     */
    public void openMessageChat() {
        chatView.setVisible(true);
    } // end of openMessageChat

    /**
     * Sends a message to the chat block
     * @param message
     */
    public void sendMessage(String message) {
        try {
            serverMethods.sendMessage(message, loggedInAccount);
        } catch (RemoteException e) {
            System.err.println(e.getMessage());
        } // end of try catch
    } // end of sendMessage

    /**
     * Returns the list of books
     * @return
     */
    public LinkedList<Book> getBooks() {
        try {
            Response<LinkedList<Book>> response = serverMethods.getBooks(true);

            if (response.isSuccess()) {
                return response.getPayload();
            } // end of if statement

        } catch (RemoteException e) {
            return new LinkedList<>();
        } // end of try catch

        return new LinkedList<>();
    } // end of getBooks

    /**
     * Sets the main view of the client
     * @param mainView
     */
    public void setMainView(ClientMainView mainView) {
        this.mainView = mainView;
        loading = new Loading(this.mainView);

    } // end of setMainView

    /**
     * Gets the main view of the client
     * @return
     */
    public ClientMainView getMainView() {
        return mainView;
    } // end of getMainView

    /**
     * Gets the logged in account
     * @return
     */
    public Student getLoggedInAccount() {
        return loggedInAccount;
    } // end of getLoggedInAccount

    public void uploadImage(String fileName, byte[] bytes) {

        File file = new File(fileName);
        if(!file.exists()) {
            try ( FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                fileOutputStream.write(bytes);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("downloaded image");
        }else {
            System.out.println("image is already downloaded");
        }

        // notifies the clients that the admin had added a book
        new Thread(() ->JOptionPane.showMessageDialog(mainView, "Admin added a new book")).start();

        if(mainView.getMenu() != null && mainView.getMenu().getCurrentButton().getText().equals("Books")) {
            // changes the frame of the client to the home page
            changeFrame(ClientPanels.HOME_PANEL);
        } // end of if statement


    }
} // end of class
