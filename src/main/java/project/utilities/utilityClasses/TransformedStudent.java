package project.utilities.utilityClasses;

import com.google.gson.JsonObject;
import project.utilities.referenceClasses.Account;
import project.utilities.referenceClasses.Book;

import java.io.Serializable;
import java.util.LinkedList;

public class TransformedStudent implements Serializable {


    private String accountId;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean isBanned;
    private int totalBorrowedBooks;
    private LinkedList<JsonObject> pendingBooks;
    private LinkedList<JsonObject> borrowedBooks;
    private LinkedList<JsonObject> pendingReturnBook;

    public TransformedStudent(String accountId, String userName, String firstName, String lastName, String email, String password, boolean isBanned, int totalBorrowedBooks, LinkedList<JsonObject> pendingBooks, LinkedList<JsonObject> borrowedBooks, LinkedList<JsonObject> pendingReturnBook) {
        this.accountId = accountId;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.isBanned = isBanned;
        this.totalBorrowedBooks = totalBorrowedBooks;
        this.pendingBooks = pendingBooks;
        this.borrowedBooks = borrowedBooks;
        this.pendingReturnBook = pendingReturnBook;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isBanned() {
        return isBanned;
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
    }

    public int getTotalBorrowedBooks() {
        return totalBorrowedBooks;
    }

    public void setTotalBorrowedBooks(int totalBorrowedBooks) {
        this.totalBorrowedBooks = totalBorrowedBooks;
    }

    public LinkedList<JsonObject> getPendingBooks() {
        return pendingBooks;
    }

    public void setPendingBooks(LinkedList<JsonObject> pendingBooks) {
        this.pendingBooks = pendingBooks;
    }

    public LinkedList<JsonObject> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(LinkedList<JsonObject> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public LinkedList<JsonObject> getPendingReturnBook() {
        return pendingReturnBook;
    }

    public void setPendingReturnBook(LinkedList<JsonObject> pendingReturnBook) {
        this.pendingReturnBook = pendingReturnBook;
    }

    @Override
    public String toString() {
        return "TransformedStudent{" +
                "accountId='" + accountId + '\'' +
                ", userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isBanned=" + isBanned +
                ", totalBorrowedBooks=" + totalBorrowedBooks +
                ", pendingBooks=" + pendingBooks +
                ", borrowedBooks=" + borrowedBooks +
                ", pendingReturnBook=" + pendingReturnBook +
                '}';
    }
}
