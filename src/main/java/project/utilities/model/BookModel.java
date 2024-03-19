package project.utilities.model;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import project.utilities.referenceClasses.Book;

import java.util.LinkedList;

public class BookModel extends DataModel {

    private final String bookJSONPath = "src/main/resources/data/book.json";

    public static void main(String[] args) {
        BookModel bookModel = new BookModel();
        for (Book book : bookModel.getBooks()) {
            System.out.println(book);
            System.out.println();
            System.out.println();
        }


        for (Book book : bookModel.getBooks()) {
            System.out.println(book);
            System.out.println();
            System.out.println();
        }
    }


    public LinkedList<Book> getBooks() {
        String filePath = "src/main/resources/data/book.json";
        LinkedList<Book> books = new LinkedList<>();


        try {
            JSONObject json = readJSON(filePath);
            JSONArray jsonArray = (JSONArray) json.get("book");

            for (Object object : jsonArray) {

                JSONObject jsonObject = (JSONObject) object;
                String bookId = (String) jsonObject.get("bookId");
                String bookTitle = (String) jsonObject.get("bookTitle");
                String author = (String) jsonObject.get("author");
                String genre = (String) jsonObject.get("genre");
                long copies = (long) jsonObject.get("copies");
                String shortDescription = (String) jsonObject.get("shortDescription");
                String imagePath = (String) jsonObject.get("imagePath");

                JSONArray currentBorrowers = (JSONArray) jsonObject.get("currentBorrowers");
                JSONArray pendingBorrowers = (JSONArray) jsonObject.get("pendingBorrowers");
                JSONArray pendingBookReturners = (JSONArray) jsonObject.get("pendingBookReturners");
                JSONArray prevBookBorrowers = (JSONArray) jsonObject.get("prevBookBorrowers");


                Book book = new Book(bookId, bookTitle, author, genre, shortDescription, imagePath, (int) copies, getStudentID(currentBorrowers), getStudentID(prevBookBorrowers), getStudentID(pendingBorrowers), getStudentID(pendingBookReturners));
                books.add(book);
            }

        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }

        return books;
    }

    public boolean editBook(Book book) {

        LinkedList<Book> books = getBooks();

        for (int i = 0; i < books.size(); i++) {

            if (books.get(i).getBookId().equals(book.getBookId())) {
                books.remove(i);
                books.add(i, book);
                return true;
            }

        }

        return false;
    } // end of editBook method

    public void addBook(Book book) {
        LinkedList<Book> books = getBooks();
        books.addFirst(book);
        JSONObject jsonObject = readJSON(bookJSONPath);
        JSONArray bookArray = new JSONArray();

        for (Book b : books) {
            bookArray.add(b.toJson());
        }

        jsonObject.put("book", bookArray);
    }

    public void deleteBook(Book book) {
        LinkedList<Book> books = getBooks();

        books.remove(book);
        JSONObject jsonObject = readJSON(bookJSONPath);
        JSONArray bookArray = new JSONArray();

        for (Book b : books) {
            bookArray.add(b.toJson());
        }

        jsonObject.put("book", bookArray);
    }


    private LinkedList<String> getStudentID(JSONArray jsonArray) {

        LinkedList<String> students = new LinkedList<>();

        for (Object object : jsonArray) {

            JSONObject jsonObject = (JSONObject) object;
            String studentID = (String) jsonObject.get("id");
            students.add(studentID);
        }

        return students;
    }

    private LinkedList<Book> getBooksWithCurrentBorrowers() {
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

    private LinkedList<Book> getBooksWithPendingBorrowers() {
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

    private LinkedList<Book> getBooksWithPreviousBorrowers() {
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

    private LinkedList<Book> getAvailableBooks() {
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

    private LinkedList<Book> getUnavailableBooks() {
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
