package project.utilities.model;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import project.utilities.referenceClasses.Book;

import java.util.LinkedList;

public class BookModel extends DataModel {

    private final String bookJSONPath = "src/main/resources/data/book.json";



    public boolean editBook(Book book) {

        LinkedList<Book> books = getBooks();

        for (int i = 0; i < books.size(); i++) {

            if (books.get(i).getBookId().equals(book.getBookId())) {
                books.remove(i);
                books.add(i, book);
                saveBookData(books);
                return true;
            }

        }

        return false;
    } // end of editBook method

    public void addBook(Book book) {
        LinkedList<Book> books = getBooks();
        books.addFirst(book);
        saveBookData(books);
    }

    public void saveBookData(LinkedList<Book> books) {
        JSONObject jsonObject = readJSON(bookJSONPath);
        JSONArray bookArray = new JSONArray();

        for (Book b : books) {
            bookArray.add(b.toJson());
        }

        jsonObject.put("book", bookArray);
        saveJSON(jsonObject, bookJSONPath);
    }



    public void deleteBook(Book book) {
        LinkedList<Book> books = getBooks();

        for(Book b : books) {

            if(b.getBookId().equals(book.getBookId())) {
                books.remove(b);
                saveBookData(books);
                break;
            }

        }

    }


    public LinkedList<Book> getBooksWithCurrentBorrowers() {
        LinkedList<Book> currentBorrowedBooks = new LinkedList<>();
        LinkedList<Book> books = getBooks();
        if (books != null) {
            for (Book book : books) {
                if (!book.getCurrentBorrowers().isEmpty()) {
                    currentBorrowedBooks.add(book);
                }
            }
        }
        return currentBorrowedBooks;
    }

    public LinkedList<Book> getBooksWithPendingBorrowers() {
        LinkedList<Book> pendingBorrowedBooks = new LinkedList<>();
        LinkedList<Book> books = getBooks();
        if (books != null) {
            for (Book book : books) {
                if (!book.getPendingBorrowers().isEmpty()) {
                    pendingBorrowedBooks.add(book);
                }
            }
        }
        return pendingBorrowedBooks;
    }

    public LinkedList<Book> getBooksWithPreviousBorrowers() {
        LinkedList<Book> previousBorrowedBooks = new LinkedList<>();
        LinkedList<Book> books = getBooks();
        if (books != null) {
            for (Book book : books) {
                if (!book.getPreviousBorrowers().isEmpty()) {
                    previousBorrowedBooks.add(book);
                }
            }
        }
        // Return the list of Unavailable Books
        return previousBorrowedBooks;
    }

    public LinkedList<Book> getBooksWithPendingBookReturners() {
        LinkedList<Book> pendingBookReturners = new LinkedList<>();
        LinkedList<Book> books = getBooks();
        if (books != null) {
            for (Book book : books) {
                if (!book.getPendingBookReturners().isEmpty()) {
                    pendingBookReturners.add(book);
                }
            }
        }
        return pendingBookReturners;
    }

    public LinkedList<Book> getAvailableBooks() {
        LinkedList<Book> availableBooks = new LinkedList<>();
        LinkedList<Book> books = getBooks();
        if (books != null) {
            for (Book book : books) {
                if (book.getCopies() > 0) {
                    availableBooks.add(book);
                }
            }
        }
        // Return the list of Unavailable Books
        return availableBooks;
    }

    public LinkedList<Book> getUnavailableBooks() {
        LinkedList<Book> unavailableBooks = new LinkedList<>();
        LinkedList<Book> books = getBooks();
        if (books != null) {
            for (Book book : books) {
                if (book.getCopies() == 0) {
                    unavailableBooks.add(book);
                }
            }
        }
        // Return the list of Unavailable Books
        return unavailableBooks;
    }
}
