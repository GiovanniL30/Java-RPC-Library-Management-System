package project.server;

import project.client.controller.ClientUpdateReceiver;
import project.server.controller.ServerUpdateReceiver;
import project.utilities.RMI.GlobalRemoteMethods;
import project.utilities.model.AccountModel;
import project.utilities.model.BookModel;
import project.utilities.referenceClasses.*;
import project.utilities.utilityClasses.ClientActions;
import project.utilities.utilityClasses.ServerActions;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Optional;

public class GlobalRemoteServant extends UnicastRemoteObject implements GlobalRemoteMethods {

    private final HashMap<String, ClientUpdateReceiver> clientsHashMap = new HashMap<>();
    private ServerUpdateReceiver serverUpdateReceiver;
    private final BookModel bookModel = new BookModel();
    private final AccountModel accountModel = new AccountModel();

    public GlobalRemoteServant(ServerUpdateReceiver serverUpdateReceiver) throws RemoteException {
        this.serverUpdateReceiver = serverUpdateReceiver;
    }

    ///////////  Client Remote Methods--------

    /**
     *
     *  Client Remote Methods
     *
     * */
    @Override
    public Response<Student> logIn(Authentication credential, ClientUpdateReceiver clientUpdateReceiver) throws RemoteException {
        System.out.println("Client Request to log in");

        LinkedList<Account> allAccounts = accountModel.getAccounts();

        Optional<Account> account = allAccounts.stream().filter(ac -> ac.getUserName().equals(credential.getUserName()) && ac.getPassword().equals(credential.getPassword())).findFirst();

        if (account.isPresent()) {

            if (clientsHashMap.containsKey(account.get().getAccountId())) {
                return new Response<>(false, new Student(null, 1, null, null));
            }

            clientsHashMap.put(account.get().getAccountId(), clientUpdateReceiver);
            System.out.println(account.get().getUserName() + " logged in successfully\n\n");
            return new Response<>(true, getStudentAccount(account.get()));
        } else {
            return new Response<>(false, new Student(null, 0, null, null));
        }
    }

    @Override
    public synchronized Response<String> borrowBook(Book book, Student student) throws RemoteException {
        System.out.println(student.getAccount().getUserName() + " Requested to borrow the book " + book.getBookTitle() + "\n\n");

        int latestBookCopies = getUpdatedBook(book.getBookId()).getCopies();

        if(latestBookCopies == 0) {
            return new Response<>(false, "Unfortunately, there are no copies of the book left");
        }

        if (student.getBorrowedBooks().size() == 5 || student.getPendingBooks().size() == 5 || student.getPendingBooks().size() + student.getBorrowedBooks().size() == 5) {
            return new Response<>(false, "You have reached the limit of 5 book being pending and borrowed");
        }

        if (bookModel.addPending(book.getBookId(), student)) {
            return new Response<>(true, "Book was successfully added for pending");
        }

        return new Response<>(false, "Book was not successfully added for pending");
    }

    @Override
    public Response<String> removePending(Book book, Student student) throws RemoteException {
        System.out.println(student.getAccount().getUserName() + " Requested to remove a book pending " + book.getBookTitle() + "\n\n");

        if (bookModel.removePending(book.getBookId(), student)) {
            return new Response<>(true, "Book was successfully removed for pending");
        }

        return new Response<>(true, "Book was not successfully removed for pending");
    }

    @Override
    public Response<String> returnBook(Book book, Student student) throws RemoteException {
        System.out.println(student.getAccount().getUserName() + " Requested to return a borrowed book" + book.getBookTitle() + "\n\n");

        if (bookModel.removeBorrowed(book.getBookId(), student, true)) {
            return new Response<>(true, "Book was successfully returned for pending");
        }
        return new Response<>(true, "Book was not successfully returned for pending");
    }



    @Override
    public Response<LinkedList<Book>> getBooks() throws RemoteException {
        System.out.println("Client Request to get all the books");
        return new Response<>(true, bookModel.getBooks());
    }

    @Override
    public void logout(Student student) throws RemoteException {
        System.out.println(student.getAccount().getUserName() + " requested to logout");
        clientsHashMap.remove(student.getAccount().getAccountId());
        System.out.println(student.getAccount().getUserName() + " logged out successfully\n\n");
    }

    @Override
    public void sendMessage(String message, Student sender) throws RemoteException {
        for(ClientUpdateReceiver clientUpdateReceiver : clientsHashMap.values()) {
            clientUpdateReceiver.receiveMessage(message, sender);
        }
    }

    @Override
    public void sendNotificationToServer(ClientActions clientActions) throws RemoteException {
        serverUpdateReceiver.receiveUpdate(clientActions);
    }


    ///////////  Server Remote Methods--------


    /**
     *
     *  Server Remote Methods
     *
     * */
    @Override
    public Response<String> acceptBook(Book book, Student student) throws RemoteException {
        System.out.println("Server accepts" + book.getBookTitle() + " for " + student.getAccount().getUserName() + "\n\n");

        if (bookModel.addBorrowed(book.getBookId(), student)) {
            clientsHashMap.get(student.getAccount().getAccountId()).receiveUpdate(ServerActions.ACCEPT_BOOK_PENDING);
            return new Response<>(true, "Book was successfully added for pending");
        }
        return new Response<>(false, "Book was not added for pending");
    }

    @Override
    public Response<String> retrieveBook(Book book, Student student) throws RemoteException {
        System.out.println("Server retrieves" + book.getBookTitle() + " for " + student.getAccount().getUserName() + "\n\n");
        if (bookModel.removeBorrowed(book.getBookId(), student, false)) {

            clientsHashMap.get(student.getAccount().getAccountId()).receiveUpdate(ServerActions.RETRIEVES_BOOK);
            return new Response<>(true, "Book was successfully retrieved");
        }
        return new Response<>(false, "Book was not retrieved");
    }

