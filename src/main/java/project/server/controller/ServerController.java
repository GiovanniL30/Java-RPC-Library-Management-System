package project.server.controller;

import project.server.views.LibrarianMainFrame;
import project.server.views.components.accountPanel.Signup;
import project.server.views.components.viewBookPanel.EditBookViewer;
import project.server.views.panels.*;
import project.server.views.utility.ServerPanels;
import project.utilities.RMI.GlobalRemoteMethods;
import project.utilities.referenceClasses.Account;
import project.utilities.referenceClasses.Book;
import project.utilities.referenceClasses.Response;
import project.utilities.referenceClasses.Student;
import project.utilities.utilityClasses.ClientActions;
import project.utilities.utilityClasses.IPGetter;
import project.utilities.utilityClasses.ServerActions;
import project.utilities.viewComponents.Loading;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.LinkedList;

/**
 * This class is the controller for the admin side
 */

public class ServerController implements ServerObserver, Serializable {
    private GlobalRemoteMethods serverMethods;
    private Loading loading;
    private LibrarianMainFrame mainView;
    private Signup signup;

    /**
     * Method to accept the book requested by the client to borrow
     */
    @Override
    public void acceptBook(Book book, Student student) {
        try {
            Response<String> response = serverMethods.acceptBook(book, student);
            if (response.isSuccess()) {
                changeFrame(ServerPanels.PENDING_BORROW_PANEL);
            }
            JOptionPane.showMessageDialog(mainView, response.getPayload());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves the book returned by the client
     */
    @Override
    public void retrieveBook(Book book, Student student) {
        try {
            Response<String> response = serverMethods.retrieveBook(book, student);
            if (response.isSuccess()) {
                changeFrame(ServerPanels.BORROWED_PANEL);
            }
            JOptionPane.showMessageDialog(mainView, response.getPayload());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get the pending return book to display in the pending return panel
     */
    @Override
    public void retrievePendingReturnBook(Book book, Student student) {
        try {
            Response<String> response = serverMethods.retrievePendingReturnBook(book, student);
            if (response.isSuccess()) {
                changeFrame(ServerPanels.PENDING_RETURN_PANEL);
            }
            JOptionPane.showMessageDialog(mainView, response.getPayload());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Allows the admin to edit book details
     */
    @Override
    public void editBook(Book book) {
        try {
            Response<String> response = serverMethods.editBook(book);
            if (response.isSuccess()) {
                changeFrame(ServerPanels.All_BOOKS_PANEL);
            }
        } catch (RemoteException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Display the edit book viewer where the admin can edit book details
     */
    @Override
    public void openBookEditor(Book book) {
        EditBookViewer editBookViewer = new EditBookViewer(mainView, book,this);
        editBookViewer.setVisible(true);
    }

    /**
     * Allows the admin to delete a book
     */
    @Override
    public boolean deleteBook(Book book) {
        try {
            Response<String> response = serverMethods.deleteBook(book);
            if (response.isSuccess()) {
                changeFrame(ServerPanels.All_BOOKS_PANEL);
            }
            JOptionPane.showMessageDialog(mainView, response.getPayload());
            return true;
        } catch (RemoteException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    /**
     * Declines the request of user to borrow a book
     */
    @Override
    public void cancelPending(Book book, Student student) {
        try {
            Response<String> response = serverMethods.cancelPending(book, student);
            if (response.isSuccess()) {
                changeFrame(ServerPanels.PENDING_BORROW_PANEL);
            }
            JOptionPane.showMessageDialog(mainView, response.getPayload());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Allows the admin to create a new book
     */
    @Override
    public void createNewBook(Book book) {
        try {
            Response<String> response = serverMethods.createNewBook(book);
            if (response.isSuccess()) {
                changeFrame(ServerPanels.ADD_BOOKS_PANEL);
            }
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Display the signup for the server side
     */
    @Override
    public void openSignUp() {
        signup = new Signup(mainView, new Dimension(1000, 500), this);
        signup.setVisible(true);
    }

    /**
     * Retrieve the list of available books
     */
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

    /**
     * Retrieve the list of unavailable books
     */
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

    /**
     * Retrieve the list of borrowed books in the meantime
     */
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

    /**
     * Retrieve the list of previously borrowed books
     */
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

    /**
     * Retrieve the list of books that are requested to borrow
     */
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

    /**
     * Get the list of books that are in pending return
     */
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

    /**
     * Allows the admin to broadcast messages to a certain user
     */
    @Override
    public void broadcastMessage(String message, String recipient) {

        try {
            Response<String> response = serverMethods.broadcastMessage(message, recipient);
            if (response.isSuccess()) {
                System.out.println("Message broadcast successfully.");
            } else {
                JOptionPane.showMessageDialog(mainView, response.getPayload());
            }
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Allows the admin to broadcast messages to all users
     */
    @Override
    public void broadcastMessageToAll(String message) {
        try {
            Response<String> response = serverMethods.broadcastMessage(message);
            if (response.isSuccess()) {
                System.out.println("Message broadcast successfully.");
            } else {
                JOptionPane.showMessageDialog(mainView, response.getPayload());
            }
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Bans the account of a certain user
     */
    @Override
    public void banAccount(Student account) {

        try {
            Response<String> response = serverMethods.banAccount(account);
            if (response.isSuccess()) {
                changeFrame(ServerPanels.MANAGE_ACCOUNTS_PANEL);
            }
            JOptionPane.showMessageDialog(mainView, response.getPayload());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Unbans the account of a certain user
     */
    @Override
    public void unbanAccount(Student account) {
        try {
            Response<String> response = serverMethods.unbanAccount(account);
            if (response.isSuccess()) {
                changeFrame(ServerPanels.MANAGE_ACCOUNTS_PANEL);
            }
            JOptionPane.showMessageDialog(mainView, response.getPayload());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Deletes the account of a certain user
     */
    @Override
    public void deleteAccount(Student account) {
        try {
            Response<String> response = serverMethods.deleteAccount(account);
            if (response.isSuccess()) {
                changeFrame(ServerPanels.MANAGE_ACCOUNTS_PANEL);
            }
            JOptionPane.showMessageDialog(mainView, response.getPayload());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Creates an account of a user
     */
    @Override
    public void createAccount(Account account) {
        try {
            Response<String> response = serverMethods.createAccount(account);
            if (response.isSuccess()) {
                changeFrame(ServerPanels.MANAGE_ACCOUNTS_PANEL);
                signup.dispose();
            }
            signup.getUserName().enableError(response.getPayload());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Allows the changing of password of a user
     */
    @Override
    public void changeUserPassword(Student account) {
        String newPass = JOptionPane.showInputDialog(mainView, "Enter the new password: ");
        if (newPass == null || newPass.isEmpty()) return;

        if (newPass.length() < 8) {
            JOptionPane.showMessageDialog(mainView, "Password Length Invalid [8 and above]");
            return;
        }

        try {
            Response<String> response = serverMethods.changeUserPassword(account, newPass);
            if (response.isSuccess()) {
                changeFrame(ServerPanels.MANAGE_ACCOUNTS_PANEL);
            }
            JOptionPane.showMessageDialog(mainView, response.getPayload());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieve the list of all books
     */
    @Override
    public LinkedList<Book> getBooks() {
        try {
            Response<LinkedList<Book>> response = serverMethods.getBooks(false);

            if (response.isSuccess()) {
                return response.getPayload();
            }

        } catch (RemoteException e) {
            return new LinkedList<>();
        }
        return new LinkedList<>();
    }

    /**
     * Retrieve the list of all student users
     */
    @Override
    public LinkedList<Student> getStudents() {
        try {
            Response<LinkedList<Student>> studentResponse = serverMethods.getStudentAccounts();

            if (studentResponse.isSuccess()) {
                return studentResponse.getPayload();
            }

        } catch (RemoteException e) {
            return new LinkedList<>();
        }
        return new LinkedList<>();
    }

    /**
     * Changing of frames for every button for display
     */
    @Override
    public void changeFrame(ServerPanels serverPanels) {
        new SwingWorker<>() {
            @Override
            protected Object doInBackground() {

                switch (serverPanels) {
                    //Home Panel Display
                    case HOME_PANEL -> {
                        mainView.getContentPane().remove(1);
                        mainView.setCurrentPanel(new HomePanel(ServerController.this));
                    }
                    // View Book Panel Display
                    case VIEW_BOOKS_PANEL -> {
                        mainView.getContentPane().remove(1);
                        mainView.setCurrentPanel(new ViewBookPanel(ServerController.this));
                    }
                    // Manage Books Panel Display
                    case MANAGE_BOOK_PANEL -> {
                        mainView.getContentPane().remove(1);
                        mainView.setCurrentPanel(new ManageBookPanel(getStudents(), ServerController.this));
                    }
                    // Manage Accounts Panel Display
                    case MANAGE_ACCOUNTS_PANEL -> {
                        mainView.getContentPane().remove(1);
                        mainView.setCurrentPanel(new ManageAccountsPanel(getStudents(), ServerController.this));
                    }
                    // Add Books Panel Display
                    case ADD_BOOKS_PANEL -> {
                        mainView.getContentPane().remove(1);
                        mainView.setCurrentPanel(new AddBooksPanel(ServerController.this));
                    }
                    // Pending Borrow of Books Panel Display
                    case PENDING_BORROW_PANEL -> {
                        mainView.getManageBookPanel().setManageBookList(getStudents(), ServerPanels.PENDING_BORROW_PANEL);
                        mainView.getManageBookPanel().getSubHeader().setCurrentClickableText(mainView.getManageBookPanel().getSubHeader().getButton1());
                    }
                    // Pending Return of Books Panel Display
                    case PENDING_RETURN_PANEL -> {
                        mainView.getManageBookPanel().setManageBookList(getStudents(), ServerPanels.PENDING_RETURN_PANEL);
                        mainView.getManageBookPanel().getSubHeader().setCurrentClickableText(mainView.getManageBookPanel().getSubHeader().getButton2());
                    }
                    // Borrowed Books Panel Display
                    case BORROWED_PANEL -> {
                        mainView.getManageBookPanel().setManageBookList(getStudents(), ServerPanels.BORROWED_PANEL);
                        mainView.getManageBookPanel().getSubHeader().setCurrentClickableText(mainView.getManageBookPanel().getSubHeader().getButton3());
                    }
                    // All Books Panel Display
                    case All_BOOKS_PANEL -> {
                        mainView.getViewBookPanel().setView(getBooks());
                        mainView.getViewBookPanel().getSubHeader().setCurrentClickableText(mainView.getViewBookPanel().getSubHeader().getButton1());
                    }
                    // Available books panel Display
                    case AVAILABLE_BOOKS_PANEL -> {
                        mainView.getViewBookPanel().setView(getAvailableBooks());
                        mainView.getViewBookPanel().getSubHeader().setCurrentClickableText(mainView.getViewBookPanel().getSubHeader().getButton2());
                    }
                    // Unavailable books Panel Display
                    case UNAVAILABLE_BOOKS_PANEL -> {
                        mainView.getViewBookPanel().setView(getUnavailableBooks());
                        mainView.getViewBookPanel().getSubHeader().setCurrentClickableText(mainView.getViewBookPanel().getSubHeader().getButton3());
                    }
                }
                return null;
            }

            @Override
            protected void done() {
                mainView.getContentPane().revalidate();
                mainView.getContentPane().repaint();
                loading.setVisible(false);
            }
        }.execute();
        loading.setVisible(true);
    }

    /**
     * Updates the display whenever actions are performed
     */
    @Override
    public void updateView(ClientActions clientActions) {
        switch (clientActions) {
            case BORROW_BOOK, CANCEL_PENDING -> {
                System.out.println(clientActions);
                if (mainView.getManageBookPanel() != null && mainView.getManageBookPanel().getSubHeader().getCurrentButton().getText().equals(ServerPanels.PENDING_BORROW_PANEL.getDisplayName())) {
                    changeFrame(ServerPanels.PENDING_BORROW_PANEL);
                }
            }
            case RETURN_BOOK -> {
                if (mainView.getManageBookPanel() != null && mainView.getManageBookPanel().getSubHeader().getCurrentButton().getText().equals(ServerPanels.BORROWED_PANEL.getDisplayName())) {
                    changeFrame(ServerPanels.BORROWED_PANEL);
                } else if (mainView.getManageBookPanel() != null && mainView.getManageBookPanel().getSubHeader().getCurrentButton().getText().equals(ServerPanels.PENDING_RETURN_PANEL.getDisplayName())) {
                    changeFrame(ServerPanels.PENDING_RETURN_PANEL);
                }
            }
        }
    }

    /**
     * Accepts the book borrowed by the user
     */
    @Override
    public void acceptBook(Student student, Book book) {
        try {
            Response<String> acceptBookResponse = serverMethods.acceptBook(book, student);

            if (acceptBookResponse.isSuccess()) {
                changeFrame(ServerPanels.PENDING_BORROW_PANEL);
                JOptionPane.showMessageDialog(getServerMainView(), acceptBookResponse.getPayload());
                serverMethods.sendNotificationToClient(ServerActions.ACCEPT_BOOK_PENDING, student);
            } else {
                JOptionPane.showMessageDialog(getServerMainView(), acceptBookResponse.getPayload());
            }

        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sets the server
     */
    public void setServerMethods(ServerUpdates updates) {
        try {

            if(updates == null) {
                serverMethods.registerServerController(null);
                System.exit(0);
            }
            serverMethods = (GlobalRemoteMethods) Naming.lookup("rmi://" + IPGetter.askUserForIP("Enter Server IP address") + ":3000/servermethods");
            serverMethods.registerServerController(updates);
        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            JOptionPane.showMessageDialog(null, "Server Not Available", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    /**
     * Retrieve the main view to isplay the main frame of the admin
     */
    public LibrarianMainFrame getServerMainView() {
        return mainView;
    }

    /**
     * Set the main view of the server
     */
    public void setServerMainView(LibrarianMainFrame mainView) {
        this.mainView = mainView;
        loading = new Loading(this.mainView);
    }
} // end of ServerController class
