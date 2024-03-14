package project.server;

import project.client.controller.ClientController;
import project.utilities.RMI.ClientRemoteMethods;
import project.utilities.model.AccountModel;
import project.utilities.model.BookModel;
import project.utilities.referenceClasses.*;
import project.utilities.utilityClasses.ServerActions;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Optional;

public class ClientServant extends UnicastRemoteObject implements ClientRemoteMethods {

    private final HashMap<String, ClientController> clientsController;
    private final BookModel bookModel;
    private final AccountModel accountModel;
    public ClientServant(BookModel bookModel, AccountModel accountModel) throws RemoteException {
        super();
        this.bookModel = bookModel;
        this.accountModel = accountModel;
        clientsController = new HashMap<>();
    }

    @Override
    public Response<Student> logIn(Authentication credential, ClientController clientObserver) {
        System.out.println("Client Request to log in");


        LinkedList<Account> allAccounts = accountModel.getAccounts();

        Optional<Account> account = allAccounts.stream().filter(ac -> ac.getUserName().equals(credential.getUserName()) && ac.getPassword().equals(credential.getPassword())).findFirst();

        if(account.isPresent()) {

            if(clientsController.containsKey(account.get().getAccountId())) {
                return new Response<>(false, new Student(null, 1, null, null));
            }

            clientsController.put(account.get().getAccountId(), clientObserver);
            return new Response<>(true, getStudentAccount(account.get()));
        }else {
            return new Response<>(false, new Student(null, 0, null, null));
        }
    }

    @Override
    public Response<String> borrowBook(Book book) {
        System.out.println("Client Request to borrow a book");
        return null;
    }

    @Override
    public Response<String> removePending(Book book) {
        System.out.println("Client Request to remove a book pending");
        return null;
    }

    @Override
    public Response<String> returnBook(Book book) {
        System.out.println("Client Request to return a book");
        return null;
    }


    @Override
    public HashMap<String, ClientController> getClients() {
        return clientsController;
    }

    @Override
    public Response<LinkedList<Book>> getBooks() {
        System.out.println("Client Request to get all the books");
        return new Response<>(true, bookModel.getBooks());
    }

    @Override
    public void notification(ServerActions serverActions) {

    }

    @Override
    public void logout(Student student) throws RemoteException {
        System.out.println("A client requested to logout");
        clientsController.remove(student.getAccount().getAccountId());
    }

    private Student getStudentAccount(Account account) {
        LinkedList<Book> books = bookModel.getBooks();
        LinkedList<Book> borrowedBooks = new LinkedList<>();
        LinkedList<Book> pendingBooks = new LinkedList<>();

        for(Book book: books) {

            for(String borrowers: book.getCurrentBorrowers()) {

                if(borrowers.equals(account.getAccountId())) {
                    borrowedBooks.add(book);
                }

            }


            for(String pending: book.getPendingBorrowers()) {

                if(pending.equals(account.getAccountId())) {
                    pendingBooks.add(book);
                }

            }

        }

        return new Student(account, borrowedBooks.size(), pendingBooks, borrowedBooks);

    }

}
