package project.utilities.referenceClasses;

import netscape.javascript.JSObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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


    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", account.getAccountId());
        jsonObject.put("lastName", account.getLastName());
        jsonObject.put("firstName", account.getFirstName());
        jsonObject.put("email", account.getLastName());
        jsonObject.put("password", account.getFirstName());
        jsonObject.put("totalBorrowedBook", totalBorrowedBooks);

        jsonObject.put("pendingBooks", getObjects("pendingBooks"));
        jsonObject.put("borrowedBooks", getObjects("borrowedBooks"));


        return jsonObject;
    }

    private JSONArray getObjects(String type) {
        JSONArray jsonArray = new JSONArray();

        LinkedList<Book> strings;

        switch (type) {
            case "pendingBooks" -> strings = getPendingBooks();
            case "borrowedBooks" -> strings = getBorrowedBooks();
            default -> strings = new LinkedList<>();
        }

        for (Book book : borrowedBooks) {
            jsonArray.add(new JSONObject().put("id", book.getBookId()));
        }

        return jsonArray;
    }
}
