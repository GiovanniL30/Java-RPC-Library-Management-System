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

public class ServerController implements ServerObserver, Serializable {

    private GlobalRemoteMethods serverMethods;
    private Loading loading;
    private LibrarianMainFrame mainView;



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

    @Override
    public void openBookEditor(Book book) {
        EditBookViewer editBookViewer = new EditBookViewer(mainView, book,this);
        editBookViewer.setVisible(true);
    }

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

    @Override
    public void openSignUp() {
        Signup signup = new Signup(mainView, new Dimension(1000, 500), this);
        signup.setVisible(true);
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


    @Override
    public void createAccount(Account account) {
        try {
            Response<String> response = serverMethods.createAccount(account);

            if (response.isSuccess()) {
                changeFrame(ServerPanels.MANAGE_ACCOUNTS_PANEL);
            }

            JOptionPane.showMessageDialog(mainView, response.getPayload());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

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

    @Override
    public void changeFrame(ServerPanels serverPanels) {


        new SwingWorker<>() {
            @Override
            protected Object doInBackground() {

                switch (serverPanels) {

                    case HOME_PANEL -> {
                        mainView.getContentPane().remove(1);
                        mainView.setCurrentPanel(new HomePanel(ServerController.this));

                    }
                    case VIEW_BOOKS_PANEL -> {
                        mainView.getContentPane().remove(1);
                        mainView.setCurrentPanel(new ViewBookPanel(ServerController.this));
                    }
                    case MANAGE_BOOK_PANEL -> {
                        mainView.getContentPane().remove(1);
                        mainView.setCurrentPanel(new ManageBookPanel(getStudents(), ServerController.this));
                    }
                    case MANAGE_ACCOUNTS_PANEL -> {
                        mainView.getContentPane().remove(1);
                        mainView.setCurrentPanel(new ManageAccountsPanel(getStudents(), ServerController.this));
                    }
                    case ADD_BOOKS_PANEL -> {
                        mainView.getContentPane().remove(1);
                        mainView.setCurrentPanel(new AddBooksPanel(ServerController.this));
                    }
                    case PENDING_BORROW_PANEL -> {
                        mainView.getManageBookPanel().setManageBookList(getStudents(), ServerPanels.PENDING_BORROW_PANEL);
                        mainView.getManageBookPanel().getSubHeader().setCurrentClickableText(mainView.getManageBookPanel().getSubHeader().getButton1());
                    }
                    case PENDING_RETURN_PANEL -> {
                        mainView.getManageBookPanel().setManageBookList(getStudents(), ServerPanels.PENDING_RETURN_PANEL);
                        mainView.getManageBookPanel().getSubHeader().setCurrentClickableText(mainView.getManageBookPanel().getSubHeader().getButton2());
                    }
                    case BORROWED_PANEL -> {
                        mainView.getManageBookPanel().setManageBookList(getStudents(), ServerPanels.BORROWED_PANEL);
                        mainView.getManageBookPanel().getSubHeader().setCurrentClickableText(mainView.getManageBookPanel().getSubHeader().getButton3());
                    }
                    case All_BOOKS_PANEL -> {
                        mainView.getViewBookPanel().setView(getBooks());
                        mainView.getViewBookPanel().getSubHeader().setCurrentClickableText(mainView.getViewBookPanel().getSubHeader().getButton1());
                    }
                    case AVAILABLE_BOOKS_PANEL -> {
                        mainView.getViewBookPanel().setView(getAvailableBooks());
                        mainView.getViewBookPanel().getSubHeader().setCurrentClickableText(mainView.getViewBookPanel().getSubHeader().getButton2());
                    }
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

    public LibrarianMainFrame getServerMainView() {
        return mainView;
    }

    public void setServerMainView(LibrarianMainFrame mainView) {
        this.mainView = mainView;
        loading = new Loading(this.mainView);
    }

}
