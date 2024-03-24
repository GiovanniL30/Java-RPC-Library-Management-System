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
    private LinkedList<Book> pendingReturnBook;

    public Student(Account account, int totalBorrowedBooks, LinkedList<Book> pendingBooks, LinkedList<Book> borrowedBooks, LinkedList<Book> pendingReturnBook) {
        this.account = account;
        this.totalBorrowedBooks = totalBorrowedBooks;
        this.pendingBooks = pendingBooks;
        this.borrowedBooks = borrowedBooks;
        this.pendingReturnBook  = pendingReturnBook;
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

    public LinkedList<Book> getPendingReturnBook() {
        return pendingReturnBook;
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
        jsonObject.put("email", account.getEmail());
        jsonObject.put("password", account.getPassword());
        jsonObject.put("totalBorrowedBook", totalBorrowedBooks);
        jsonObject.put("userName", account.getUserName());

        JSONArray pendingBooksArray = getObjects("pendingBooks");
        JSONArray pendingReturnBooksArray = getObjects("pendingReturnBooks");
        JSONArray borrowedBooksArray = getObjects("borrowedBooks");


        jsonObject.put("pendingReturnBooks", pendingReturnBooksArray );
        jsonObject.put("borrowedBooks", borrowedBooksArray);
        jsonObject.put("pendingBooks", pendingBooksArray) ;


        return jsonObject;
    }

    private JSONArray getObjects(String type) {
        JSONArray jsonArray = new JSONArray();
        LinkedList<Book> strings = new LinkedList<>();

        switch (type) {
            case "pendingBooks" -> strings = getPendingBooks();
            case "borrowedBooks" -> strings = getBorrowedBooks();
            case "pendingReturnBooks" -> strings = getPendingReturnBook();
        }

        for (Book book : strings) {
            System.out.println(type);
            System.out.println(book.getBookId());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", book.getBookId());
            jsonArray.add(jsonObject);
        }

        return jsonArray;
    }
}
