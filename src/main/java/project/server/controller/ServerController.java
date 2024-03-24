package project.server.controller;

import project.server.views.LibrarianMainFrame;
import project.server.views.panels.*;
import project.server.views.utility.ServerPanels;
import project.utilities.RMI.GlobalRemoteMethods;
import project.utilities.referenceClasses.Account;
import project.utilities.referenceClasses.Book;
import project.utilities.referenceClasses.Response;
import project.utilities.referenceClasses.Student;
import project.utilities.utilityClasses.ClientActions;
import project.utilities.utilityClasses.ServerActions;
import project.utilities.viewComponents.Loading;

import javax.swing.*;
import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
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
        try {
            Response<String> response = serverMethods.retrieveBook(book,student);
            if (response.isSuccess()) {
                JOptionPane.showMessageDialog(null, "Book retrieved successfully.");
            } else {
                JOptionPane.showMessageDialog(null, response.getPayload());
            }
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void editBook(Book book) {

        try {
            Response<String> response = serverMethods.editBook(book);
            if (response.isSuccess()){

            }
        } catch (RemoteException e){
            throw new RuntimeException();
        }
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

            if(studentResponse.isSuccess()) {
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
                        mainView.setCurrentPanel(new ManageBookPanel(getBooks(), getStudents(), ServerController.this));

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
               if(mainView.getManageBookPanel() != null && mainView.getManageBookPanel().getSubHeader().getCurrentButton().getText().equals(ServerPanels.PENDING_BORROW_PANEL.getDisplayName())){
                   changeFrame(ServerPanels.PENDING_BORROW_PANEL);
               }
           }
       }
    }

    @Override
    public void acceptBook(Student student, Book book) {
        try {
          Response<String> acceptBookResponse = serverMethods.acceptBook(book, student);

          if(acceptBookResponse.isSuccess()) {
              changeFrame(ServerPanels.PENDING_BORROW_PANEL);
              JOptionPane.showMessageDialog(getServerMainView(), acceptBookResponse.getPayload());
              serverMethods.sendNotificationToClient(ServerActions.ACCEPT_BOOK_PENDING, student);
          }else {
              JOptionPane.showMessageDialog(getServerMainView(), acceptBookResponse.getPayload() );
          }

        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void setServerMethods() {
        try {
            serverMethods = (GlobalRemoteMethods) LocateRegistry.getRegistry(1099).lookup("server");
        } catch (RemoteException | NotBoundException e) {
            throw new RuntimeException(e);
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
