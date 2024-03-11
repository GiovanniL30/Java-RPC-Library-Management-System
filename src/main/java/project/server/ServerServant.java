package project.server;

import project.server.controller.ServerController;
import project.server.controller.ServerObserver;
import project.utilities.RMI.ServerRemoteMethods;
import project.utilities.referenceClasses.Account;
import project.utilities.referenceClasses.Book;
import project.utilities.referenceClasses.Response;
import project.utilities.referenceClasses.Student;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;

public class ServerServant extends UnicastRemoteObject implements ServerRemoteMethods {

    private ServerController serverController;

    protected ServerServant() throws RemoteException {
        super();
    }


    @Override
    public Response<String> acceptBook(Book book, Student student) {
        System.out.println("Server accepts book");
        return null;
    }

    @Override
    public Response<String> retrieveBook(Book book, Student student) {
        System.out.println("Server Retrieves a book");
        return null;
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
}
