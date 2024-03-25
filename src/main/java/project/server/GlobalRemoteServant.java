package project.server;

import project.client.controller.ClientUpdateReceiver;
import project.server.controller.ServerUpdateReceiver;
import project.utilities.RMI.GlobalRemoteMethods;
import project.server.model.AccountModel;
import project.server.model.BookModel;
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
    private final BookModel bookModel = new BookModel();
    private final AccountModel accountModel = new AccountModel();
    private final ServerUpdateReceiver serverUpdateReceiver;

    public GlobalRemoteServant(ServerUpdateReceiver serverUpdateReceiver) throws RemoteException {
        this.serverUpdateReceiver = serverUpdateReceiver;
    }

    ///////////  Client Remote Methods--------

    /**
     * Client Remote Methods
     */
    @Override
    public Response<Student> logIn(Authentication credential, ClientUpdateReceiver clientUpdateReceiver) throws RemoteException {
        System.out.println("Client Request to log in");

        LinkedList<Account> allAccounts = accountModel.getAccounts();

        Optional<Account> account = allAccounts.stream().filter(ac -> ac.getUserName().equals(credential.getUserName()) && ac.getPassword().equals(credential.getPassword())).findFirst();

        if (account.isPresent()) {

            if (clientsHashMap.containsKey(account.get().getAccountId())) {
                return new Response<>(false, new Student(new Account("Your account is already logged in on another machine", "", "", "", "", "", true), 1, null, null, null));
            }

            if(account.get().getIsBanned()) {
                return new Response<>(false, new Student(new Account("Your Account is banned", "", "", "", "", "", true), 1, null, null, null));
            }

            clientsHashMap.put(account.get().getAccountId(), clientUpdateReceiver);
            System.out.println(account.get().getUserName() + " logged in successfully\n\n");
            return new Response<>(true, getStudentAccount(account.get()));
        } else {
            return new Response<>(false, new Student(null, 0, null, null, null));
        }
    }

    @Override
    public synchronized Response<String> borrowBook(Book book, Student student) throws RemoteException {
        System.out.println(student.getAccount().getUserName() + " Requested to borrow the book " + book.getBookTitle() + "\n\n");

        int latestBookCopies = getUpdatedBook(book.getBookId()).getCopies();

        if (latestBookCopies == 0) {
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

        if (bookModel.removeBorrowed(book.getBookId(), student, false)) {
            sendNotificationToServer(ClientActions.RETURN_BOOK);
            return new Response<>(true, "Book was successfully returned for pending");
        }

        return new Response<>(true, "Book was not successfully returned for pending");
    }


    @Override
    public Response<LinkedList<Book>> getBooks(boolean isClient) throws RemoteException {
        if (isClient) System.out.println("Client Request to get all the books");
        else System.out.println("Admin Request to get all the books");
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
        for (ClientUpdateReceiver clientUpdateReceiver : clientsHashMap.values()) {
            clientUpdateReceiver.receiveMessage(message, sender);
        }
    }

    @Override
    public void sendNotificationToServer(ClientActions clientActions) throws RemoteException {
        System.out.println(serverUpdateReceiver);
        System.out.println(clientActions);
        serverUpdateReceiver.receiveUpdate(clientActions);
    }


    ///////////  Server Remote Methods--------


    /**
     * Server Remote Methods
     */
    @Override
    public Response<String> acceptBook(Book book, Student student) throws RemoteException {
        System.out.println("Server accepts" + book.getBookTitle() + " for " + student.getAccount().getUserName() + "\n\n");

        if (bookModel.addBorrowed(book.getBookId(), student)) {

            if(clientsHashMap.containsKey(student.getAccount().getAccountId())) {
                clientsHashMap.get(student.getAccount().getAccountId()).receiveUpdate(ServerActions.ACCEPT_BOOK_PENDING);
            }

            return new Response<>(true, "Book was successfully added for pending");
        }
        return new Response<>(false, "Book was not added for pending");
    }

    @Override
    public Response<String> retrieveBook(Book book, Student student) throws RemoteException {
        System.out.println("Server retrieves" + book.getBookTitle() + " for " + student.getAccount().getUserName() + "\n\n");

        if (bookModel.removeBorrowed(book.getBookId(), student, true)) {

            if(clientsHashMap.containsKey(student.getAccount().getAccountId())) {
                clientsHashMap.get(student.getAccount().getAccountId()).receiveUpdate(ServerActions.RETRIEVES_BOOK);
            }


            return new Response<>(true, "Book was successfully retrieved");
        }
        return new Response<>(false, "Book was not retrieved");
    }

    @Override
    public Response<String> retrievePendingReturnBook(Book book, Student student) throws RemoteException {
        System.out.println("Server retrieves pending return book" + book.getBookTitle() + " for " + student.getAccount().getUserName() + "\n\n");

        if (bookModel.retrivePendingReturnBook(book.getBookId(), student)) {

            if(clientsHashMap.containsKey(student.getAccount().getAccountId())) {
                clientsHashMap.get(student.getAccount().getAccountId()).receiveUpdate(ServerActions.RETRIVE_PENDING_BOOK);
            }

            return new Response<>(true, "Book was successfully retrieved");
        }
        return new Response<>(false, "Book was not retrieved");
    }


    @Override
    public Response<String> editBook(Book book) throws RemoteException {

        if(bookModel.saveBookChanges(book)) {
            return new Response<>(true, book.getBookTitle() + " was successfully edited");
        }else {
            return new Response<>(false, book.getBookTitle() + " was not successfully edited");
        }

    }

    @Override
    public Response<String> deleteBook(Book book) throws RemoteException {
        bookModel.deleteBook(book);
        for(ClientUpdateReceiver clientUpdateReceiver : clientsHashMap.values()) {
            clientUpdateReceiver.receiveUpdate(ServerActions.DELETE_BOOK);
        }
        return new Response<>(true, book.getBookTitle() + " was successfully deleted");
    }

    @Override
    public Response<String> cancelPending(Book book, Student student) throws RemoteException {
        System.out.println("Server cancels" + book.getBookTitle() + " for " + student.getAccount().getUserName() + "\n\n");

        if (bookModel.removePending(book.getBookId(), student)) {

            if(clientsHashMap.containsKey(student.getAccount().getAccountId())) {
                clientsHashMap.get(student.getAccount().getAccountId()).receiveUpdate(ServerActions.CANCEL_BOOK_PENDING);
            }

            return new Response<>(true, "Book was successfully cancelled");
        }
        return new Response<>(false, "Book was not cancelled");
    }

    @Override
    public Response<String> createNewBook(Book book) throws RemoteException {
        bookModel.addBook(book);
        for(ClientUpdateReceiver clientUpdateReceiver : clientsHashMap.values()) {
            clientUpdateReceiver.receiveUpdate(ServerActions.ADDED_NEW_BOOK);
        }
        return new Response<>(true, book.getBookTitle() + " was successfully added");
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
    public Response<String> banAccount(Student account) throws RemoteException {
        account.getAccount().setIsBanned(true);
        accountModel.saveStudentAccountChanges(account);

        if(clientsHashMap.containsKey(account.getAccount().getAccountId())) {
            clientsHashMap.get(account.getAccount().getAccountId()).receiveUpdate(ServerActions.BAN_ACCOUNT);
        }

        return new Response<>(true, "Account Banned Successfully");
    }

    @Override
    public Response<String> unbanAccount(Student account) throws RemoteException {
        account.getAccount().setIsBanned(false);
        accountModel.saveStudentAccountChanges(account);

        if(clientsHashMap.containsKey(account.getAccount().getAccountId())) {
            clientsHashMap.get(account.getAccount().getAccountId()).receiveUpdate(ServerActions.UNBAN_ACCOUNT);
        }
        return new Response<>(true, "Account UnBanned Successfully");
    }

    @Override
    public Response<String> deleteAccount(Student account) throws RemoteException {
        accountModel.deleteAccount(account);

        if(clientsHashMap.containsKey(account.getAccount().getAccountId())) {
            clientsHashMap.get(account.getAccount().getAccountId()).receiveUpdate(ServerActions.DELETE_ACCOUNT);
        }
        return new Response<>(true, "Account Deleted Successfully");
    }

    @Override
    public Response<String> editAccount(Student account) throws RemoteException {

        //??usage
        accountModel.saveStudentAccountChanges(account);
        return new Response<>(true, "Account Edited Successfully");
    }

    @Override
    public Response<String> createAccount(Account account) throws RemoteException {
        accountModel.addAccount(new Student(account, 0, new LinkedList<>(), new LinkedList<>(), new LinkedList<>()));
        return new Response<>(true, "Account successfully created.");
    }

    @Override
    public Response<String> changeUserPassword(Student account, String newPassword) throws RemoteException {
        account.getAccount().setPassword(newPassword);
        accountModel.saveStudentAccountChanges(account);

        if(clientsHashMap.containsKey(account.getAccount().getAccountId())) {
            clientsHashMap.get(account.getAccount().getAccountId()).receiveUpdate(ServerActions.CHANGE_PASSWORD);
        }
        return new Response<>(true, "Account Password Successfully Changed");
    }

    @Override
    public void sendNotificationToClient(ServerActions serverActions) throws RemoteException {
        for (ClientUpdateReceiver clientUpdateReceiver : clientsHashMap.values()) {
            clientUpdateReceiver.receiveUpdate(serverActions);
        }
    }

    @Override
    public void sendNotificationToClient(ServerActions serverActions, Student student) throws RemoteException {
        clientsHashMap.get(student.getAccount().getAccountId()).receiveUpdate(serverActions);
    }

    public Response<LinkedList<Student>> getStudentAccounts() {
        LinkedList<Student> studentAccounts = accountModel.getStudentAccounts(accountModel.getBooks());
        return new Response<>(true, studentAccounts);
    }



    private Student getStudentAccount(Account account) {
        LinkedList<Book> books = bookModel.getBooks();
        LinkedList<Book> borrowedBooks = new LinkedList<>();
        LinkedList<Book> pendingBooks = new LinkedList<>();
        LinkedList<Book> pendingBookReturn = new LinkedList<>();

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

            for (String pending : book.getPendingBookReturners()) {

                if (pending.equals(account.getAccountId())) {
                    pendingBookReturn.add(book);
                }

            }

        }

        return new Student(account, borrowedBooks.size(), pendingBooks, borrowedBooks, pendingBookReturn);

    }

    private synchronized Book getUpdatedBook(String bookId) {

        try {
            return getBooks(true).getPayload().stream().filter(book -> book.getBookId().equals(bookId)).findAny().get();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

    }



}
