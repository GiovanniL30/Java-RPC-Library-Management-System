package project.server;

import project.server.controller.ServerController;
import project.server.controller.ServerObserver;
import project.utilities.RMI.ClientRemoteMethods;
import project.utilities.RMI.ServerRemoteMethods;
import project.utilities.model.AccountModel;
import project.utilities.model.BookModel;
import project.utilities.referenceClasses.Account;
import project.utilities.referenceClasses.Book;
import project.utilities.referenceClasses.Response;
import project.utilities.referenceClasses.Student;
import project.utilities.utilityClasses.ClientActions;
import project.utilities.utilityClasses.ServerActions;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;

public class ServerServant extends UnicastRemoteObject implements ServerRemoteMethods {

    private ServerController serverController;
    private BookModel bookModel;
    private AccountModel accountModel;

    protected ServerServant(BookModel bookModel, AccountModel accountModel) throws RemoteException {
        super();
        this.bookModel = bookModel;
        this.accountModel = accountModel;
    }


    @Override
    public Response<String> acceptBook(Book book, Student student) {
        System.out.println("Server accepts" + book.getBookTitle() + " for " + student.getAccount().getUserName() + "\n\n");

        if(bookModel.addBorrowed(book.getBookId(), student)) {
            return new Response<>(true, "Book was successfully added for pending");
        }
            return new Response<>(false, "Book was not added for pending");
    }

    @Override
    public Response<String> retrieveBook(Book book, Student student) {
        System.out.println("Server retrieves" + book.getBookTitle() + " for " + student.getAccount().getUserName() + "\n\n");
        if (bookModel.removeBorrowed(book.getBookId(), student, false)) {
            return new Response<>(true, "Book was successfully retrieved");
        }
        return new Response<>(false, "Book was not retrieved");
    }

    @Override
    public Response<String> editBook(Book book) {
        System.out.println("Server Edited Books");
        return null;
    }

    @Override
    public Response<String> deleteBook(Book book) {
        System.out.println("Server deletes a book");
        return null;
    }

    @Override
    public Response<String> cancelPending(Book book, Student student) {
        System.out.println("Server cancel a book pending");
        return null;
    }

    @Override
    public Response<String> createNewBook(Book book) {
        System.out.println("Server creates a new book");
        return null;
    }

    @Override
    public Response<String> broadcastMessage(String message) {
        System.out.println("Server sends a broadcast message");
        return null;
    }

    @Override
    public Response<String> banAccount(Account account) {
        System.out.println("Server bans a account");
        return null;
    }

    @Override
    public Response<String> unbanAccount(Account account) {
        System.out.println("Sever unbans a account");
        return null;
    }

    @Override
    public Response<String> deleteAccount(Student account) {
        System.out.println("Server deletes a account");
        return null;
    }

    @Override
    public Response<String> createAccount(Account account) {
        System.out.println("Server created a new account");
        return null;
    }

    @Override
    public Response<String> changeUserPassword(Account account, String newPassword) {
        System.out.println("Sever changed a password for a student");
        return null;
    }

    @Override
    public Response<LinkedList<Book>> getBooks() {
        System.out.println("Server get all books");
        return null;
    }

    @Override
    public void registerServer(ServerController serverObserver) {
        serverController = serverObserver;
    }

    @Override
    public void notification(ClientActions clientActions) {

        new Thread(() -> {
            System.out.println("A client sent a notification");
            serverController.updateView(clientActions);
        }).start();

    }
}
