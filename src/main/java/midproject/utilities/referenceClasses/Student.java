package midproject.utilities.referenceClasses;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Represents a student in the library system.
 */
public class Student implements Serializable {

    private Account account;
    private int totalBorrowedBooks;
    private LinkedList<Book> pendingBooks;
    private LinkedList<Book> borrowedBooks;
    private final LinkedList<Book> pendingReturnBook;

    /**
     * Constructs a Student object with the specified parameters.
     *
     * @param account           The account associated with the student.
     * @param totalBorrowedBooks The total number of books borrowed by the student.
     * @param pendingBooks      The list of books pending to be borrowed by the student.
     * @param borrowedBooks     The list of books currently borrowed by the student.
     * @param pendingReturnBook The list of books pending to be returned by the student.
     */
    public Student(Account account, int totalBorrowedBooks, LinkedList<Book> pendingBooks, LinkedList<Book> borrowedBooks, LinkedList<Book> pendingReturnBook) {
        this.account = account;
        this.totalBorrowedBooks = totalBorrowedBooks;
        this.pendingBooks = pendingBooks;
        this.borrowedBooks = borrowedBooks;
        this.pendingReturnBook = pendingReturnBook;
    }

    /**
     * Gets the account associated with the student.
     *
     * @return The account associated with the student.
     */
    public Account getAccount() {
        return account;
    }

    /**
     * Sets the account associated with the student.
     *
     * @param account The account to set.
     */
    public void setAccount(Account account) {
        this.account = account;
    }

    /**
     * Gets the total number of books borrowed by the student.
     *
     * @return The total number of books borrowed by the student.
     */
    public int getTotalBorrowedBooks() {
        return totalBorrowedBooks;
    }

    /**
     * Sets the total number of books borrowed by the student.
     *
     * @param totalBorrowedBooks The total number of books to set.
     */
    public void setTotalBorrowedBooks(int totalBorrowedBooks) {
        this.totalBorrowedBooks = totalBorrowedBooks;
    }

    /**
     * Gets the list of books pending to be borrowed by the student.
     *
     * @return The list of books pending to be borrowed.
     */
    public LinkedList<Book> getPendingBooks() {
        return pendingBooks;
    }

    /**
     * Sets the list of books pending to be borrowed by the student.
     *
     * @param pendingBooks The list of books pending to be borrowed.
     */
    public void setPendingBooks(LinkedList<Book> pendingBooks) {
        this.pendingBooks = pendingBooks;
    }

    /**
     * Gets the list of books currently borrowed by the student.
     *
     * @return The list of books currently borrowed by the student.
     */
    public LinkedList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    /**
     * Sets the list of books currently borrowed by the student.
     *
     * @param borrowedBooks The list of books currently borrowed by the student.
     */
    public void setBorrowedBooks(LinkedList<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    /**
     * Gets the list of books pending to be returned by the student.
     *
     * @return The list of books pending to be returned by the student.
     */
    public LinkedList<Book> getPendingReturnBook() {
        return pendingReturnBook;
    }

    /**
     * Returns a string representation of the Student object.
     *
     * @return A string representation of the Student object.
     */
    @Override
    public String toString() {
        return "Student{" + "account=" + account + ", totalBorrowedBooks=" + totalBorrowedBooks + ", pendingBooks=" + pendingBooks + ", borrowedBooks=" + borrowedBooks + '}';
    }

}
