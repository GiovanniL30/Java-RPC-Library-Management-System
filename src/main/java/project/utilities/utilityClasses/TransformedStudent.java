package project.utilities.utilityClasses;

import com.google.gson.JsonObject;
import project.utilities.referenceClasses.Account;
import project.utilities.referenceClasses.Book;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Represents a student in transformed format.
 */
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

    /**
     * Constructs a TransformedStudent object with the specified parameters.
     */
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

    /**
     * Gets the unique identifier of the account.
     *
     * @return The account ID.
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * Sets the unique identifier of the account.
     *
     * @param accountId The account ID to set.
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    /**
     * Gets the username of the student.
     *
     * @return The username.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the username of the student.
     *
     * @param userName The username to set.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets the first name of the student.
     *
     * @return The first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the student.
     *
     * @param firstName The first name to set.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the student.
     *
     * @return The last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the student.
     *
     * @param lastName The last name to set.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the email address of the student.
     *
     * @return The email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the student.
     *
     * @param email The email address to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the password of the student.
     *
     * @return The password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the student.
     *
     * @param password The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Checks if the student is banned.
     *
     * @return True if the student is banned, false otherwise.
     */
    public boolean isBanned() {
        return isBanned;
    }

    /**
     * Sets the banned status of the student.
     *
     * @param banned The banned status to set.
     */
    public void setBanned(boolean banned) {
        isBanned = banned;
    }

    /**
     * Gets the total number of books borrowed by the student.
     *
     * @return The total number of borrowed books.
     */
    public int getTotalBorrowedBooks() {
        return totalBorrowedBooks;
    }

    /**
     * Sets the total number of books borrowed by the student.
     *
     * @param totalBorrowedBooks The total number of borrowed books to set.
     */
    public void setTotalBorrowedBooks(int totalBorrowedBooks) {
        this.totalBorrowedBooks = totalBorrowedBooks;
    }

    /**
     * Gets a list of pending books for the student.
     *
     * @return A list of pending books.
     */
    public LinkedList<JsonObject> getPendingBooks() {
        return pendingBooks;
    }

    /**
     * Sets the list of pending books for the student.
     *
     * @param pendingBooks A list of pending books to set.
     */
    public void setPendingBooks(LinkedList<JsonObject> pendingBooks) {
        this.pendingBooks = pendingBooks;
    }

    /**
     * Gets a list of borrowed books by the student.
     *
     * @return A list of borrowed books.
     */
    public LinkedList<JsonObject> getBorrowedBooks() {
        return borrowedBooks;
    }

    /**
     * Sets the list of borrowed books by the student.
     *
     * @param borrowedBooks A list of borrowed books to set.
     */
    public void setBorrowedBooks(LinkedList<JsonObject> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    /**
     * Gets a list of pending books to return by the student.
     *
     * @return A list of pending books to return.
     */
    public LinkedList<JsonObject> getPendingReturnBook() {
        return pendingReturnBook;
    }

    /**
     * Sets the list of pending books to return by the student.
     *
     * @param pendingReturnBook A list of pending books to return.
     */
    public void setPendingReturnBook(LinkedList<JsonObject> pendingReturnBook) {
        this.pendingReturnBook = pendingReturnBook;
    }

    /**
     * Returns a string representation of the TransformedStudent object.
     *
     * @return A string representation of the TransformedStudent object.
     */
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
