package project.utilities.referenceClasses;

import java.io.Serializable;
import java.util.LinkedList;

public class Student implements Serializable {

    private Account account;
    private int totalBorrowedBooks;
    private LinkedList<Book> pendingBooks;
    private LinkedList<Book> borrowedBooks;

    public Student(Account account, int totalBorrowedBooks, LinkedList<Book> pendingBooks, LinkedList<Book> borrowedBooks) {
        this.account = account;
        this.totalBorrowedBooks = totalBorrowedBooks;
        this.pendingBooks = pendingBooks;
        this.borrowedBooks = borrowedBooks;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getTotalBorrowedBooks() {
        return totalBorrowedBooks;
    }

    public void setTotalBorrowedBooks(int totalBorrowedBooks) {
        this.totalBorrowedBooks = totalBorrowedBooks;
    }

    public LinkedList<Book> getPendingBooks() {
        return pendingBooks;
    }

    public void setPendingBooks(LinkedList<Book> pendingBooks) {
        this.pendingBooks = pendingBooks;
    }

    public LinkedList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(LinkedList<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    @Override
    public String toString() {
        return "Student{" +
                "account=" + account +
                ", totalBorrowedBooks=" + totalBorrowedBooks +
                ", pendingBooks=" + pendingBooks +
                ", borrowedBooks=" + borrowedBooks +
                '}';
    }
}
