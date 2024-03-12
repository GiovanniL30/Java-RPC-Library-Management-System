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

public class BookModel {

    public static void main(String[] args) {
        BookModel bookModel = new BookModel();
        for(Book book: bookModel.getBooks()) {
            System.out.println(book);
            System.out.println();
            System.out.println();
        }
    }

    public LinkedList<Book> getBooks() {

        LinkedList<Book> books = new LinkedList<>();
        JSONParser parser = new JSONParser();

        try {
            JSONObject json = (JSONObject) parser.parse(new FileReader("src/main/resources/data/book.json"));
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


                Book book = new Book(bookId, bookTitle, author, genre, shortDescription, imagePath, (int)copies, getStudentID(currentBorrowers), getStudentID(pendingBorrowers), getStudentID(pendingBookReturners), getStudentID(prevBookBorrowers));
                books.add(book);
            }

        } catch (IOException | ParseException  | NumberFormatException e) {
            throw new RuntimeException(e);
        }

        return books;
    }

    private LinkedList<String> getStudentID(JSONArray jsonArray){

        LinkedList<String> students = new LinkedList<>();

        for(Object object: jsonArray) {

            JSONObject jsonObject = (JSONObject) object;
            String studentID = (String) jsonObject.get("id");
            students.add(studentID);
        }

        return students;
    }



}