    @Override
    public Response<String> editBook(Book book) throws RemoteException {
        return null;
    }

    @Override
    public Response<String> deleteBook(Book book) throws RemoteException {
        return null;
    }

    @Override
    public Response<String> cancelPending(Book book, Student student) throws RemoteException {
        System.out.println("Server cancels" + book.getBookTitle() + " for " + student.getAccount().getUserName() + "\n\n");

        if (bookModel.removePending(book.getBookId(), student)) {
            clientsHashMap.get(student.getAccount().getAccountId()).receiveUpdate(ServerActions.CANCEL_BOOK_PENDING);
            return new Response<>(true, "Book was successfully cancelled");
        }
        return new Response<>(false, "Book was not cancelled");
    }

    @Override
    public Response<String> createNewBook(Book book) throws RemoteException {
        return null;
    }

    @Override
    public Response<LinkedList<Book>> getAvailableBooks() throws RemoteException {
        System.out.println("Server shows the available number of books");
        return new Response<>(true, bookModel.getAvailableBooks());
    }

    @Override
    public Response<LinkedList<Book>> getUnavailableBooks() throws RemoteException {
        System.out.println("Server shows the unavailable number of books");
        return new Response<>(true, bookModel.getUnavailableBooks());
    }

    @Override
    public Response<LinkedList<Book>> getCurrentBorrowedBooks() throws RemoteException {
        System.out.println("Server shows the number of borrowed books with current borrowers");
        return new Response<>(true, bookModel.getBooksWithCurrentBorrowers());
    }

    public Response<LinkedList<Book>> getPreviousBorrowedBooks() throws RemoteException {
        System.out.println("Server shows the number of borrowed books with previous borrowers");
        return new Response<>(true, bookModel.getBooksWithPreviousBorrowers());
    }

    @Override
    public Response<LinkedList<Book>> getPendingBorrowingBooks() throws RemoteException {
        System.out.println("Server shows the number of pending books that were requested for borrowing");
        return new Response<>(true, bookModel.getBooksWithPendingBorrowers());
    }

    @Override
    public Response<LinkedList<Book>> getPendingReturningBooks() throws RemoteException {
        System.out.println("Server shows the number of pending books that were requested for returning");
        return new Response<>(true, bookModel.getBooksWithPendingBookReturners());
    }

    @Override
    public Response<LinkedList<Account>> getAccounts() throws RemoteException {
        return new Response<>(true, accountModel.getAccounts());
    }

    @Override
    public Response<String> broadcastMessage(String message) throws RemoteException {
        try {
            System.out.println("Server broadcasts: " + message);
            for (ClientUpdateReceiver clientUpdateReceiver : clientsHashMap.values()) {
                clientUpdateReceiver.receiveMessage(message, null);
            }
            return new Response<>(true, "Message broadcasted successfully.");
        } catch (Exception e) {
            return new Response<>(false, "Failed to broadcast message: " + e.getMessage());
        }
    }

    @Override
    public Response<String> banAccount(Account account) throws RemoteException {
        return null;
    }

    @Override
    public Response<String> unbanAccount(Account account) throws RemoteException {
        return null;
    }

    @Override
    public Response<String> deleteAccount(Student account) throws RemoteException {
        try {
            LinkedList<Student> studentAccounts = accountModel.getStudentAccounts();

            for (Student student : studentAccounts) {
                if (student.getAccount().getAccountId().equals(account.getAccount().getAccountId())) {
                    studentAccounts.remove(student);
                    accountModel.saveStudentAccountData(studentAccounts);
                    return new Response<>(true, "Account deleted successfully.");
                }
            }
            return new Response<>(false, "Account not found.");
        } catch (Exception e) {
            return  new Response<>(false, e.getMessage());
        }
    }

    @Override
    public Response<String> createAccount(Account account) throws RemoteException {
        try {
            if (accountModel.createAccount(account)) {
                return  new Response<>(true, "Account successfully created.");
            }
            return new Response<>(false, "Account now created.");
        } catch (Exception e) {
            return  new Response<>(false, e.getMessage());
        }
    }

    @Override
    public Response<String> changeUserPassword(Account account, String newPassword) throws RemoteException {
        return null;
    }

    @Override
    public void sendNotificationToClient(ServerActions serverActions) throws RemoteException {
        for(ClientUpdateReceiver clientUpdateReceiver : clientsHashMap.values()) {
            clientUpdateReceiver.receiveUpdate(serverActions);
        }
    }

    public Response<LinkedList<Student>> getStudentAccounts() {
        LinkedList<Student> studentAccounts = accountModel.getStudentAccounts();
        return new Response<>(true, studentAccounts);
    }

    private Student getStudentAccount(Account account) {
        LinkedList<Book> books = bookModel.getBooks();
        LinkedList<Book> borrowedBooks = new LinkedList<>();
        LinkedList<Book> pendingBooks = new LinkedList<>();

        for (Book book : books) {

            for (String borrowers : book.getCurrentBorrowers()) {

                if (borrowers.equals(account.getAccountId())) {
                    borrowedBooks.add(book);
                }

            }

            for (String pending : book.getPendingBorrowers()) {

                if (pending.equals(account.getAccountId())) {
                    pendingBooks.add(book);
                }

            }

        }

        return new Student(account, borrowedBooks.size(), pendingBooks, borrowedBooks);

    }

    private synchronized Book getUpdatedBook(String bookId) {

        try {
            return getBooks().getPayload().stream().filter(book -> book.getBookId().equals(bookId)).findAny().get();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

    }

}
