package project.utilities.model;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import project.utilities.referenceClasses.Book;
import project.utilities.referenceClasses.Student;

import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class BookModel extends DataModel {

    public static void main(String[] args) {
        BookModel bookModel = new BookModel();
        for(Book book: bookModel.getBooks()) {
            System.out.println(book);
            System.out.println();
            System.out.println();
        }


        for(Book book: bookModel.getBooks()) {
            System.out.println(book);
            System.out.println();
            System.out.println();
        }
    }


    public LinkedList<Book> getBooks() {
        String filePath = "src/main/resources/data/book.json";
        LinkedList<Book> books = new LinkedList<>();
        JSONParser parser = new JSONParser();

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


                Book book = new Book(bookId, bookTitle, author, genre, shortDescription, imagePath, (int)copies, getStudentID(currentBorrowers), getStudentID(prevBookBorrowers), getStudentID(pendingBorrowers), getStudentID(pendingBookReturners));
                books.add(book);
            }

        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }

        return books;
    }

    private boolean editBook(Book book) {

        JSONObject json = readJSON("src/main/resources/data/book.json");
        JSONArray jsonArray = (JSONArray) json.get("book");


        if (jsonArray.isEmpty()) return false;


        for(Object o : jsonArray) {

            JSONObject bookJson =  (JSONObject) o;

            if(bookJson.get("bookId").equals(book.getBookId())) {

                jsonArray.remove(o);

                JSONObject updatedBook = new JSONObject();
                //addAllNece

            }


        }


        // Return false if the book to be edited was not found
        return false;
    } // end of editBook method


    private LinkedList<String> getStudentID(JSONArray jsonArray){

        LinkedList<String> students = new LinkedList<>();

        for(Object object: jsonArray) {

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
}
